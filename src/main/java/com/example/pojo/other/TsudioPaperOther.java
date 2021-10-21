package com.example.pojo.other;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/18 20:32
 */
public class TsudioPaperOther {

    private String userId;
    /**
     * 文章id
     */
    private String paperId;
    /**
     * 文章标题
     */
    private String paperTitle;
    /**
     * 文章摘要
     */
    private String paperAbout;
    /**
     * 文章配图
     */
    private String paperImg;
    /**
     * 文章配图url
     */
    private String paperurl;
    /**
     * 文章标签
     */
    private String paperTag;
    /**
     * 文章模块
     */
    private String paperModule;

    private String updateTime;

    private String createTime;

    private String username;


    private Integer startSize;

    private Integer pageSize;

    private Integer pageNump;

    public Integer getStartSize() {
        return startSize;
    }

    public void setStartSize(Integer startSize) {
        this.startSize = startSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNump() {
        return pageNump;
    }

    public void setPageNump(Integer pageNump) {
        this.pageNump = pageNump;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperAbout() {
        return paperAbout;
    }

    public void setPaperAbout(String paperAbout) {
        this.paperAbout = paperAbout;
    }

    public String getPaperImg() {
        return paperImg;
    }

    public void setPaperImg(String paperImg) {
        this.paperImg = paperImg;
    }

    public String getPaperurl() {
        return paperurl;
    }

    public void setPaperurl(String paperurl) {
        this.paperurl = paperurl;
    }

    public String getPaperTag() {
        return paperTag;
    }

    public void setPaperTag(String paperTag) {
        this.paperTag = paperTag;
    }

    public String getPaperModule() {
        return paperModule;
    }

    public void setPaperModule(String paperModule) {
        this.paperModule = paperModule;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
