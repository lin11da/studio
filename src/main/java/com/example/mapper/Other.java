package com.example.mapper;

import com.example.pojo.entity.*;
import com.example.pojo.other.TsudioPaperOther;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface Other {
    /**
     * 添加页脚
     * @param head
     * @param number
     * @param email
     * @param address
     * @param copyright
     * @param update_time
     * @param create_time
     * @return
     */
    Integer foothermanage(String footerid,String head, String number,String email, String address,String copyright,String update_time,String create_time);

    /**
     * 查看页脚信息
     * @return
     */
    StudioFooter selectfooter();

    /**
     * 更新页脚
     * @param footerid
     * @param head
     * @param number
     * @param email
     * @param address
     * @param copyright
     * @param updatetime
     * @return
     */
    Integer updatefooter(String footerid,String head,String number,String email,String address,String copyright,String updatetime);

    /**
     * 删除页脚
     * @param footerid
     * @return
     */
    Integer deletefooter(String footerid);

    /**
     * ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     */


    /**
     * 查询公告
     * @return
     */
    StudioBulletinBoard selectannouncement();

    /**
     * 更新公告
     * @param announcement_id
     * @param announcement
     * @param title
     * @param update_time
     * @return
     */
    Integer updateannouncement(String announcement_id,String announcement,String title,String update_time);

    /**
     * 添加公告
     * @param announcement_id
     * @param announcement
     * @param title
     * @param update_time
     * @param create_time
     * @return
     */
    Integer insertannouncement(String announcement_id, String announcement,String title,String update_time,String create_time);

    /**
     * 删除公告
     * @param announcement_id
     * @return
     */
    Integer deleteannouncement(String announcement_id);

    /**
     * =============================================================================================================================
     */

    /**
     * 添加荣誉墙内容
     * @param honor_id
     * @param honor
     * @param honor_img_url
     * @param playing_time
     * @param compete
     * @param project
     * @param update_time
     * @param create_time
     * @return
     */
    Integer inserthonorwall(String honor_id,String honor,String honor_img_url,String playing_time,String compete,String project, String update_time, String create_time);


    /**
     * 查看荣誉墙
     * @return
     */
    List<StudioHonorWall> selecthonorwall();

    /**
     * 更新荣誉
     * @param honor_id
     * @param honor
     * @param honor_img_url
     * @param playing_time
     * @param compete
     * @param project
     * @param update_time
     * @return
     */
    Integer updatehonorwall(String honor_id,String honor,String honor_img_url,String playing_time,String compete,String project, String update_time);

    /**
     * 删除某个荣誉
     * @param honor_id
     * @return
     */
    Integer deletehonorwall(String honor_id);

    /**
     * ==============================================================================================================================
     */


    /**
     * 添加成员介绍
     * @param member_id
     * @param member_name
     * @param member_img_url
     * @param member_about
     * @param time_line
     * @param update_time
     * @param create_time
     * @return
     */
    Integer insertmembers(String member_id,String member_name,String member_img_url,String member_about,String time_line,String update_time,String create_time);

    /**
     * 查看成员介绍
     * @return
     */
    List<StudioMember> selectmembers();

    /**
     * 更改某个成员介绍
     * @param member_id
     * @param member_name
     * @param member_img_url
     * @param member_about
     * @param time_line
     * @param update_time
     * @return
     */
    Integer updatemembers(String member_id,String member_name,String member_img_url,String member_about,String time_line,String update_time);


    /**
     * 删除某个或几个成员介绍
     * @param member_id
     * @return
     */
    Integer deletemembers(String member_id);

    /**
     * ============================================================================================================================================
     */

    /**
     * 添加网站奉献者信息
     * @param dedicator_id
     * @param dedicator_name
     * @param dedicator_img_url
     * @param dedicator_about
     * @param timeline
     * @param update_time
     * @param create_time
     * @return
     */
    Integer insertdedicator(String dedicator_id,String dedicator_name,String dedicator_img_url, String dedicator_about,String timeline,String update_time,String create_time);

    /**
     * 查看网站奉献者信息
     * @return
     */
    List<StudioWebDedicator> selectdedicator();

    /**
     * 更改网站奉献者信息
     * @param dedicator_id
     * @param dedicator_name
     * @param dedicator_img_url
     * @param dedicator_about
     * @param timeline
     * @param update_time
     * @return
     */
    Integer updatededicator(String dedicator_id,String dedicator_name,String dedicator_img_url,String dedicator_about,String timeline,String update_time);

    /**
     * 删除网站奉献者信息
     * @param dedicator_id
     * @return
     */
    Integer deletededicator(String dedicator_id);

    /**
     * =======================================================================================================================================
     */


    /**
     * 添加模块
     * @param module_id
     * @param module_name
     * @param update_time
     * @param create_time
     * @return
     */
    Integer insertmodule(String module_id,String module_name,String update_time,String create_time);

    /**
     * 查看模块
     * @return
     */
    List<StudioModule> selectmodule();

    /**
     * 删除模块
     * @param module_id
     * @return
     */
    Integer deletemodule(String module_id);

    /**
     * ======================================================================================================================================================
     */

    /**
     * 添加标签
     * @param tag_id
     * @param tag_name
     * @param update_time
     * @param create_time
     * @return
     */
    Integer inserttag(String tag_id,String tag_name,String update_time,String create_time);


    /**
     * 查看标签
     * @return
     */
    List<StudioTag> selecttag();

    /**
     * 删除标签
     * @param tag_id
     * @return
     */
    Integer deletetag(String tag_id);


}
