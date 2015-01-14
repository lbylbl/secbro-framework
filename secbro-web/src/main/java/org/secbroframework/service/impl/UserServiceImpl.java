package org.secbroframework.service.impl;

import org.secbroframework.mapper.UserMapper;
import org.secbroframework.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    
    private UserMapper userMapper;
    
    @Override
    public void deleteUser(int id){
        userMapper.deleteBlog(id);
    }

}
