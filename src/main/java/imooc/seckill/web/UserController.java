package imooc.seckill.web;

import imooc.seckill.entity.User;
import imooc.seckill.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request) {
		return "loginView";
	}

	@RequestMapping("/login")
	public String login(User user,HttpSession session, HttpServletResponse response,HttpServletRequest request){
		User result = userService.login(user);
		//登录成功，跳转到list页面
		if(result != null 
				&& result.getUserName() != null 
				&& !result.getUserName().isEmpty()){
			session.setAttribute("user", result);
			Cookie userNameCookie = new Cookie("userName", result.getUserName());
			Cookie passwordCookie = new Cookie("password", result.getPassword());
			userNameCookie.setPath("/seckill");
			passwordCookie.setPath("/seckill");
			userNameCookie.setMaxAge(1000);
			passwordCookie.setMaxAge(1000);
			response.addCookie(userNameCookie);
			response.addCookie(passwordCookie);
			
			
			return "redirect:/seckill/list";
		} else {
			//登录失败
			if(request.getAttribute("first") == null){
				String errorMessage = "密码错误。";
				request.setAttribute("message", errorMessage);
			}
		}
		return "loginView";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "registerView";
	}

	@RequestMapping(value = "/registerHandler", method = RequestMethod.POST)
	public String registerHandler(User user) {
		boolean result = userService.userRegister(user);
		logger.debug(user.toString());
		if (result) {
			return "redirect:/user/login";
		}
		return "error";
	}
	
	@RequestMapping(value = "/queryUserByName", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" } )
	@ResponseBody
	public User queryUserByUserName(User user){
		User result = userService.queryUser(user);
		return result;
	}
}
