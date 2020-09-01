package com.lyx.houtai.web.controller;

import com.alibaba.fastjson.JSON;
import com.lyx.houtai.facade.StudentPageParam;
import com.lyx.houtai.mongodb.model.StudentInfo;
import com.lyx.houtai.mongodb.repository.StudentMongodbDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lyx.houtai.model.User;
import com.lyx.houtai.mongodb.model.SchoolInfo;
import com.lyx.houtai.mongodb.service.ISchoolInfoService;
import com.lyx.houtai.service.IUserService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/home")
@Slf4j
public class IndexController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ISchoolInfoService schoolInfoService;
	@Autowired
	private StudentMongodbDao studentMongodbDao;
	
     @RequestMapping("/index")
     public String index(){
    	 return "SpringBoot 多模块工程 Hello word!haha111222333";
     }
     
     @RequestMapping("/getUserById")
     public String getUserById(){
    	 User user = userService.queryUserById(1L);
    	 return "user:"+user.toString();
     }

	@RequestMapping("/insertUser")
	public void insertUser(){
     	User user = new User();
     	user.setCreateTime(new Date());
     	user.setUserName("李霞22");
		int count = userService.insertRecord(user);
		log.info("添加结束:{}",count);
	}
     
     @RequestMapping("/getSchoolById")
     public String getUserSchoolByUserId(@RequestParam(value="id",required=false)String id){
    	 if ( StringUtils.isBlank(id) ) {
    		 id = "0";
    	 }
    	 SchoolInfo info = schoolInfoService.findById(id);
    	 return "SchoolInfo:"+(info==null?"":info.toString());
     }

	@RequestMapping("/page/student/list")
	public List<StudentInfo> getUserSchoolByUserId(@Valid @RequestBody StudentPageParam studentPageParam){
		return studentMongodbDao.queryPageList(studentPageParam);
	}

	@PostMapping("/add/student")
	public String insertStudent(@Valid @RequestBody StudentInfo studentInfo){
     	log.info("ssss:{}", JSON.toJSONString(studentInfo));
		return studentMongodbDao.add(studentInfo);
	}

}
