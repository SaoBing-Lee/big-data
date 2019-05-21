package com.yangzhongli.sp.utils;

import com.yangzhongli.sp.constants.CharsetName;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

/**
 *
 * <p>Title: MD5����</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2011</p>
 *
 * <p>Company: dboo</p>
 *
 * @author not attributable
 * @version 1.0
 */
public class MD5 {

    private static final Logger logger = LoggerFactory.getLogger(MD5.class);

  public static byte[] getKeyedDigest(byte[] buffer, byte[] key) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(buffer);
      return md5.digest(key);
    }
    catch (NoSuchAlgorithmException e) {
    }
    return null;
  }

  public static String getKeyedDigest(String strSrc, String key) {
    try {
      MessageDigest md5 = MessageDigest.getInstance("MD5");
      md5.update(strSrc.getBytes("UTF8"));
      String result = "";
      byte[] temp;

      temp = md5.digest(key.getBytes("UTF8"));
      for (int i = 0; i < temp.length; i++) {
        result += Integer.toHexString( (0x000000ff & temp[i]) |
                                      0xffffff00).substring(6);
      }
      return result;
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public static String MD5(String strSrc) {
	    try {
	      MessageDigest md5 = MessageDigest.getInstance("MD5");
	      md5.update(strSrc.getBytes("UTF8"));
	      String result = "";
	      byte[] temp;

	      temp = md5.digest("".getBytes("UTF8"));
	      for (int i = 0; i < temp.length; i++) {
	        result += Integer.toHexString( (0x000000ff & temp[i]) |
	                                      0xffffff00).substring(6);
	      }
	      return result;
	    }
	    catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

    public static String sign(String content) {
        return sign(content, "", CharsetName.UTF_8);
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        logger.debug(text);
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    /**
     * 签名字符串
     * @param text 需要签名的字符串
     * @param sign 签名结果
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String input_charset) {
        text = text + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text, input_charset));
        if(mysign.equals(sign)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
  
  
}
