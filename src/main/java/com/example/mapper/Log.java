package com.example.mapper;

import com.example.pojo.entity.StudioUserLoginlog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Log {
    /**
     * 登录日志
     * @param userid
     * @param loginip
     * @param loginaddress
     * @param logintime
     * @param updatetime
     * @param createtime
     * @return
     */
    Integer addloginlog(String logid,String userid,String loginip,String loginaddress,String logintime,String updatetime,String createtime);

    /**
     * 查找管理员登录日志
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<StudioUserLoginlog> selectadminloginlog(int pageStart, int pageSize);

    /**
     * 查找管理员登录日志  by userid
     * @param pageStart
     * @param pageSize
     * @param userid
     * @return
     */
    List<StudioUserLoginlog> selectadminloginlogbyuserid(int pageStart, int pageSize, String userid);

    /**
     * 删除记录
     * @param logid
     * @return
     */
    Integer deleteloginlog(String logid);

}
