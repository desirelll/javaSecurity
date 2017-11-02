package com.desire.security.pbe;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.SecureRandom;

/**
 * @author desire
 * Created by desire on 2017/11/2.
 */
public class PBE_demo {

    private static String src = "hello world";

    public static void main(String[] args) {
        jdkPBE();
    }

    private static void jdkPBE() {
        try {
            // 初始化盐
            SecureRandom random = new SecureRandom();
            byte[] salt = random.generateSeed(8);

            // 口令与密钥
            String password = "desire";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            SecretKey key = factory.generateSecret(pbeKeySpec);

            // 加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            byte[] bytes = cipher.doFinal(src.getBytes());
            System.out.println("jdk PBE encrypt: " + Base64.encodeBase64String(bytes));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
            bytes = cipher.doFinal(bytes);
            System.out.println("jdk PBE decrypt: " + new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
