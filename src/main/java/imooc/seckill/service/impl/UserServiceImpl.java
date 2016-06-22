package imooc.seckill.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imooc.seckill.dao.UserDao;
import imooc.seckill.entity.User;
import imooc.seckill.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public User login(User user) {
		User result = userDao.login(user);
		return result;
	}

	public boolean userRegister(User user) {
		int result = userDao.userRegister(user);
		if(result > 0){
			return true;
		}
		return false;
	}

	public boolean updatePassword(User user) {
		int result = userDao.updatePassword(user);
		if(result > 0){
			return true;
		}
		return false;
	}

	public User queryUser(User user) {
		User result = userDao.queryUser(user);
		return result;
	}

	
}
