package com.service;

import java.util.List;

import com.pojo.po.TUser;

public interface userService {
	List<TUser> getAllAccount()throws Exception;
}
