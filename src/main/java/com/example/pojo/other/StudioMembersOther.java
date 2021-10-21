package com.example.pojo.other;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/17 21:16
 */
public class StudioMembersOther {
    private Integer id;


    private String[] memberId;
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

    public String[] getMemberId() {
        return memberId;
    }

    public void setMemberId(String[] memberId) {
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
