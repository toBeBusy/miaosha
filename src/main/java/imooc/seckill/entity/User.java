package imooc.seckill.entity;

import java.io.Serializable;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2782172836356828938L;

	//用户名
	private String userName;
	
	//密码
	private String password;

	//手机号
	private long userPhone;
	
	private String userType;

	
	
	public User(String userName, String password, long userPhone,
			String userType) {
		super();
		this.userName = userName;
		this.password = password;
		this.userPhone = userPhone;
		this.userType = userType;
	}
	
	public User(String userName, String password, long userPhone) {
		super();
		this.userName = userName;
		this.password = password;
		this.userPhone = userPhone;
	}
	
	public User(){
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password
				+ ", userPhone=" + userPhone + ", userType=" + userType + "]";
	}
	
	public void readObject(){
		System.out.println("读对象。");
	}
	
	public void writeObject(){
		System.out.println("写对象。");
	}
}
