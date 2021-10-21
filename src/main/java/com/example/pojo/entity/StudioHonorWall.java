package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioHonorWall)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioHonorWall implements Serializable {
    private static final long serialVersionUID = -13468958773363399L;
    
    private Integer id;
    
    private String honorId;
    /**
    * 荣誉
    */
    private String honor;
    /**
    * 荣誉图片url
    */
    private String honorImgUrl;
    /**
    * 比赛时间
    */
    private String playingTime;
    /**
    * 比赛名称
    */
    private String compete;
    /**
    * 比赛项目
    */
    private String project;
    
    private String updateTime;
    
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHonorId() {
        return honorId;
    }

    public void setHonorId(String honorId) {
        this.honorId = honorId;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getHonorImgUrl() {
        return honorImgUrl;
    }

    public void setHonorImgUrl(String honorImgUrl) {
        this.honorImgUrl = honorImgUrl;
    }

    public String getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(String playingTime) {
        this.playingTime = playingTime;
    }

    public String getCompete() {
        return compete;
    }

    public void setCompete(String compete) {
        this.compete = compete;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
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