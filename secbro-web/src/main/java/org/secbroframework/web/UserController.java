package org.secbroframework.web;

import javax.annotation.Resource;

import org.secbroframework.model.User;
import org.secbroframework.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    
    @Resource
    private UserService userService;
    
    @RequestMapping("getUser.htm")
    public String getUser(){
        User user = userService.getUser(1);
        if(user == null){
            System.out.println("User is null");
        } else {
            System.out.println(user.getUsername());
        }
        return "user/userList";
    }

}
