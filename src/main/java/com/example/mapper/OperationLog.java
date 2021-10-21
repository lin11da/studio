package com.example.mapper;

import com.example.pojo.other.Operation_Log.Operation_Log_Up;
import com.example.pojo.other.Operation_Log.RootOperLogAspect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface OperationLog {

    /**
     * 操作日志
     * @param userid
     * @param operModul
     * @param operDesc
     * @param operType
     * @param operUrl
     * @param operationip
     * @param operationdate
     * @param result
     * @param errormsg
     * @param operationtime
     * @param updatetime
     * @param createtime
     * @return
     */
    Integer insertOperationLog(String userid,String operModul,String operDesc,String operType,
                               String operUrl,String operationip,String operationdate,String result,String errormsg,String operationtime,String updatetime,String createtime);


    /**
     * 查看操作日志
     * @return
     */
    List<Operation_Log_Up> select_Operation_Log(Map<String,Integer> map);
}
