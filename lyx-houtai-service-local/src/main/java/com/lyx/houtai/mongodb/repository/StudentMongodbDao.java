package com.lyx.houtai.mongodb.repository;

import com.alibaba.fastjson.JSON;
import com.lyx.houtai.facade.StudentPageParam;
import com.lyx.houtai.mongodb.model.StudentInfo;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @ClassName: StudentMongodbDao
 * @Author: shenyafei
 * @Date: 2020/8/31
 * @Desc
 **/
@Component
@Slf4j
public class StudentMongodbDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public String add(@NonNull StudentInfo studentInfo) {
        if (StringUtils.isEmpty(studentInfo.getId())) {
            studentInfo.setId(UUID.randomUUID().toString());
        }
        if(studentInfo.getCreateDate() == null){
            studentInfo.setCreateDate(new Date());
        }
        if(studentInfo.getUpdateDate() == null){
            studentInfo.setUpdateDate(new Date());
        }
        log.info("studentInfo={}", JSON.toJSONString(studentInfo));
        mongoTemplate.insert(studentInfo);
        return studentInfo.getId();
    }

    /***
     * 分页查询学生列表数据
     * @param studentPageParam
     * @return
     */
    public List<StudentInfo> queryPageList(StudentPageParam studentPageParam){
        Query query = new Query();
        if (StringUtils.isNotEmpty(studentPageParam.getStudentName())) {
            Pattern pattern = compile("^.*"+studentPageParam.getStudentName()+".*$", Pattern.CASE_INSENSITIVE);
            query.addCriteria(Criteria.where("studentName").regex(pattern));
        }
        if (studentPageParam.getStatus() != null) {
            query.addCriteria(Criteria.where("status").is(studentPageParam.getStatus()));
        }
        if (studentPageParam.getAge() != null) {
            query.addCriteria(Criteria.where("age").is(studentPageParam.getAge()));
        }
        if (!CollectionUtils.isEmpty(studentPageParam.getClassCodeList())) {
            query.addCriteria(Criteria.where("classCode").in(studentPageParam.getClassCodeList()));
        }
        if (!CollectionUtils.isEmpty(studentPageParam.getTeacherIdList())) {
            query.addCriteria(Criteria.where("classCode").in(studentPageParam.getTeacherIdList()));
        }
        if (CollectionUtils.isEmpty(studentPageParam.getSortList())) {
            List<Sort.Order> sortList = new ArrayList<>();
            sortList.add(new Sort.Order(Sort.Direction.DESC,"createDate"));
            query.with(Sort.by(sortList));
        } else {
            List<Sort.Order> sortList = new ArrayList<>();
            for (String[] arr : studentPageParam.getSortList()) {
                if(arr[1].toUpperCase().equals("DESC")){
                    sortList.add(new Sort.Order(Sort.Direction.DESC,arr[0]));
                } else {
                    sortList.add(new Sort.Order(Sort.Direction.ASC,arr[0]));
                }
            }
            query.with(Sort.by(sortList));
        }
        query.skip((studentPageParam.getPageNum()-1)* studentPageParam.getPageSize());
        query.limit(studentPageParam.getPageSize());
        List<StudentInfo> list = mongoTemplate.find(query,StudentInfo.class);
        return list;
    }
}
