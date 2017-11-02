package com.desire.security.des;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by desire on 2017/11/2.
 */
public class AES_demo {

    private static String src = "hello world!";

    public static void main(String[] args) {
        jdkAES();
    }

    private static void jdkAES() {
        try {
            // 生成 key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // key 转换
            Key key = new SecretKeySpec(keyBytes, "AES");

            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(src.getBytes());
            System.out.println("jdk AES encrypt: " + Base64.encodeBase64String(bytes));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            bytes = cipher.doFinal(bytes);
            System.out.println("jdk AES decrypt: " + new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
