package com.aowin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aowin.model.User;
import com.aowin.service.impl.UserServiceImpl;

@RestController
public class HelloController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/send")
	public String jms() {
		String code=userService.send();
		return code;
	}
	
	@RequestMapping("/login")
	public Map login(String msg,String account,String password,HttpServletRequest request) {
		boolean check=userService.checkcode(msg);
		Map map=new HashMap();
		if(check) {
			User user=userService.Login(account, password);
			request.getSession().setAttribute("user", user);
			map.put("user", user);
			return map;
		}else {
			request.setAttribute("error", "验证码输入错误!");
			return null;
		}
	}
}
