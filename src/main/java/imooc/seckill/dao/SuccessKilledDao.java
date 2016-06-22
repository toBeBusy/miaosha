package imooc.seckill.dao;

import java.util.Map;

import imooc.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

	/**
	 * 插入秒杀明细信息
	 * @param successKilled
	 * @return
	 */
	public int insertSuccessKilled(Map<String, Object> param);
	
	/**
	 * 查询秒杀明细表
	 * @param successkilled
	 * @return
	 */
	SuccessKilled querySuccessKilled(SuccessKilled successkilled);
}
