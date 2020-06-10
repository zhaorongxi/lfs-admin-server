package com.bc.admin.util;

import com.bc.base.exception.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtils {

    /**
     * 密钥 (需要前端和后端保持一致)
     */
    private static final String SALT = "1234123412ABCDEF";

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(EncryptUtils.class);
    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/CBC/PKCS5Padding";
    /**
     * encryptToAESPKCS5FromObject:将对象进行AES加密
     *
     * @param encryptData : 加密对象
     * @param :           加密密钥
     * @return
     */
    public static String encryptToAESPKCS5FromObject(Object encryptData) {
        String encryptResultStr = null;
        try {
            ObjectMapper om = new ObjectMapper();
            String encryptDataStr = om.writeValueAsString(encryptData);
            encryptResultStr = encryptToAESPKCS5(encryptDataStr);
        } catch (JsonProcessingException e) {
            throw new BusinessException(e);
        }
        return encryptResultStr;
    }

    /**
     * public static final int ENCRYPT_MODE 用于将 Cipher 初始化为加密模式的常量。
     * public static final int DECRYPT_MODE 用于将 Cipher 初始化为解密模式的常量。
     * public static final int WRAP_MODE 用于将 Cipher 初始化为密钥包装模式的常量。
     * public static final int UNWRAP_MODE 用于将 Cipher 初始化为密钥解包模式的常量。
     * public static final int PUBLIC_KEY 用于表示要解包的密钥为“公钥”的常量。
     * public static final int PRIVATE_KEY 用于表示要解包的密钥为“私钥”的常量。
     * public static final int SECRET_KEY 用于表示要解包的密钥为“秘密密钥”的常量。
     **/

    /**
     * encryptToAESPKCS5:加密AES
     *
     * @param content ： 加密内容
     * @param ：       加密密钥
     * @return
     */
    public static String encryptToAESPKCS5(String content) {
        byte[] encryptResult = null;
        try {
            // 密钥
            SecretKeySpec secretKey = new SecretKeySpec(SALT.getBytes(), "AES");
            // 算法/模式/填充
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化向量,在密钥相同的前提下，加上初始化向量，相同内容加密后相同
            IvParameterSpec zeroIv = new IvParameterSpec(SALT.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, zeroIv);
            encryptResult = cipher.doFinal(byteContent);
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException",e);
        } catch (InvalidKeyException e) {
            logger.error("InvalidKeyException",e);
        } catch (InvalidAlgorithmParameterException e) {
            logger.error("InvalidAlgorithmParameterException",e);
        } catch (NoSuchPaddingException e) {
            logger.error("NoSuchPaddingException",e);
        } catch (BadPaddingException e) {
            logger.error("BadPaddingException",e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException",e);
        } catch (IllegalBlockSizeException e) {
            logger.error("IllegalBlockSizeException",e);
        }
        return Base64.getEncoder().encodeToString(encryptResult);
    }

    /**
     * decryptToAESPKCS5:解密AES
     *
     * @param content ： 解密内容
     * @param
     * @return
     */
    public static String decryptToAESPKCS5(String content) {
        byte[] decryptResult = null;
        try {
            // 密钥
            SecretKeySpec secretKey = new SecretKeySpec(SALT.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
            // base64转换
            byte[] byteContent = Base64.getDecoder().decode(content);
            IvParameterSpec zeroIv = new IvParameterSpec(SALT.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, zeroIv);
            decryptResult = cipher.doFinal(byteContent);
            return new String(decryptResult, "utf-8");
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException",e);
        } catch (InvalidKeyException e) {
            logger.error("InvalidKeyException",e);
        } catch (InvalidAlgorithmParameterException e) {
            logger.error("InvalidAlgorithmParameterException",e);
        } catch (NoSuchPaddingException e) {
            logger.error("NoSuchPaddingException",e);
        } catch (BadPaddingException e) {
            logger.error("BadPaddingException",e);
        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException",e);
        } catch (IllegalBlockSizeException e) {
            logger.error("IllegalBlockSizeException",e);
        }
        return "";
    }

    /**
     * 将二进制转换成16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * lyc
     *
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }


    /**
     * 私有构造
     */
    private EncryptUtils() {
    }
}
