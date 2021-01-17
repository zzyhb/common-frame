package com.yhb.cms.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author fusu
 * @since 2020/10/13 3:11 下午
 */
@Component
@Slf4j
public class OssUtil {
    @Autowired
    private OssConstantConfig ossConstantConfig;

    /** 上传文件*/
    public String upLoad(File file, String fileSuffix){
        log.info("------OSS文件上传开始--------"+file.getName());
        String endpoint=ossConstantConfig.getEndpoint();
        log.info("获取到的Point为:"+endpoint);
        String accessKeyId=ossConstantConfig.getAccessKeyId();
        String accessKeySecret=ossConstantConfig.getAccessKeySecret();
        String bucketName=ossConstantConfig.getBucketName();
        String fileHost=ossConstantConfig.getFolder();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=format.format(new Date());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(getContentType(fileSuffix));
        try {
            // 判断容器是否存在,不存在就创建
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            String fileUrl = fileHost + "/" + dateStr + "/" + file.getName();
            // 设置权限(公开读)
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file, objectMetadata));

            if (result != null) {
                log.info("PutObjectResult result: {}", result);
                log.info("------OSS文件上传成功------" + fileUrl);
                //文件的web访问地址
                return ossConstantConfig.getWebUrl() +"/"+ fileUrl;

            }
        }catch (OSSException oe){
            log.error(oe.getMessage());
        }catch (ClientException ce){
            log.error(ce.getErrorMessage());
        }finally{
            if(ossClient!=null){
                ossClient.shutdown();
            }
        }
        return null;
    }

    public static String getContentType(String fileNameExtension) {
        if (".bmp".equalsIgnoreCase(fileNameExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileNameExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileNameExtension) ||
                ".jpg".equalsIgnoreCase(fileNameExtension) ||
                ".png".equalsIgnoreCase(fileNameExtension)) {
            return "image/jpg";
        }
        if (".html".equalsIgnoreCase(fileNameExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileNameExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileNameExtension)) {
            return "application/vnd.visio";
        }
        if (".pptx".equalsIgnoreCase(fileNameExtension) ||
                ".ppt".equalsIgnoreCase(fileNameExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".docx".equalsIgnoreCase(fileNameExtension) ||
                ".doc".equalsIgnoreCase(fileNameExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileNameExtension)) {
            return "text/xml";
        }
        return "image/jpg";
    }

}
