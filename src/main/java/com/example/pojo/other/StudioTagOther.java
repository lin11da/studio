package com.example.pojo.other;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/18 15:02
 */
public class StudioTagOther {
    /**
     * 标签id
     */
    private String[] tagId;
    /**
     * 标签名称
     */
    private String tagName;

    private String updateTime;

    private String createTime;

    public String[] getTagId() {
        return tagId;
    }

    public void setTagId(String[] tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
