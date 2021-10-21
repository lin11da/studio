package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioFooter)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioFooter implements Serializable {
    private static final long serialVersionUID = 844298575268433701L;
    
    private Integer id;

    private String footerid;
    
    private String head;
    /**
    * 电话
    */
    private String number;
    /**
    * 邮箱
    */
    private String email;
    /**
    * 地址
    */
    private String address;
    /**
    * 版权
    */
    private String copyright;
    
    private String updateTime;
    
    private String createTime;

    public String getFooterid() {
        return footerid;
    }

    public void setFooterid(String footerid) {
        this.footerid = footerid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
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