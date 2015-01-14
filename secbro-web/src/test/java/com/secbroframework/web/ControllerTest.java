package com.secbroframework.web;

import org.junit.Test;
import org.secbroframework.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ControllerTest {
	@Test
    public void userServiceTest(){
        ApplicationContext context = new ClassPathXmlApplicationContext("context/applicationContext-common.xml");
        UserService userService = (UserService)context.getBean("userService");
        System.out.println(userService.getUser(1).getUsername());
    }
}
