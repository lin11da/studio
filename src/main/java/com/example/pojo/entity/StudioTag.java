package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioTag)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioTag implements Serializable {
    private static final long serialVersionUID = -23639334978932428L;
    
    private Integer id;
    /**
    * 标签id
    */
    private String tagId;
    /**
    * 标签名称
    */
    private String tagName;

    private String updateTime;
    
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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