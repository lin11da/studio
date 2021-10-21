package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (CarRootLoginlog)实体类
 *
 * @author makejava
 * @since 2021-09-07 20:39:46
 */

public class StudioUserLoginlog implements Serializable {
    private static final long serialVersionUID = 460303197196735553L;
    
    private Integer id;

    private String logid;
    
    private String userid;
    
    private String openid;
    
    private String loginip;
    
    private String loginaddress;
    
    private String logintime;
    private String updatetime;
    
    private String createtime;
    private int pageStart;
    private int pageSize;
    private int pageNumber;


    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getLoginaddress() {
        return loginaddress;
    }

    public void setLoginaddress(String loginaddress) {
        this.loginaddress = loginaddress;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
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

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}