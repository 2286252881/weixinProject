package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.mapper.TUserMapper;
import com.pojo.po.TUser;
import com.service.userService;

public class UserServiceImpl implements userService {

	
	@Autowired private TUserMapper userMapper;

	public List<TUser> getAllAccount() throws Exception {
		return null;
	}
}
