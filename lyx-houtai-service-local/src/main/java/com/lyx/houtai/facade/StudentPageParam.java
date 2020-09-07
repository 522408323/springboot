package com.lyx.houtai.facade;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: StudentPageParam
 * @Author: shenyafei
 * @Date: 2020/9/1
 * @Desc
 **/
@ApiModel(description = "学生分页查询请参")
@Data
public class StudentPageParam implements Serializable {
    @ApiModelProperty(value="学生", example = "1", allowEmptyValue = true)
    private String studentName;
    @ApiModelProperty(value="年龄", example = "1", allowEmptyValue = true)
    private Integer age;
    @ApiModelProperty(value="年级集合", example = "1", allowEmptyValue = true)
    private List<Integer> classCodeList;
    @ApiModelProperty(value="教师集合", example = "1", allowEmptyValue = true)
    private List<Long> teacherIdList;
    @ApiModelProperty(value="学生状态", example = "1", allowEmptyValue = true)
    private Integer status;
    @ApiModelProperty(value="分页条件", example = "1", allowEmptyValue = true)
    @NotNull(message = "分页条件不能为空")
    private Integer pageNum;
    @ApiModelProperty(value="分页条件", example = "1", allowEmptyValue = true)
    @NotNull(message = "分页条件不能为空")
    private Integer pageSize;

    /***
     * 排序 例如：[{"createDate","desc"}]
     */
    @ApiModelProperty(value="排序", example = "", allowEmptyValue = true)
    private List<String[]> sortList;

}
