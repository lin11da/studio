package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioOneroute)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:30
 */
public class StudioOneroute implements Serializable {
    private static final long serialVersionUID = 898536758980387652L;
    
    private Integer id;
    
    private String userId;
    /**
    * 路由id
    */
    private String routeId;
    /**
    * 路由名称
    */
    private String routeName;
    /**
    * 路由的url
    */
    private String routeUrl;
    
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

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
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