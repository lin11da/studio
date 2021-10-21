package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioTworoute)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioTworoute implements Serializable {
    private static final long serialVersionUID = -44126363058061743L;
    
    private Integer id;


    private String oneRouteId;
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

    public String getOneRouteId() {
        return oneRouteId;
    }

    public void setOneRouteId(String oneRouteId) {
        this.oneRouteId = oneRouteId;
    }
}