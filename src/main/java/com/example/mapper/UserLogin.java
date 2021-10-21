package com.example.mapper;

import com.example.pojo.entity.StudioUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserLogin {

    /**
     * 用户登录
     * @param usernumber
     * @param password
     * @return
     */
    StudioUser userlogin(String usernumber,String password);

    /**
     * 根据number进行查找信息
     * @param usernumber
     * @return
     */
    StudioUser getrole(String usernumber);

    /**
     * 根据userid查询信息
     * @param user_id
     * @return
     */
    StudioUser getrole_by_userid(String user_id);

    /**
     * 注册用户
     * @param user_id
     * @param username
     * @param usernumber
     * @param password
     * @param update_time
     * @param create_time
     * @return
     */
    Integer registereduser(String user_id,String username,String usernumber,String password,String update_time,String create_time);


    /**
     * 修改用户信息
     * @param username
     * @param usernumber
     * @param gender
     * @param adbout
     * @param birthday
     * @param img
     * @param update_time
     * @return
     */
    Integer modify_user_information(String username,String usernumber,String gender,String adbout,String birthday ,String img,String update_time );
}
