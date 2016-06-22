package imooc.seckill.dao;

import imooc.seckill.entity.Page;
import imooc.seckill.entity.Seckill;

import java.util.List;
import java.util.Map;

public interface SeckillDao {
	
	/**
	 * 减少库存
	 * @param seckillId 商品id
	 * @param killTime 操作时间
	 * @return 返回
	 */
	public int reduceNumber(Map<String, Object> params);

	/**
	 * 根据id查询商品
	 * @param seckillId
	 * @return
	 */
	Seckill querySeckillById(long seckillId);
	
	/**
	 * 查询所有商品
	 * @return
	 */
	List<Seckill> queryAll();
}
