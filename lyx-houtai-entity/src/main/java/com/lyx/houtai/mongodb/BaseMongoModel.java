package com.lyx.houtai.mongodb;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseMongoModel implements Serializable {

	private static final long serialVersionUID = -2946056847406492795L;

    protected String id;

    @Version
    private int version;

    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
}
