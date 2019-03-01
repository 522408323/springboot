package com.lyx.houtai.mongodb.service;

import java.util.List;

import com.lyx.houtai.mongodb.model.SchoolInfo;


public interface ISchoolInfoService extends BaseService<SchoolInfo> {
    
	String save(SchoolInfo data);
	
	SchoolInfo findById(String id);
	
	List<SchoolInfo> queryProvInfo();
	
	List<SchoolInfo> queryCityInfoByProvCode( String provCode );
	
	List<SchoolInfo> queryAreaInfoByCityCode( String cityCode );
	
	List<SchoolInfo> queryInfoByAreaCode(String areaCode);
	
	List<SchoolInfo> queryInfoByAreaCodeAndKeyword(String areaCode, String keyword);
	
}
