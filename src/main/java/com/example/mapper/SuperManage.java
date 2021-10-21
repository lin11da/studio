package com.example.mapper;

import com.example.pojo.entity.StudioOneroute;
import com.example.pojo.entity.StudioTworoute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/22 21:04
 */
@Repository
@Mapper
public interface SuperManage {

    /**
     * 超级管理员插入一级权限
     * @param route_id
     * @param route_name
     * @param route_url
     * @param update_time
     * @param create_time
     * @return
     */
    Integer insertrole(String route_id,String route_name,String route_url,String update_time,String create_time);

    /**
     * 查看是否存在一级权限
     * @param route_id
     * @return
     */
    StudioOneroute selectonerole(String route_id);


    /**
     * 超级管理员插入二级权限
     * @param one_route_id
     * @param route_id
     * @param route_name
     * @param route_url
     * @param update_time
     * @param create_time
     * @return
     */
    Integer inserttworole(String one_route_id,String route_id,String route_name,String route_url,String update_time,String create_time);


    /**
     * 超级管理员给普通管理员赋予权限
     * @param one_route
     * @param two_route
     * @param updateTime
     * @param user_id
     * @return
     */
    Integer setpermission(String one_route,String two_route,String updateTime,String user_id);


    /**
     * 查询一级权限的各种信息  url name
     * @param route_id
     * @return
     */
    StudioOneroute selectoneroledate(String route_id);

    /**
     * 查询二级权限的各种信息  url name
     * @param route_id
     * @return
     */
    StudioTworoute selecttworoledate(String route_id);
}
