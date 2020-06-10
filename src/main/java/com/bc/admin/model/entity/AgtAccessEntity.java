package com.bc.admin.model.entity;

import org.apache.ibatis.type.Alias;

@Alias("AgtAccessEntity")
public class AgtAccessEntity {

    private Long id;

    private String agtNo;

    private String agtPhone;
    
    private String agtName;

    private String appId;

    private String appKey;
    
    private Integer keyLength;

    private Integer appType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgtNo() {
        return agtNo;
    }

    public void setAgtNo(String agtNo) {
        this.agtNo = agtNo;
    }

    public String getAgtPhone() {
        return agtPhone;
    }

    public void setAgtPhone(String agtPhone) {
        this.agtPhone = agtPhone;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getKeyLength() {
        return keyLength;
    }

    public void setKeyLength(Integer keyLength) {
        this.keyLength = keyLength;
    }

	public String getAgtName() {
		return agtName;
	}

	public void setAgtName(String agtName) {
		this.agtName = agtName;
	}

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
