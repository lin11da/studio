package com.example.mapper;

import com.example.pojo.entity.StudioPaper;
import com.example.pojo.other.TsudioPaperOther;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Paper {

    /**
     * 用户添加文章
     * @param user_id
     * @param paper_id
     * @param paper_title
     * @param paper_about
     * @param paper_img
     * @param paperurl
     * @param paper_tag
     * @param paper_module
     * @param update_time
     * @param create_time
     * @return
     */
    Integer insertpaper(String user_id,String paper_id,String paper_title,String paper_about, String paper_img,
                        String paperurl,String paper_tag,String paper_module,String update_time,String create_time);


    /**
     * 查看所有文章
     * @return
     */
    List<TsudioPaperOther> selectpaper(Integer startSize,Integer pageSize);


    /**
     * 更新文章
     * @param paper_id
     * @param paper_title
     * @param paper_about
     * @param paper_img
     * @param paperurl
     * @param paper_tag
     * @param paper_module
     * @param update_time
     * @return
     */
    Integer updatepaper(String paper_id,String paper_title,String paper_about, String paper_img,
                        String paperurl,String paper_tag,String paper_module,String update_time);


    /**
     * 逻辑删除文章
     * @param paper_id
     * @return
     */
    Integer logicdelectpaper(String paper_id);

    /**
     * 用paperid进行userid的查询
     * @param paper_id
     * @return
     */
    StudioPaper paperidgetuserid(String paper_id);


    /**
     * 根据title查看所有文章
     * @param paper_title
     * @param startSize
     * @param pageSize
     * @return
     */
    List<TsudioPaperOther> selectpaperbytitle(String paper_title,Integer startSize,Integer pageSize );
}
