package com.yangzhongli.sp.constants;

import lombok.Data;

/**
 * insert description here
 *
 * @author leven
 * @since 2019/1/4 22:22
 */
@Data
public class AliOssConfig {

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

}
