package com.desire.security.md;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by desire on 2017/11/2.
 */
public class MD_demo {

    private static String src = "hello world!";

    public static void main(String[] args) {
        jdkMD5();
        jdkMD2();
        bcMD4();
        bcMD5();
        ccMD5();
        ccMD2();
    }

    public static void jdkMD5() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(src.getBytes());
            // 转换为 16 进制输出
            System.out.println("JDK MD5: " + Hex.encodeHexString(md5Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void jdkMD2() {
        try {
            MessageDigest md2 = MessageDigest.getInstance("MD2");
            byte[] md2Bytes = md2.digest(src.getBytes());
            // 转换为 16 进制输出
            System.out.println("JDK MD2: " + Hex.encodeHexString(md2Bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void bcMD4() {
        MD4Digest md4Digest = new MD4Digest();
        md4Digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md4Bytes = new byte[md4Digest.getDigestSize()];
        md4Digest.doFinal(md4Bytes, 0);
        System.out.println("BC MD4: " + org.bouncycastle.util.encoders.Hex.toHexString(md4Bytes));
    }

    public static void bcMD5() {
        MD5Digest md5Digest = new MD5Digest();
        md5Digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] md5Bytes = new byte[md5Digest.getDigestSize()];
        md5Digest.doFinal(md5Bytes, 0);
        System.out.println("BC MD5: " + org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes));
    }

    public static void ccMD5() {
        System.out.println("CC MD5: " + DigestUtils.md5Hex(src.getBytes()));
    }

    public static void ccMD2() {
        System.out.println("CC MD2: " + DigestUtils.md2Hex(src.getBytes()));
    }
}
