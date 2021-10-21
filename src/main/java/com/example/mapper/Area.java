package com.example.mapper;

import com.example.pojo.entity.StudioRichText;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Area {
    /**
     * 查询全部导航条
     * @return
     */
    List<StudioRichText> allarea();

    /**
     * 修改导航条信息
     * @param areaUrl
     * @param updatetime
     * @param areaId
     * @return
     */
    Integer updatearea(String areaUrl,String updatetime,String areaId);
}
