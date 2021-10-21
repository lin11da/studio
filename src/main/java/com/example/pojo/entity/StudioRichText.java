package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioRichText)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioRichText implements Serializable {
    private static final long serialVersionUID = -42631595117436043L;
    
    private Integer id;
    /**
    * 区域id
    */
    private String areaId;
    /**
    * 区域名称
    */
    private String areaName;
    /**
    * 区域url
    */
    private String areaUrl;
    
    private String updateTime;
    
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaUrl() {
        return areaUrl;
    }

    public void setAreaUrl(String areaUrl) {
        this.areaUrl = areaUrl;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}