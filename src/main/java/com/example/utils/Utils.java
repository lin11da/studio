package com.example.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Utils {
    /**
     * 获取随机数
     * @param length
     * @return
     */
    public static String RandomStr(int length){
        String str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(72);
            stringBuffer.append(str.charAt(number));
        }
        return stringBuffer.toString();
    }


    public static String timeStr(long s){
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long lt = new Long(s);
            Date date = new Date(lt*1000);
            res = simpleDateFormat.format(date);
            return res;
    }

    /**
     * 判断是不是数字
     * @param number  手机号码
     * @return 是则返回true
     */
    public static Boolean pdsz(String number) {
        boolean isNum = number.matches("[0-9]+");
        return isNum;
    }

    /**
     * 当前时间戳
     * @return 当前时间戳
     */
    public static String time(){
        Instant timestamp = Instant.now();
        //返回当前时间戳
        return String.valueOf(timestamp.getEpochSecond());
    }

    /**
     * 判断输入的是否是整数或者一位小数
     * String humidity,String temperature,String illumination
     * @return
     */
    public static boolean pattern(String humidity,String temperature,String illumination){
        boolean matches0 = Pattern.matches("^[+]?([0-9]+(.[0-9]{1,1})?)$",humidity);
        boolean matches1 = Pattern.matches("^[+]?([0-9]+(.[0-9]{1,1})?)$",temperature);
        boolean matches2 = Pattern.matches("^[+]?([0-9]+(.[0-9]{1,1})?)$",illumination);
        if (matches0&&matches1&&matches2){
            return true;
        }else {
            return false;
        }
    }


    //计算长度
    public static boolean datalong(String data, int datalong){
        if (data.length()==datalong){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 拿到 : 后面的字符串 再拼接  输出到前端
     * @param strList
     * @return
     */
    public static String forfor(List<String> strList){
        List<String> list = new LinkedList<>();
        for (String s : strList) {
            int index=s.indexOf(":");
            String result=s.substring(index+1);
            list.add(result);
        }
        String stringauthority = String.join(",",list);
        return stringauthority;
    }

    public static String suffix(String[] strList){
        List<String> list = new LinkedList<>();
        for (String s : strList) {
            list.add("admin:"+s);
        }
        String stringauthority = String.join(",",list);
        return stringauthority;
    }

    /**
     * String(yyyy-MM-dd HH:mm:ss)转13位时间戳
     *
     * @param time String
     * @return Integer
     */
    public static String StringToTimestamp(String time) {

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("转时间戳失败");
        }
        System.out.println(date.getTime());
        return String.valueOf(date.getTime());
    }



//    /**
//     * 从实体类里拿出数据
//     * AutoDataVO(productkey=n19wsEZnTMt, deviceid=g8NPHo, data={"paramstype0":{"Temp":"36","Humi":"111"}})
//     * @param autoDataVO
//     * @return
//     */
//    public static Map<String,String> index(AutoDataVO autoDataVO){
//        Map<String,String > map = new HashMap<>();
//        String data = autoDataVO.getData();
//        String serialid = autoDataVO.getProductkey();
//        String deviceid = autoDataVO.getDeviceid();
//        //将String类型 转化为json类型
//        JSONObject json = JSONObject.parseObject(autoDataVO.getData());
//        //从json类型里拿出数据
//        String jsonString = json.getString("paramstype0");
//        JSONObject json2 = JSONObject.parseObject(jsonString);
//
//        String temp = json2.getString("Temp");
//        String humi = json2.getString("Humi");
//
//        map.put("serialid",serialid);
//        map.put("temp",temp);
//        map.put("humi",humi);
//
//        return map;
//
//    }

}
