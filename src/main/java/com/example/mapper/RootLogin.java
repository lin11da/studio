package com.example.mapper;

import com.example.pojo.entity.StudioRoot;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RootLogin {
    /**
     * 管理员登录
     * @param usernumber
     * @param password
     * @return
     */
    StudioRoot rootlogin(String usernumber, String password);

    /**
     *
     * @param usernumber
     * @return
     */
    StudioRoot getrole(String usernumber);

    /**
     * 添加管理员
     * @param user_id
     * @param username
     * @param usernumber
     * @param password
     * @param role
     * @param update_time
     * @param create_time
     * @return
     */
    Integer registered(String user_id,String username, String usernumber, String password, String role, String update_time, String create_time);

    /**
     * 根据user_id查询信息
     * @param user_id
     * @return
     */
    StudioRoot getrolebyuserid(String user_id);
}
