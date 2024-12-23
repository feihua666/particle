package com.particle.global.tool.template;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/7/17 18:47
 */
public class Sha1Test {

    static String result = "fce370bbc56dada553b0e0e6b2dbdfc42e095dbbe7878f876e21baba8ddd081a".toLowerCase();

    static String prefix = "110106";
    static int year = 1981;
    static String hit = "1";
    public static void main(String[] args) {

        String test = "0bdaab758db036f2474335bd106d252f1cdd44a3".toLowerCase();
        String id = "371102199911037810";
        String idBase64 = Base64.encode(id);

        System.out.println("idBase64 = " + idBase64);
// id = idBase64.toLowerCase();
        String s = DigestUtil.sha1Hex(id);
        System.out.println("sha1Hex = " + s);


        s = DigestUtil.md5Hex(id);
        System.out.println("md5Hex = " + s);

        s = DigestUtil.md5Hex(id);
        s = DigestUtil.sha1Hex(s);
        System.out.println("md5sha1Hex = " + s);

        s = DigestUtil.sha256Hex(id);
        System.out.println("sha256Hex = " + s);


        s = DigestUtil.sha256Hex(id);
        s = DigestUtil.sha1Hex(s);
        System.out.println("sha256Hexsha1Hex = " + s);

        s = SmUtil.sm3(id);
        System.out.println("sm3 = " + s);

        s = SmUtil.sm3(id);
        s = DigestUtil.sha1Hex(s);
        System.out.println("sm3sha1Hex = " + s);
    }

    private static void test(){

        List<String> strings = FileUtil.readUtf8Lines("/Users/yw/temp/test11.txt");
        // for (String string : strings) {
        //     result = string;
        LocalDate localDate = LocalDateTimeUtil.parseDate(year-1 + "-12-31");

        while (localDate.getYear() != year + 1) {
            localDate = localDate.plusDays(1);

            String monthDay = LocalDateTimeUtil.format(localDate, "MMdd");
            String temp = prefix + year  + monthDay;
            // 遍历从0000到9999的所有数字
            for (int i = 0; i < 10000; i++) {
                // 使用String.format来格式化数字，确保是四位数（不足部分用0填充）
                String lastFourDigits = String.format("%04d", i);
                String temp1 = temp + lastFourDigits;
                System.out.println(temp1);
                String sha1hex = SmUtil.sm3(temp1).toLowerCase();
                if (Objects.equals(sha1hex, result)) {
                    System.out.println("hit=" + temp1);
                    return;
                }

            }
        }
        // }
    }

}
