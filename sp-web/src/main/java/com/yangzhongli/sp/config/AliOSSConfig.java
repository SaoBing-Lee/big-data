package com.yangzhongli.sp.config;

import com.yangzhongli.sp.constants.AliOssConfig;
import com.yangzhongli.sp.utils.AliOssUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * insert description here
 *
 * @author leven
 * @since 2019/1/4 19:21
 */
@Configuration
@ConfigurationProperties(value = "oss.ali", ignoreInvalidFields = true)
@Getter
@Setter
public class AliOSSConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    /**
     * 图片访问链接基本前缀
     */
    private String basePrefix;
    //阿里云的文件夹名称
    private String folder;

    @Bean
    public Object initOssUtil() {
        AliOssConfig config = new AliOssConfig();
        config.setAccessKeyId(accessKeyId);
        config.setAccessKeySecret(accessKeySecret);
        config.setBasePrefix(basePrefix);
        config.setBucketName(bucketName);
        config.setEndpoint(endpoint);
        config.setFolder(folder);
        AliOssUtil.init(config);
        return new Object();
    }

}
