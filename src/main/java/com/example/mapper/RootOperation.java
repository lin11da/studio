package com.example.mapper;

import com.example.pojo.entity.StudioRoot;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface RootOperation {
    /**
     * 查询全部管理员
     * @return
     */
    List<StudioRoot> allroot();

    /**
     * 修改管理员信息
     * @param usernumber
     * @param username
     * @param password
     * @param updatetime
     * @param userId
     * @return
     */
    Integer updateroot(String usernumber,String username,String password,String updatetime,String userId);

    /**
     *  删除管理员
     * @return
     */
    Integer delectroot(String userId);
}
