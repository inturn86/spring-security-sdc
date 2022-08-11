package com.sdc.study.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sdc.study.security.jwt.exception.JwtRuntimeException;

@Controller
public class TestController {

	// (^(?:.*(?<!jsp))$)
//	@RequestMapping(value = "{domain:^(?!resources|html|ws|webjar|swagger*).*}/**/*", method = { RequestMethod.GET, RequestMethod.POST })
//	public String pathUrl(HttpServletRequest request, Model model) throws Exception {
//		String url = request.getServletPath();
////		if (url.contains(viewSuffix)) {
////			throw new SFPException("접근할 수 없는 URL 입니다.");
////		}
//		return url;
//	}

	// @RequestMapping(value = "{extension:^(?:.*(?<!jsp))$}", method = {RequestMethod.GET, RequestMethod.POST})
//	@RequestMapping(value = "/{domain:^(?!resources|html|swagger*).*}", method = { RequestMethod.GET, RequestMethod.POST })
//	public String pathUrl(HttpServletRequest request){
//		return request.getServletPath();
//	}

	@RequestMapping("/login.page")
	public String login() throws Exception {
		return "/login/login";
	}

	@RequestMapping("/deny.page")
	public String deny() throws Exception {
		return "/security/deny";
	}

	@RequestMapping("/")
//	@Secured(value = "ROLE_BASIC")
	public String index() throws Exception {

		return "/main";
	}

	@RequestMapping("/mainHome.page")
//	@Secured(value = "ROLE_BASIC")
	public String loginMain() throws Exception {

		return "main";
	}

	@RequestMapping("/test/mainHome.page")
//	@Secured(value = "ROLE_BASIC")
	public String testloginMain() throws Exception {

		return "main";
	}
}
