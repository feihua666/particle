package com.particle.particledemo;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class ComUtil {


    public static String getMd5ZYPara(Map<String, Object> paramap, String userid, String userkey) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Object> map : paramap.entrySet()) {
            String key = map.getKey();
            list.add(key);
        }
        List<String> order = null;
        try {
            order = order(list);
            StringBuilder sb = new StringBuilder();
            sb.append(userid);
            for (String a : order) {
                sb.append(a).append(":").append(paramap.get(a)).append(",");
            }
            String param = sb.toString();
            String newParam = param.substring(0, param.length() - 1) + userkey;
            String md5 = getMd5(newParam);
            return md5;
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return "excep";
    }

    public static List<String> order(List<String> list) throws Exception {
        List<String> listChar = new ArrayList<String>();
        //1.把list数组中的每个字符串 转为字符数组
        for (int i = 0; i < list.size(); i++) {
            listChar.add(list.get(i));
        }
        List<String> listCharO = listChar;

        //2.循环数组listChar取出每一个进行冒泡排序比较

        for (int a = 0; a < listChar.size(); a++) {
            for (int b = 0; b < listChar.size(); b++) {
                String achar = listChar.get(a);
                String bchar = listChar.get(b);
                //相同的就不需要比较
                if (achar != bchar) {
                    int size = 0;
                    //可能会存在前面都一样的字符所有取字符数组长度小的 来进行循环比较 字符的ascll
                    if (achar.length() > bchar.length()) {
                        size = bchar.length();
                    } else {
                        size = achar.length();
                    }
                    //循环比较赋值
                    for (int c = 0; c < size; c++) {
                        if ((int) achar.charAt(c) > (int) bchar.charAt(c)) {
                            if (a < b) {
                                listCharO.set(b, achar);
                                listCharO.set(a, bchar);
                                break;
                            }
                        } else if ((int) achar.charAt(c) == (int) bchar.charAt(c)) {

                        } else if ((int) achar.charAt(c) < (int) bchar.charAt(c)) {
                            break;
                        }
                    }
                }
            }
        }
        return listCharO;
    }


    /**
     * MD5加密，前面encrypt方法可能会出现加密缺少前置0的情况
     */
    public static String getMd5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
