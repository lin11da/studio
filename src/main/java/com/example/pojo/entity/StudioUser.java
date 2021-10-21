package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioUser)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioUser implements Serializable {
    private static final long serialVersionUID = -80274015075476640L;
    
    private Integer id;
    
    private String userId;
    /**
    * 头像url
    */
    private String img;
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
    * 性别
    */
    private String gender;
    /**
    * 生日
    */
    private String birthday;
    /**
    * 个人简介
    */
    private String about;
    /**
    * 角色
    */
    private String role;
    /**
    * 是否禁用
    */
    private String disable;
    /**
    * 是否在线
    */
    private Integer online;
    /**
    * 是否删除
    */
    private Integer delete;
    
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDisable() {
        return disable;
    }

    public void setDisable(String disable) {
        this.disable = disable;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
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