package imooc.seckill.web;

import imooc.seckill.dto.Exposer;
import imooc.seckill.dto.SeckillExecution;
import imooc.seckill.dto.SeckillResult;
import imooc.seckill.entity.Page;
import imooc.seckill.entity.Seckill;
import imooc.seckill.entity.User;
import imooc.seckill.exception.RepeatKillException;
import imooc.seckill.exception.SeckillCloseException;
import imooc.seckill.service.SeckillService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
// @Service @Component
@RequestMapping("/seckill")
// 模块名 url:/模块/资源/{id}/细分
public class SeckillController {

	private final Logger logger = LoggerFactory
			.getLogger(SeckillController.class);

	@Autowired
	private SeckillService seckillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model,HttpSession session,
			@CookieValue(value="userName",required = false) String userName,
			@CookieValue(value="password",required = false) String passWord) {
		List<Seckill> list = seckillService.getSeckills();
		User user = (User)session.getAttribute("user");
		System.out.println(userName);
		System.out.println(passWord);
		System.out.println(user);
		logger.debug(user.toString());
		model.addAttribute("list", list);
		return "list";
	}

	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
		if (seckillId == null) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (null == seckill) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "detail";
	}

	// ajax json
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<Exposer> exposer(
			@PathVariable("seckillId") Long seckillId) {
		SeckillResult<Exposer> result;
		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result = new SeckillResult<Exposer>(true, e.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(
			@PathVariable("seckillId") Long seckillId,
			@PathVariable("md5") String md5,
			@CookieValue(value = "killPhone", required = false) Long userPhone) {
		if (null == userPhone) {
			return new SeckillResult<SeckillExecution>(false, "未注册");
		}
		SeckillResult<SeckillExecution> result = null;
		try {
			SeckillExecution seckillExecution = seckillService.executeSeckill(
					seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true, seckillExecution);
		} catch (SeckillCloseException e) {
			SeckillExecution excution = new SeckillExecution(seckillId, "秒杀关闭");
			return new SeckillResult<SeckillExecution>(true, excution);
		} catch (RepeatKillException e) {
			SeckillExecution excution = new SeckillExecution(seckillId, "重复秒杀");
			return new SeckillResult<SeckillExecution>(true, excution);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			SeckillExecution excution = new SeckillExecution(seckillId, "内部错误");
			return new SeckillResult<SeckillExecution>(true, excution);
		}
	}

	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time() {
		Long time = new Date().getTime();
		return new SeckillResult<Long>(true, time);
	}
}
