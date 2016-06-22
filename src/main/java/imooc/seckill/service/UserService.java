package imooc.seckill.service;

import imooc.seckill.entity.User;

public interface UserService {
	
		//用户登录
		public User login(User user);
		
		//用户注册
		public boolean userRegister(User user);
		
		//修改密码
		public boolean updatePassword(User user);

		//查询用户
		public User queryUser(User user);
}
