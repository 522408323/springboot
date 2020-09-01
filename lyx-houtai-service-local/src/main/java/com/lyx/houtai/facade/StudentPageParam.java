package com.lyx.houtai.facade;

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
@Data
public class StudentPageParam implements Serializable {

    private String studentName;

    private Integer age;

    private List<Integer> classCodeList;

    private List<Long> teacherIdList;

    private Integer status;

    @NotNull(message = "分页条件不能为空")
    private Integer pageNum;
    @NotNull(message = "分页条件不能为空")
    private Integer pageSize;

    /***
     * 排序 例如：[{"createDate","desc"}]
     */
    private List<String[]> sortList;

}
