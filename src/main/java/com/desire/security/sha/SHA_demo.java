package com.desire.security.sha;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by desire on 2017/11/2.
 */
public class SHA_demo {

    private static String src = "hello world!";

    public static void main(String[] args) {
        jdkSHA1();
        bcSHA1();
        bcSHA224();
        ccSHA1();
    }

    public static void jdkSHA1() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(src.getBytes());
            System.out.println("jdk SHA-1: " + Hex.encodeHexString(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void bcSHA1() {
        SHA1Digest digest = new SHA1Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] sha1Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha1Bytes, 0);
        System.out.println("bc SHA-1: " + org.bouncycastle.util.encoders.Hex.toHexString(sha1Bytes));
    }

    public static void bcSHA224() {
        Digest digest = new SHA224Digest();
        digest.update(src.getBytes(), 0, src.getBytes().length);
        byte[] sha224Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha224Bytes, 0);
        System.out.println("bc SHA-224: " + org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));
    }

    public static void ccSHA1() {
        System.out.println("cc SHA1-1: " + DigestUtils.sha1Hex(src.getBytes()));
        System.out.println("cc SHA1-2: " + DigestUtils.sha1Hex(src));
    }
}
