//package org.anyonetoo.anyonetoo.config.s3;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//
//@Configuration
//public class S3Config {
//
//    @Value("${spring.cloud.aws.credentials.accessKey}")
//    private String accessKey;
//
//    @Value("${spring.cloud.aws.credentials.secretKey}")
//    private String secretKey;
//
//    @Value("${spring.cloud.aws.region.static}")
//    private String region;
//
//    @Bean
//    public AmazonS3 getAmazonS3Client(){
//        final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey,secretKey);
//        // Get Amazon S3 client and return s3 client object
//
//        return AmazonS3ClientBuilder
//                .standard()
//                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
//                .withRegion(region)
//                .build();
//    }
//}