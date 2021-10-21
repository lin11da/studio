package com.example.pojo.other;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/22 21:32
 */
public class StudioinsertTwoRole {
    private String userId;
    /**
     * 路由id
     */
    private String oneroute;

    private String tworoute;

    private String routeName;

    private String routeUrl;


    private String updateTime;

    private String createTime;

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOneroute() {
        return oneroute;
    }

    public void setOneroute(String oneroute) {
        this.oneroute = oneroute;
    }

    public String getTworoute() {
        return tworoute;
    }

    public void setTworoute(String tworoute) {
        this.tworoute = tworoute;
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

    public String getRouteUrl() {
        return routeUrl;
    }

    public void setRouteUrl(String routeUrl) {
        this.routeUrl = routeUrl;
    }
}
