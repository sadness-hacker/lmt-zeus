package com.lmt.zeus.parent.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.lmt.zeus.parent.exception.ZeusExceptionEnum;
import com.lmt.zeus.parent.exception.ZeusException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @description 加密工具类
 * @author bazhandao
 * @date 2018/11/8 17:51
 * @since JDK1.8
 */
public class SecurityUtils {

    /**
     * AES签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1PRNG";

    /**
     * 获取字符串的md5值 32位小写
     * @author bazhandao
     * @date 2018-11-10
     * @param str
     * @return
     */
    public static String md5(String str) {
        @SuppressWarnings("deprecation")
        String md5 = Hashing.md5().newHasher().putString(str, Charsets.UTF_8).hash().toString();
        return md5;
    }

    /**
     * 获取字符串的md5值,转为Long型,正数
     * @param str
     * @return
     */
    public static long md5ToLong(String str) {
        return Math.abs(Hashing.md5().newHasher().putString(str, Charsets.UTF_8).hash().asLong());
    }

    /**
     * 获取字符串的md5值 16位小写
     * @author bazhandao
     * @date 2018-11-10
     * @param str
     * @return
     */
    public static String shortMd5(String str) {
        return md5(str).substring(8, 24);
    }

    /**
     * 获取文件md5值
     * @author bazhandao
     * @date 2018-11-10
     * @param filePath
     * @return
     */
    public static String getFileMd5(String filePath) {
        return getFileMd5(new File(filePath));
    }

    /**
     * 获取文件md5值
     * @author bazhandao
     * @date 2018-11-10
     * @param file
     * @return
     */
    public static String getFileMd5(File file) {
        try {
            @SuppressWarnings("deprecation")
            String md5 = Files.hash(file, Hashing.md5()).toString();
            return md5;
        } catch (IOException e) {
            throw ZeusException.wrap(ZeusExceptionEnum.GEN_FILE_MD5_ERR.getCode(), ZeusExceptionEnum.GEN_FILE_MD5_ERR.getMsg(), e)
                .set("file", file);
        }
    }

    /**
     * aes加密,128位
     * @param key
     * @param content
     * @return
     */
    public static String aesEncode(String key, String content) {
        return aesEncode(128, key, content);
    }

    /**
     * aes加密
     * @param bitNum 128,192,256
     * @param key
     * @param content
     * @return
     */
    public static String aesEncode(int bitNum, String key, String content) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance(SIGN_ALGORITHMS);
            secureRandom.setSeed(key.getBytes());
            keygen.init(bitNum, secureRandom);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keygen.generateKey().getEncoded(), "AES"));
            byte [] arr = content.getBytes("utf-8");
            byte [] encodeArr = cipher.doFinal(arr);
            String code = new String(Base64.getEncoder().encode(encodeArr));
            return code;
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.AES_ENCODE_ERROR.getCode(), ZeusExceptionEnum.AES_ENCODE_ERROR.getMsg(), e)
                    .set("bitNum", bitNum)
                    .set("key", key)
                    .set("content", content);
        }
    }

    /**
     * aes解密
     * @param key
     * @param content
     * @return
     */
    public static String aesDecode(String key, String content) {
        return aesDecode(128, key, content);
    }

    /**
     * AES解密
     * @param bitNum 128,192,256
     * @param key
     * @param content
     * @return
     */
    public static String aesDecode(int bitNum, String key, String content) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance(SIGN_ALGORITHMS);
            secureRandom.setSeed(key.getBytes());
            keygen.init(bitNum, secureRandom);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keygen.generateKey().getEncoded(), "AES"));
            byte [] byteArr = Base64.getDecoder().decode(content);
            byte [] decodeArr = cipher.doFinal(byteArr);
            String decode = new String(decodeArr, StandardCharsets.UTF_8);
            return decode;
        } catch (Exception e) {
            throw ZeusException.wrap(ZeusExceptionEnum.AES_DECODE_ERROR.getCode(), ZeusExceptionEnum.AES_DECODE_ERROR.getMsg(), e)
                    .set("bitNum", bitNum)
                    .set("key", key)
                    .set("content", content);
        }
    }


}
