package com.example.pojo.other;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/22 21:21
 */
public class StudioRole {

    private String userId;
    /**
     * 路由id
     */
    private String[] onerouteId;

    private String[] tworouteId;


    private String updateTime;

    private String createTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String[] getOnerouteId() {
        return onerouteId;
    }

    public void setOnerouteId(String[] onerouteId) {
        this.onerouteId = onerouteId;
    }

    public String[] getTworouteId() {
        return tworouteId;
    }

    public void setTworouteId(String[] tworouteId) {
        this.tworouteId = tworouteId;
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
