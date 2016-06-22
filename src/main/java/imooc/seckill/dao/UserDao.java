package imooc.seckill.dao;

import imooc.seckill.entity.User;

public interface UserDao {
	
	//用户登录
	public User login(User user);
	
	//用户注册
	public int userRegister(User user);
	
	//修改密码
	public int updatePassword(User user);

	//查询用户
	public User queryUser(User user);
}
