package imooc.seckill.enmu;

public enum SeckillState {
	SUCCESS(1,"秒杀成功"),
	CLOSE(2,"秒杀结束"),
	REPEAT(3,"重复秒杀");
//	()
	
	private int state;
	private String stateInfo;
	
	private SeckillState(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	
	

}
