package imooc.seckill.dto;

import imooc.seckill.entity.SuccessKilled;

/**
 * 封装秒杀执行后的结果
 * @author 313353
 *
 */
public class SeckillExecution {
	private long seckillId;
	
	private int state;
	
	private String stateInfo;
	
	private SuccessKilled successKilled;

	public SeckillExecution(long seckillId, int state, String stateInfo,
			SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = state;
		this.stateInfo = stateInfo;
		this.successKilled = successKilled;
	}

	public SeckillExecution(long seckillId, String stateInfo) {
		super();
		this.seckillId = seckillId;
		this.stateInfo = stateInfo;
	}



	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}
	
	
}
