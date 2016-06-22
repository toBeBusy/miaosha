package imooc.seckill.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
	
	private static final Logger logger = LoggerFactory.getLogger(TestLog.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.debug("开始。");
		System.out.println("什么玩意。");
	}

}
