package com.example.pojo.other.Operation_Log;

import java.io.Serializable;

/**
 * (StudioWorks)实体类
 *
 * @author makejava
 * @since 2021-10-21 22:57:08
 */
public class StudioWorks implements Serializable {
    private static final long serialVersionUID = -49125103625280541L;

    
    private String workid;
    
    private String workName;
    
    private String workAbout;
    
    private String workTime;
    
    private String workHtml;
    
    private String workImg;
    
    private String updatetime;
    
    private String createtime;




    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkAbout() {
        return workAbout;
    }

    public void setWorkAbout(String workAbout) {
        this.workAbout = workAbout;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkHtml() {
        return workHtml;
    }

    public void setWorkHtml(String workHtml) {
        this.workHtml = workHtml;
    }

    public String getWorkImg() {
        return workImg;
    }

    public void setWorkImg(String workImg) {
        this.workImg = workImg;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

}