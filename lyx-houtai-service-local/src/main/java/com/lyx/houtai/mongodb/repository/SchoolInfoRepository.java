package com.lyx.houtai.mongodb.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lyx.houtai.mongodb.model.SchoolInfo;

public interface SchoolInfoRepository extends MongoRepository<SchoolInfo, String> {
	
	List<SchoolInfo> findByAreaCode(String areaCode);
}
