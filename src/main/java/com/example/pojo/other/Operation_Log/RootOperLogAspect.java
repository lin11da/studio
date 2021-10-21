package com.example.pojo.other.Operation_Log;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/10/16 14:33
 */
public class RootOperLogAspect {
    private Integer id;

    private String userid;

    private String openid;

    /**
     * 操作模块（admin/user）
     */
    private String operModul;

    /**
     * 操作说明（操作）
     */
    private String operDesc;

    /**
     * 操作的url
     */
    private String operUrl;

    /**
     * 操作类型
     */
    private String operType;

    /**
     * 错误信息
     */
    private String errormsg;

    /**
     * 操作输入地信息
     */
    private String operationdate;


    /**
     * 结果
     */
    private String result;

    private String operationip;

    private String operationtime;

    private String updatetime;

    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOperModul() {
        return operModul;
    }

    public void setOperModul(String operModul) {
        this.operModul = operModul;
    }

    public String getOperDesc() {
        return operDesc;
    }

    public void setOperDesc(String operDesc) {
        this.operDesc = operDesc;
    }

    public String getOperUrl() {
        return operUrl;
    }

    public void setOperUrl(String operUrl) {
        this.operUrl = operUrl;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(String operationdate) {
        this.operationdate = operationdate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOperationip() {
        return operationip;
    }

    public void setOperationip(String operationip) {
        this.operationip = operationip;
    }

    public String getOperationtime() {
        return operationtime;
    }

    public void setOperationtime(String operationtime) {
        this.operationtime = operationtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
