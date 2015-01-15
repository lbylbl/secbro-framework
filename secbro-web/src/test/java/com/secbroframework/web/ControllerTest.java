package com.secbroframework.web;

import org.junit.Test;
import org.secbroframework.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ControllerTest {
	@Test
    public void userServiceTest(){
	    
	    String[] str = new String[]{"context/applicationContext-common.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(str);
        UserService userService = (UserService)context.getBean("userService");
        System.out.println(userService.getUser(1).getUsername());
    }
}
