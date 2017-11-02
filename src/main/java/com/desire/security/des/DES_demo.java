package com.desire.security.des;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Security;

/**
 * @author desire
 * @description 对称加密算法
 * Created by desire on 2017/11/2.
 */
public class DES_demo {

    private static String src = "hello world!";

    public static void main(String[] args) {
        jdkDES();
        bcDES();
    }

    public static void jdkDES() {
        try {
            // 生成 key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            // key 转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey convertSecretKey = factory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk DES encrypt: " + org.apache.commons.codec.binary.Hex.encodeHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk DES decrypt: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void bcDES() {
        try {
            Security.addProvider(new BouncyCastleProvider());

            // 生成 key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES", "BC");
            keyGenerator.getProvider();
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] bytesKey = secretKey.getEncoded();

            // key 转换
            DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey convertSecretKey = factory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk DES encrypt: " + org.apache.commons.codec.binary.Hex.encodeHexString(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk DES decrypt: " + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
