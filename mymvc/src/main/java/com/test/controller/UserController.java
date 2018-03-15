package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tanlei.mvc.framework.annotation.Controller;
import com.tanlei.mvc.framework.annotation.RequestMapping;

@Controller(url = "/user")  
public class UserController {  
  
    @RequestMapping(url ="/show.html")  
    public String login(User user, String haha, HttpServletRequest request, HttpSession session) {
    	//modelandview小部分和前端处理已完成
    	UserServiceImpl userService = new UserServiceImpl();
    	userService.sayHello(user);
    	System.out.println(haha);
    	request.setAttribute("userName", "tanlei");
    	session.setAttribute("age", 27);
        return "forward:index.jsp"; //TODO: 视图解析器部分
    }
    
    @RequestMapping(url ="/redirect.html")  
    public String redirect() {
        return "redirect:/user/show.html"; //TODO: 视图解析器部分
    }
    
    
}  