package com.lfs.admin.model.vo;

import com.lfs.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhao6
 */
public class ProductGroupVo extends BaseEntity implements Serializable {

    private Integer id;

    private String groupNum;

    private String groupName;

    private Integer groupState;

    public String getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(String groupNum) {
        this.groupNum = groupNum;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupState() {
        return groupState;
    }

    public void setGroupState(Integer groupState) {
        this.groupState = groupState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
