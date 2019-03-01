package com.lyx.houtai.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.io.Serializable;

public class BaseMongoModel implements Serializable {

	private static final long serialVersionUID = -2946056847406492795L;

	@Id
    protected String id;

    @Version
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
