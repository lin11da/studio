package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioMember)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioMember implements Serializable {
    private static final long serialVersionUID = -26959012325810961L;
    
    private Integer id;
    
    private String userId;
    
    private String memberId;
    /**
    * 成员名字
    */
    private String memberName;
    /**
    * 成员图片
    */
    private String memberImgUrl;
    /**
    * 成员简介
    */
    private String memberAbout;
    /**
    * 时间线
    */
    private String timeLine;
    
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberImgUrl() {
        return memberImgUrl;
    }

    public void setMemberImgUrl(String memberImgUrl) {
        this.memberImgUrl = memberImgUrl;
    }

    public String getMemberAbout() {
        return memberAbout;
    }

    public void setMemberAbout(String memberAbout) {
        this.memberAbout = memberAbout;
    }

    public String getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(String timeLine) {
        this.timeLine = timeLine;
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