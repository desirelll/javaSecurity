package com.desire.security.des;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.SecureRandom;

/**
 * @author desire
 * Created by desire on 2017/11/2.
 */
public class DES3_demo {

    private static String src = "hello world!";

    public static void main(String[] args) {
        jdk3DES();
    }

    private static void jdk3DES() {
        try {
            // 生成 key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            //keyGenerator.init(168);
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            // key 转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            SecretKey convertSecretKey = factory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk 3DES encrypt: " + org.apache.commons.codec.binary.Hex.encodeHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk 3DES decrypt: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
