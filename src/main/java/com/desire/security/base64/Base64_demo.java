package com.desire.security.base64;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by desire on 2017/11/2.
 */
public class Base64_demo {

    private static String src = "hello world!";

    public static void main(String[] args) throws IOException {
        jdkBase64();
        commonsCodesBase64();
        bouncyCastleBase64();
    }

    public static void jdkBase64() throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        String encode = encoder.encode(src.getBytes());
        System.out.println("encode: " + encode);

        BASE64Decoder decoder = new BASE64Decoder();
        System.out.println(new String(decoder.decodeBuffer(encode)));
    }

    public static void commonsCodesBase64() {
        byte[] encodeBytes = Base64.encodeBase64(src.getBytes());
        System.out.println("encode: " + new String(encodeBytes));

        byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
        System.out.println(new String(decodeBytes));
    }

    public static void bouncyCastleBase64() {
        byte[] encode = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
        System.out.println("encode: " + encode);

        byte[] decode = org.bouncycastle.util.encoders.Base64.decode(encode);
        System.out.println(new String(decode));
    }

}
