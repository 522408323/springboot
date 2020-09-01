package com.lyx.houtai.mongodb.model;

import com.lyx.houtai.mongodb.BaseMongoModel;
import lombok.Data;


@Data
public class StudentInfo extends BaseMongoModel{

	/***
	 * 学生名称
	 */
	private String studentName;

	/***
	 * 学生头像
	 */
	private String headImg;
	/***
	 * 学生年龄
	 */
	private Integer age;
	/***
	 * 学生状态 1:正常 2：禁用
	 */
	private Integer status;

	/***
	 * 学生所属年级
	 */
	private Integer classCode;
	/***
	 * 学生所属老师id
	 */
	private Long teacherId;

	/***
	 * 学生家庭住址
	 */
	private String address;


}
