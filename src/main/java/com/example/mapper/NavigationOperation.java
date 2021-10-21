package com.example.mapper;


import com.example.pojo.other.nav.NavO;
import com.example.pojo.other.nav.NavO_Root;
import com.example.pojo.other.nav.NavO_two;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NavigationOperation {
    /**
     * 查看一级导航
     * @return
     */
    List<NavO_Root> select_nav_one();

    /**
     * 查看二级导航
     * @return
     */
    List<NavO_two> select_nav_two(String one_route_id);


    /**
     * 根据twoid查询该导航是否存在
     */
    NavO select_char_two_nav(String route_id);

    /**
     * 根据oneid查询该导航是否存在
     * @param route_id
     * @return
     */
    NavO select_char_one_nav(String route_id);

}
