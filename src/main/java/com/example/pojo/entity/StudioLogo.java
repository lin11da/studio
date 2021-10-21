package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioLogo)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioLogo implements Serializable {
    private static final long serialVersionUID = 407184185521455608L;
    
    private Integer id;
    
    private String logoId;
    
    private String logoUrl;
    
    private String updateTime;
    
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogoId() {
        return logoId;
    }

    public void setLogoId(String logoId) {
        this.logoId = logoId;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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