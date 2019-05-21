package com.yangzhongli.sp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

public class MessageUtil {

    private static final Logger logger = LoggerFactory.getLogger(MessageUtil.class);

    /**
     * 向微信微信服务器发送请求
     *
     * @param requestUrl    请求链接
     * @param requestMethod 请求方法
     * @param outputStr     输出流写数据
     */
    public static String sendWeiXinMessage(String requestUrl, String requestMethod, String outputStr) throws Exception {
        return sendHttpsRequest(requestUrl, requestMethod, outputStr, null);
    }

    /**
     * 发送https请求
     *
     * @param requestUrl    请求链接
     * @param requestMethod 请求方法
     * @param outputStr     输出流写数据
     * @param contentType   传输内容类型
     */
    public static String sendHttpsRequest(String requestUrl, String requestMethod, String outputStr, String contentType) {
        HttpsURLConnection conn = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            conn = (HttpsURLConnection) url.openConnection();
            if (contentType != null)
                conn.setRequestProperty("Content-Type", contentType);
            conn.setSSLSocketFactory(ssf);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(10000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            inputStream = conn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            return buffer.toString();
        } catch (Exception e) {
            logger.error("https请求异常：{" + requestUrl + "}", e);
        } finally {
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error("关闭 bufferedReader 异常", e);
                }
            if (inputStreamReader != null)
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    logger.error("关闭 inputStreamReader 异常", e);
                }
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("关闭 inputstream 异常");
                }
            if (conn != null)
                conn.disconnect();
        }
        return null;
    }



}