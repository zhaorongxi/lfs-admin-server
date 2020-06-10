package com.bc.admin.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

public class DESUtils {

    private final static String DES = "DES";

    private static String INDEX = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }

    private static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        int len = INDEX.length();
        for (int i = 0; i < length; i++) {
            sb.append(INDEX.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }

    public static String getKey() {
        return getRandomString(16);
    }

    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        String strs = Base64.encodeBase64String(bt);
        return strs;
    }

    public static String decrypt(String data, String key) throws Exception {
        if (data == null) {
            return null;
        }
        byte[] buf = Base64.decodeBase64(data);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(DES);
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        return cipher.doFinal(data);
    }

}
