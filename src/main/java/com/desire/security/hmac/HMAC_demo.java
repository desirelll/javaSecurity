package com.desire.security.hmac;


import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by desire on 2017/11/2.
 */
public class HMAC_demo {

    private static String src = "hello world!";

    public static void main(String[] args) {
        jdkHmacMD5();
        bcHmacMD5();
    }

    public static void jdkHmacMD5() {
        try {
            // 初始化
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
            // 产生密钥
            SecretKey secretKey = keyGenerator.generateKey();
            // 获得密钥
            //byte[] key = secretKey.getEncoded();

            byte[] key = Hex.decodeHex(new char[]{'a','a','a','a','a','a','a','a','a','a'});

            // 还原密钥
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacMD5");

            // 实例化 MAC
            Mac mac = Mac.getInstance(secretKeySpec.getAlgorithm());
            // 初始化 MAC
            mac.init(secretKeySpec);
            // 执行摘要
            byte[] bytes = mac.doFinal(src.getBytes());
            System.out.println("jdk hmacMD5: " + Hex.encodeHexString(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bcHmacMD5() {
        HMac hmac = new HMac(new MD5Digest());
        hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaaaaaaaa")));
        hmac.update(src.getBytes(), 0, src.getBytes().length);

        byte[] hmacMD5Bytes = new byte[hmac.getMacSize()];
        hmac.doFinal(hmacMD5Bytes, 0);
        System.out.println("bc hmacMD5: " + org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes));
    }
}
