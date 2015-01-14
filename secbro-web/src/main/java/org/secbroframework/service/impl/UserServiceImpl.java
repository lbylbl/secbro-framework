package org.secbroframework.service.impl;

import javax.annotation.Resource;

import org.secbroframework.dao.UserDao;
import org.secbroframework.model.User;
import org.secbroframework.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource
    private UserDao userDao;
    @Override
    public void deleteUser(int id){
    	
    }

	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	}

}
