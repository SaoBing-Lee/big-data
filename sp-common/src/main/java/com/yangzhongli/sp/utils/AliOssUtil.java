package com.yangzhongli.sp.utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.yangzhongli.sp.constants.AliOssConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 数据库连接 配置类
 *
 * @author texous
 * @since 2019-01-04 19:08:52
 */
@Slf4j
@Component
public class AliOssUtil {

    private static AliOssConfig config;

    public static void init(AliOssConfig config) {
        AliOssUtil.config = config;
    }

    /**
     * 上传文件
     *
     * @param fileUrl 文件在oss服务器的地址
     * @param file     文件
     * @return 文件在oss上的路径
     */
    public static String upload(String fileUrl, File file) {
        //上传到oss后的文件名称
        //String fileName = String.valueOf(new Date().getTime());
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(config.getEndpoint(),
                new DefaultCredentialProvider(config.getAccessKeyId(), config.getAccessKeySecret()),
                new ClientConfiguration());
        try {
            PutObjectResult result = ossClient.putObject(config.getBucketName(), fileUrl, file);
            return config.getBasePrefix() + fileUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            ossClient.shutdown();
        }

        return null;
    }

    /**
     * 文件上传
     *
     * @param fileName    文件名
     * @param inputStream 文件流
     * @return 文件在oss上的路径
     */
    public static String upload(String fileName, InputStream inputStream) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(config.getEndpoint(),
                new DefaultCredentialProvider(config.getAccessKeyId(), config.getAccessKeySecret()),
                new ClientConfiguration());
        try {
            ossClient.putObject(config.getBucketName(), config.getFolder()+fileName, inputStream);

            return config.getBasePrefix() + config.getFolder()+fileName;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            ossClient.shutdown();
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }

        return null;
    }

    /**
     * 文件下载
     *
     * @param key
     * @param destFile 文件
     */
    public static void download(String key, File destFile) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(config.getEndpoint(),
                new DefaultCredentialProvider(config.getAccessKeyId(), config.getAccessKeySecret()),
                new ClientConfiguration());
        // 下载object到文件
        ossClient.getObject(new GetObjectRequest(config.getBucketName(), key), destFile);
        // 关闭client
        ossClient.shutdown();
    }

    public static OSSObject download(String key) throws IOException {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(config.getEndpoint(),
                new DefaultCredentialProvider(config.getAccessKeyId(), config.getAccessKeySecret()),
                new ClientConfiguration());
        return ossClient.getObject(config.getBucketName(), key);
    }


    /**
     * 删除文件
     *
     * @param fileName 文件名称
     */
    public static void del(String fileName) {
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(config.getEndpoint(),
                new DefaultCredentialProvider(config.getAccessKeyId(), config.getAccessKeySecret()),
                new ClientConfiguration());
        // 删除文件。
        ossClient.deleteObject(config.getBucketName(), config.getFolder()+fileName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    /**
     * 删除服务上的文件
     * @param filePath 路径
     * @return
     */
    public static boolean deleteServerFile(String filePath){
        boolean delete_flag = false;
        File file = new File(filePath);
        if (file.exists() && file.isFile() && file.delete())
            delete_flag = true;
        else
            delete_flag = false;
        return delete_flag;
    }
}