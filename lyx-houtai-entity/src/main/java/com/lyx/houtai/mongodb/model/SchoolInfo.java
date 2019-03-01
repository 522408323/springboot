package com.lyx.houtai.mongodb.model;

import java.util.Date;

import com.lyx.houtai.mongodb.BaseMongoModel;



@SuppressWarnings("serial")
public class SchoolInfo extends BaseMongoModel{
	
   private Long schoolId;
   
   private String schoolName;
   
   private Integer status;
   
   private Integer type;
   
   private String areaCode;
   
   private String areaName;
   
   private String cityCode;
   
   private String cityName;
   
   private String provinceCode;
   
   private String provinceName;

	public Long getSchoolId() {
		return schoolId;
	}
	
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getAreaCode() {
		return areaCode;
	}
	
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public String getCityCode() {
		return cityCode;
	}
	
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}
	
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Override
	public String toString() {
		return "SchoolInfo [id=" + id + ", schoolId=" + schoolId + ", schoolName=" + schoolName + ", status=" + status + ", type="
				+ type + ", areaCode=" + areaCode + ", areaName=" + areaName + ", cityCode=" + cityCode + ", cityName="
				+ cityName + ", provinceCode=" + provinceCode + ", provinceName=" + provinceName + "]";
	}
	
   

}
