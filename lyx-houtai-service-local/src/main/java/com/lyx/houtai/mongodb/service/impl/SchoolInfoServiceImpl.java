package com.lyx.houtai.mongodb.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.lyx.houtai.mongodb.model.SchoolInfo;
import com.lyx.houtai.mongodb.repository.SchoolInfoRepository;
import com.lyx.houtai.mongodb.service.ISchoolInfoService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class SchoolInfoServiceImpl extends BaseMongoServiceImpl<SchoolInfo> implements ISchoolInfoService {

    private final static Logger logger = LoggerFactory.getLogger(SchoolInfoServiceImpl.class);
    
    private final static String tableName = "schoolInfo";

    @Autowired
    private SchoolInfoRepository dao;
    
	@Override
	public Update buildUpdate(SchoolInfo t) {
		Update update = new Update();

        if(t != null) {

            Class<? extends SchoolInfo> clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Method method = null;
            for(Field field : fields) {
                String fieldName = field.getName();
                try {
                    method = clazz.getMethod(new StringBuffer("get")
                            .append(Character.toUpperCase(fieldName.charAt(0)))
                            .append(fieldName.substring(1)).toString()
                    );
                    Object result = method.invoke(t);
                    if(result != null) {
                        update.set(fieldName, result);
                    }
                }
                catch (NoSuchMethodException e1) {
                    logger.warn("buildUpdate warn , {} field not exited");
                    continue;
                }
                catch (Exception e) {
                    logger.error("buildUpdate error {} ", e);
                }
            }

            return update;

        }
        return null;
	}

	@Override
	public String save(SchoolInfo data) {
		return dao.save(data).getId();
	}

	@Override
	public SchoolInfo findById(String id) {
		Optional<SchoolInfo> record = dao.findById(id);
		if ( record.isPresent() ) {
			return record.get();
		}
		return null;
	}

	@Override
	public List<SchoolInfo> queryCityInfoByProvCode(String provCode) {
		
        List<SchoolInfo> list = new ArrayList<SchoolInfo>();
		Criteria criteria = Criteria.where("provinceCode").is(provCode);
		 Aggregation agg = Aggregation.newAggregation(
	                Arrays.asList(
	                        //Aggregation.project("name"),
	                        // 1：sql where 语句筛选符合条件的记录
	                        Aggregation.match(criteria),
	                        // 2：分组条件，设置分组字段
	                        Aggregation.group("cityCode")
	                        //.count().as("allCount")// 增加COUNT为分组后输出的字段
	                        .last("cityCode").as("cityCode").last("cityName").as("cityName"), // 增加publishDate为分组后输出的字段
	                        // 3：重新挑选字段
	                        Aggregation.project("cityCode","cityName")
	                )
	        );
        AggregationResults<SchoolInfo> results = mongoTemplate.aggregate(agg, tableName, SchoolInfo.class);
        if ( results != null && results.getMappedResults() != null ) {
        	list = results.getMappedResults();
        }
		return list;
	}

	@Override
	public List<SchoolInfo> queryAreaInfoByCityCode(String cityCode) {
		List<SchoolInfo> list = new ArrayList<SchoolInfo>();
		Criteria criteria = Criteria.where("cityCode").is(cityCode);
		 Aggregation agg = Aggregation.newAggregation(
	                Arrays.asList(
	                        //Aggregation.project("name"),
	                        // 1：sql where 语句筛选符合条件的记录
	                        Aggregation.match(criteria),
	                        // 2：分组条件，设置分组字段
	                        Aggregation.group("areaCode")
	                        //.count().as("allCount")// 增加COUNT为分组后输出的字段
	                        .last("areaCode").as("areaCode").last("areaName").as("areaName"), // 增加publishDate为分组后输出的字段
	                        // 3：重新挑选字段
	                        Aggregation.project("areaCode","areaName")
	                )
	        );
        AggregationResults<SchoolInfo> results = mongoTemplate.aggregate(agg, tableName, SchoolInfo.class);
        if ( results != null && results.getMappedResults() != null ) {
        	list = results.getMappedResults();
        }
		return list;
	}

	@Override
	public List<SchoolInfo> queryInfoByAreaCode(String areaCode) {
		return dao.findByAreaCode(areaCode);
	}

	@Override
	public List<SchoolInfo> queryProvInfo() {
		List<SchoolInfo> list = new ArrayList<SchoolInfo>();
		 Aggregation agg = Aggregation.newAggregation(
	                Arrays.asList(
	                        //Aggregation.project("name"),
	                        // 1：sql where 语句筛选符合条件的记录
	                        //Aggregation.match(criteria),
	                        // 2：分组条件，设置分组字段
	                        Aggregation.group("provinceCode")
	                        //.count().as("allCount")// 增加COUNT为分组后输出的字段
	                        .last("provinceCode").as("provinceCode").last("provinceName").as("provinceName"), // 增加publishDate为分组后输出的字段
	                        // 3：重新挑选字段
	                        Aggregation.project("provinceCode","provinceName")
	                )
	        );
        AggregationResults<SchoolInfo> results = mongoTemplate.aggregate(agg, tableName, SchoolInfo.class);
        if ( results != null && results.getMappedResults() != null ) {
        	list = results.getMappedResults();
        }
		return list;
	}

	@Override
	public List<SchoolInfo> queryInfoByAreaCodeAndKeyword(String areaCode, String keyword) {
		//模糊匹配
		Pattern pattern = Pattern.compile("^.*"+keyword+".*$", Pattern.CASE_INSENSITIVE);
		Query query = Query.query(Criteria.where("areaCode").is(areaCode)); 
		query.addCriteria(Criteria.where("schoolName").regex(pattern));
		List<SchoolInfo> list = mongoTemplate.find(query, SchoolInfo.class);
        return list;
	}

	
}
