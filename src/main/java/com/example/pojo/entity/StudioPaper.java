package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioPaper)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioPaper implements Serializable {
    private static final long serialVersionUID = -23987357337600092L;
    
    private Integer id;
    
    private String userId;
    /**
    * 文章id
    */
    private String paperId;
    /**
    * 文章标题
    */
    private String paperTitle;
    /**
    * 文章摘要
    */
    private String paperAbout;
    /**
    * 文章配图
    */
    private String paperImg;
    /**
    * 文章配图url
    */
    private String paperurl;
    /**
    * 文章标签
    */
    private String paperTag;
    /**
    * 文章模块
    */
    private String paperModule;
    
    private String updateTime;
    
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperAbout() {
        return paperAbout;
    }

    public void setPaperAbout(String paperAbout) {
        this.paperAbout = paperAbout;
    }

    public String getPaperImg() {
        return paperImg;
    }

    public void setPaperImg(String paperImg) {
        this.paperImg = paperImg;
    }

    public String getPaperImgId() {
        return paperurl;
    }

    public void setPaperImgId(String paperImgId) {
        this.paperurl = paperImgId;
    }

    public String getPaperTag() {
        return paperTag;
    }

    public void setPaperTag(String paperTag) {
        this.paperTag = paperTag;
    }

    public String getPaperModule() {
        return paperModule;
    }

    public void setPaperModule(String paperModule) {
        this.paperModule = paperModule;
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