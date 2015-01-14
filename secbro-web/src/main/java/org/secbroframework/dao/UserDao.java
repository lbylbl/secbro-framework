package org.secbroframework.dao;

import javax.annotation.Resource;

import org.secbroframework.mapper.UserMapper;
import org.secbroframework.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Resource
	private UserMapper userMapper;
	
	public User getUser(int id){
		return userMapper.getUser(id);
	}
	
	public void deleteUser(int id){
		userMapper.deleteUser(id);
	}

}
