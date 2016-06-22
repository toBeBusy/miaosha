package imooc.seckill.entity;

public class Page {
	
	private int totalPage;
	private int nowPage;
	private int nextPage;
	private int perPage;
	
	public Page(int totalPage, int nowPage, int nextPage, int perPage) {
		super();
		this.totalPage = totalPage;
		this.nowPage = nowPage;
		this.nextPage = nextPage;
		this.perPage = perPage;
	}
	
	public Page(int totalPage, int nowPage) {
		super();
		this.totalPage = totalPage;
		this.nowPage = nowPage;
	}

	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	
	

}
