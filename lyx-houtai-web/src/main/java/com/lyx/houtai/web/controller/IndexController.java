package com.lyx.houtai.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyx.houtai.model.User;
import com.lyx.houtai.mongodb.model.SchoolInfo;
import com.lyx.houtai.mongodb.service.ISchoolInfoService;
import com.lyx.houtai.service.IUserService;

@RestController
@RequestMapping("/home")
public class IndexController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private ISchoolInfoService schoolInfoService;
	
     @RequestMapping("/index")
     public String index(){
    	 return "SpringBoot 多模块工程 Hello word!haha111222333";
     }
     
     @RequestMapping("/getUserById")
     public String getUserById(){
    	 User user = userService.queryUserById(1L);
    	 return "user:"+user.toString();
     }
     
     @RequestMapping("/getSchoolById")
     public String getUserSchoolByUserId(@RequestParam(value="id",required=false)String id){
    	 if ( StringUtils.isBlank(id) ) {
    		 id = "0";
    	 }
    	 SchoolInfo info = schoolInfoService.findById(id);
    	 return "SchoolInfo:"+(info==null?"":info.toString());
     }
}
