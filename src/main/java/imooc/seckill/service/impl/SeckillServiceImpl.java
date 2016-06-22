package imooc.seckill.service.impl;

import imooc.seckill.dao.SeckillDao;
import imooc.seckill.dao.SuccessKilledDao;
import imooc.seckill.dto.Exposer;
import imooc.seckill.dto.SeckillExecution;
import imooc.seckill.entity.Page;
import imooc.seckill.entity.Seckill;
import imooc.seckill.entity.SuccessKilled;
import imooc.seckill.exception.RepeatKillException;
import imooc.seckill.exception.SeckillCloseException;
import imooc.seckill.exception.SeckillException;
import imooc.seckill.service.SeckillService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

	@Autowired
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKilledDao successKilledDao;

	private final String slat = "awefzsrfgareg!#@$sefawef4568";

	public List<Seckill> getSeckills() {
		return seckillDao.queryAll();
	}

	public Seckill getById(long seckillId) {
		return seckillDao.querySeckillById(seckillId);
	}

	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.querySeckillById(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// 系统当前时间
		Date nowTime = new Date();
		if (nowTime.getTime() < startTime.getTime()
				|| nowTime.getTime() > endTime.getTime()) {
			Exposer exposer = new Exposer(false, null, seckillId,
					nowTime.getTime(), startTime.getTime(), endTime.getTime());
			return exposer;
		}
		String md5 = this.getMD5(seckillId);
		return new Exposer(true, md5, seckillId,
				nowTime.getTime(), startTime.getTime(), endTime.getTime());
	}

	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillException, RepeatKillException,
			SeckillCloseException {
		if (md5 == null || !md5.equals(this.getMD5(seckillId))) {
			throw new SeckillException("seckill data rewrite");
		}
		//执行秒杀逻辑：
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("seckillId", seckillId);
		params.put("killTime", new Date());
		int updateCount = seckillDao.reduceNumber(params);
		if(updateCount <= 0){
			//秒杀结束
			throw new SeckillCloseException("seckill closed");
		}
		//向秒杀成功表中插入信息
		Map<String, Object> insertParams = new HashMap<String, Object>();
		insertParams.put("seckillId", seckillId);
		insertParams.put("userPhone", userPhone);
		int insertCount = successKilledDao.insertSuccessKilled(insertParams);
		SuccessKilled successKilled = null;
		if(insertCount > 0){
			//秒杀成功
			successKilled = successKilledDao.querySuccessKilled(new SuccessKilled(seckillId, userPhone));
		} else {
			throw new SeckillException("重复秒杀。");
		}
		
		return new SeckillExecution(seckillId, 1, "秒杀成功", successKilled);
	}

	private String getMD5(long seckillId) {
		String base = seckillId + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

}
