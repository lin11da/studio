package com.example.mapper;

import com.example.pojo.entity.StudioAdminHomePageImg;
import com.example.pojo.entity.StudioLogo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Studioimg {
    /**
     * 查询logourl
     * @return
     */
    StudioLogo logourl();

    /**
     * 修改logourl
     * @param logoUrl
     * @param updateTime
     * @param logoId
     * @return
     */
    Integer updatelogourl(String logoUrl,String updateTime,String logoId);

    /**
     * 轮播图更改
     * @param imgUrl
     * @param updateTime
     * @param imgId
     * @return
     */
    Integer updatehomeurl(String imgUrl,String updateTime,String imgId);


    List<StudioAdminHomePageImg> selecthomeurl();
}
