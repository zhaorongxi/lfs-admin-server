package com.lfs.admin.model.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhao6
 */
@Alias("ProductInfoEntity")
public class ProductInfoEntity implements Serializable {

    private Integer id;

    private Long productNum;

    private Long businessNum;

    private Long arriveType;

    private BigDecimal tradeFace;

    private BigDecimal price;

    private BigDecimal discount;

    private String upChannel;

    private String businessName;
    
    private Integer productType;
    
    private Integer specialType;

    private String unit;

    private Integer state;

    private String createTime;

    private String updateTime;

    private String productDetail;

    public Long getProductNum() {
        return productNum;
    }

    public void setProductNum(Long productNum) {
        this.productNum = productNum;
    }

    public Long getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(Long businessNum) {
        this.businessNum = businessNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getArriveType() {
        return arriveType;
    }

    public void setArriveType(Long arriveType) {
        this.arriveType = arriveType;
    }

    public BigDecimal getTradeFace() {
        return tradeFace;
    }

    public void setTradeFace(BigDecimal tradeFace) {
        this.tradeFace = tradeFace;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getUpChannel() {
        return upChannel;
    }

    public void setUpChannel(String upChannel) {
        this.upChannel = upChannel;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getSpecialType() {
		return specialType;
	}

	public void setSpecialType(Integer specialType) {
		this.specialType = specialType;
	}

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }
}
