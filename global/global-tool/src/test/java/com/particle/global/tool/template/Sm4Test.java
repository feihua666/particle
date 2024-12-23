package com.particle.global.tool.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SM4;

import javax.crypto.SecretKey;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/6/3 11:15
 */
public class Sm4Test {

    public static void main(String[] args) {
        SecretKey sm41 = SecureUtil.generateKey("SM4", 128);
        byte[] sm4s = sm41.getEncoded();
        System.out.println("key = " + HexUtil.encodeHexStr(sm4s));
        SM4 sm4 = new SM4(Mode.ECB, Padding.PKCS5Padding, sm4s);

        String readUtf8String = FileUtil.readUtf8String("/Users/yw/yuansu/temp/a.txt");

        for (int i = 0; i < 6; i++) {
            test(sm4, readUtf8String);
        }

    }

    private static void test(SM4 sm4, String data) {

        long start = System.currentTimeMillis();
        String encryptHex = sm4.encryptHex(data);
        System.out.println(StrUtil.format("encrypt duration: {}ms" , (System.currentTimeMillis() - start)));

        start = System.currentTimeMillis();
        String decryptStr = sm4.decryptStr(encryptHex);
        System.out.println(StrUtil.format("decrypt duration: {}ms" , (System.currentTimeMillis() - start)));

        System.out.println(decryptStr);
    }
}
