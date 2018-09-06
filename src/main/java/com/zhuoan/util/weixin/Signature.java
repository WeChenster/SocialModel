package com.zhuoan.util.weixin;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 15:23
 */
public class Signature {
    /**
     * 签名算法
     * @param o 要参与签名的数据对象
     * @return 签名
     * @throws IllegalAccessException
     */
    public static String getSign(Object o) throws IllegalAccessException {
        ArrayList<String> list = new ArrayList<String>();
        Class<?> cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(o) != null && f.get(o) != "") {
                list.add(f.getName() + "=" + f.get(o) + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + Configure.getKey();
        Util.log("Sign Before MD5:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        Util.log("Sign Result:" + result);
        return result;
    }

    public static String getSign(Map<String,String> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Entry<String, String> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + Configure.getKey();
        System.out.println("-----------Sign Before MD5----:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        System.out.println("-----------Sign Result--------:" + result);
        return result;
    }

   public static String getSignWithoutKey(Map<String,String> map){
	   ArrayList<String> list = new ArrayList<String>();
       for(Entry<String, String> entry:map.entrySet()){
           if(entry.getValue()!=""){
               list.add(entry.getKey() + "=" + entry.getValue() + "&");
           }
       }
       int size = list.size();
       String [] arrayToSort = list.toArray(new String[size]);
       Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
       StringBuilder sb = new StringBuilder();
       for(int i = 0; i < size; i ++) {
           sb.append(arrayToSort[i]);
       }
       String result = sb.toString();
       result=result.substring(0, result.length() - 1);
       System.out.println("-----------Sign Before MD5----:" + result);
       result = MD5.MD5Encode(result).toUpperCase();
       System.out.println("-----------Sign Result--------:" + result);
       return result;
   }

}
