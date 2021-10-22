package com.example.server.Other;

import com.example.mapper.NavigationOperation;
import com.example.mapper.RootLogin;
import com.example.mapper.SuperManage;
import com.example.pojo.other.nav.NavO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * TODO
 *
 * @author chen
 * @version 1.0
 * @date 2021/9/22 22:03
 */
@Service
public class RoletoList {
    @Autowired
    RootLogin rootLogin;

    @Autowired
    SuperManage superManage;

    @Autowired
    NavigationOperation navigationOperation;


    public Object rootlogin(String oneid,String twoid){
        List<String> oneid_list=null;
        List<String> twoid_list = null;

        //尝试解析oneid_list
        try {
            //Sting转List
            oneid_list = Arrays.asList(oneid.split(",")); //[a, b, c]
            Map<String, Object> listmap = new LinkedHashMap<>();
            List<Object> listmap2 = new ArrayList<>();
            for (String s : oneid_list) {

                NavO navO_one = navigationOperation.select_char_one_nav(s);
                String one_label = navO_one.getRouteName();
                String url = navO_one.getRouteUrl();
                String oneid_mysql1 = navO_one.getRouteId();

                twoid_list = Arrays.asList(twoid.split(",")); //[a, b, c]
                List<NavO> list = new LinkedList<>();

                Map<String, Object> map = new HashMap<>();
                map.put("label",one_label);
                map.put("url",url);
                map.put("id",oneid_mysql1);
                map.put("icon",navO_one.getIcon());

                for (String s1 : twoid_list) {
                    NavO navO = navigationOperation.select_char_two_nav(s1);
                    String oneid_mysql = navO.getOnerouteId();
                    if (s.equals(oneid_mysql)){
                        list.add(navO);
                        map.put("children",list);

                    }
                }

                listmap2.add(map);
            }
            return listmap2;
        } catch (Exception e) {
            //do nothing
        }

        return null;
    }



}
