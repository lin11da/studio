package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioBulletinBoard)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioBulletinBoard implements Serializable {
    private static final long serialVersionUID = -57273648307487957L;
    
    private Integer id;

    private String title;
    
    private String announcementId;
    /**
    * 公告
    */
    private String announcement;
    
    private String updateTime;
    
    private String createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
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