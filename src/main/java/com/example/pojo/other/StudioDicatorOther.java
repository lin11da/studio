package com.example.pojo.other;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/17 21:46
 */
public class StudioDicatorOther {
    /**
     * 奉献者id
     */
    private String[] dedicatorId;
    /**
     * 奉献者名称
     */
    private String dedicatorName;

    /**
     * 奉献者图片url
     */
    private String dedicatorImgUrl;
    /**
     * 奉献介绍
     */
    private String dedicatorAbout;
    /**
     * 奉献时间线
     */
    private String timeline;

    private String updateTime;

    private String createTime;

    public String[] getDedicatorId() {
        return dedicatorId;
    }

    public void setDedicatorId(String[] dedicatorId) {
        this.dedicatorId = dedicatorId;
    }

    public String getDedicatorName() {
        return dedicatorName;
    }

    public void setDedicatorName(String dedicatorName) {
        this.dedicatorName = dedicatorName;
    }

    public String getDedicatorImgUrl() {
        return dedicatorImgUrl;
    }

    public void setDedicatorImgUrl(String dedicatorImgUrl) {
        this.dedicatorImgUrl = dedicatorImgUrl;
    }

    public String getDedicatorAbout() {
        return dedicatorAbout;
    }

    public void setDedicatorAbout(String dedicatorAbout) {
        this.dedicatorAbout = dedicatorAbout;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
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
