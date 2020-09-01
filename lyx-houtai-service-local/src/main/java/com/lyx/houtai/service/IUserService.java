package com.lyx.houtai.service;

import com.lyx.houtai.model.User;

public interface IUserService {
	
	 User queryUserById(Long id);

	 int insertRecord(User user);
	 
}
