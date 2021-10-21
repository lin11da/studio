package com.example.pojo.entity;

import java.io.Serializable;

/**
 * (StudioModule)实体类
 *
 * @author makejava
 * @since 2021-09-15 17:13:33
 */
public class StudioModule implements Serializable {
    private static final long serialVersionUID = 865929576728057535L;
    
    private Integer id;
    
    private String moduleId;
    
    private String moduleName;
    
    private String updateTime;
    
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
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