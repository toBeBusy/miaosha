package imooc.test.dao;

import imooc.seckill.dao.SeckillDao;
import imooc.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SeckillDaoTest {
	@Resource(name="seckillDao")
	private SeckillDao seckillDao;

	@Test
	public void testReduceNumber(){
		seckillDao.reduceNumber(1001, new Date());
	}

	@Test
	public void testQuerySeckillById(){
		seckillDao.querySeckillById(1001);
	}
	
	@Test
	public void testQueryAll(){
		List<Seckill> seckillList = seckillDao.queryAll();
		System.out.println("");
	}
}
