package com.yhb.cms.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author fusu
 * @since 2020/10/13 3:14 下午
 */
@Data
@Component
@Configuration
public class OssConstantConfig {
    @Value("${aliyun.file.endpoint}")
    private   String endpoint;
    @Value("${aliyun.file.accessKeyId}")
    private  String accessKeyId;
    @Value("${aliyun.file.accessKeySecret}")
    private  String accessKeySecret;
    @Value("${aliyun.file.folder}")
    private  String folder;
    @Value("${aliyun.file.bucketName}")
    private  String bucketName;
    @Value("${aliyun.file.webUrl}")
    private  String webUrl;
}
