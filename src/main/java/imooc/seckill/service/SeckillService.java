package imooc.seckill.service;

import imooc.seckill.dto.Exposer;
import imooc.seckill.dto.SeckillExecution;
import imooc.seckill.entity.Page;
import imooc.seckill.entity.Seckill;
import imooc.seckill.exception.RepeatKillException;
import imooc.seckill.exception.SeckillCloseException;
import imooc.seckill.exception.SeckillException;

import java.util.List;

/**
 * 秒杀服务接口
 * 
 * @author 313353
 * 
 */
public interface SeckillService {

	/**
	 * 查询所有秒杀
	 * 
	 * @return
	 */
	List<Seckill> getSeckills();

	/**
	 * 根据ID查询秒杀
	 * 
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);

	/**
	 * 秒杀开启时输出秒杀接口地址， 否则输出系统时间和秒杀时间
	 * 
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);

	/**
	 * 
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException;
}
