package com.lyx.houtai.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lyx.houtai.mapper.UserMapper;
import com.lyx.houtai.model.User;
import com.lyx.houtai.service.IUserService;

@Service
@Transactional
public class IUserServiceImpl implements IUserService {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
	private UserMapper userMapper;
	
	public User queryUserById(Long id) {
		logger.debug("查询id:【"+id+"】数据");
		return userMapper.selectByPrimaryKey(id);
	}

}
