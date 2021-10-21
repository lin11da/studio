package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioAdminHomePageImg)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioAdminHomePageImg implements Serializable {
    private static final long serialVersionUID = 903573555656932876L;
    
    private Integer id;
    /**
    * 图片id
    */
    private String imgId;
    /**
    * 图片url
    */
    private String imgUrl;
    
    private String updateTime;
    
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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