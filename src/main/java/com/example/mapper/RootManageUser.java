package com.example.mapper;

import com.example.pojo.entity.StudioUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RootManageUser {
    /**
     * 查看所有没有被删除的学生
     * @return
     */
    List<StudioUser> selectalluser();

    /**
     * 根据手机号码查询用户
     * @param usernumber
     * @return
     */
    List<StudioUser> selectuserbynumber(String usernumber);

    /**
     * 更改用户信息
     * @param password
     * @param usernumber
     * @param username
     * @param update_time
     * @param userid
     * @return
     */
    Integer updateuserdate(String password,String usernumber,String username,String update_time,String userid);

    /**
     * 修改用户权限
     * @param userid
     * @param role
     * @return
     */
    Integer updateusermanage(String userid,String role);
}
