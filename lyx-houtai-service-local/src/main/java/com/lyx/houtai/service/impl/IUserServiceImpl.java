package com.lyx.houtai.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class IUserServiceImpl implements IUserService {
	
    @Autowired
	private UserMapper userMapper;

    @Override
	public User queryUserById(Long id) {
		log.info("查询id:【"+id+"】数据");
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insertRecord(User user) {
		log.info("查询id:【{}】数据",JSON.toJSONString(user));
		return userMapper.insert(user);
	}

}
