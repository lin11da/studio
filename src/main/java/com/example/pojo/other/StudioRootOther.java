package com.example.pojo.other;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/15 17:47
 */
public class StudioRootOther {
    private Integer id;

    private String[] userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户号码
     */
    private String usernumber;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 角色
     */
    private String role;
    /**
     * 一级路由
     */
    private String oneRoute;
    /**
     * 二级路由
     */
    private String twoRoute;
    /**
     * 是否禁用
     */
    private String disable;

    private String updateTime;

    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String[] getUserId() {
        return userId;
    }

    public void setUserId(String[] userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOneRoute() {
        return oneRoute;
    }

    public void setOneRoute(String oneRoute) {
        this.oneRoute = oneRoute;
    }

    public String getTwoRoute() {
        return twoRoute;
    }

    public void setTwoRoute(String twoRoute) {
        this.twoRoute = twoRoute;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
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
