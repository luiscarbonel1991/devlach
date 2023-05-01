package com.devlach.springbootawss3async.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.net.URI;

@Configuration
public class AwsConfig {

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    private static final String LOCAL_STACK_ENDPOINT = "http://localhost:4566";

   /*
    @Bean
    public S3AsyncClient s3AsyncClient() { // Not crtBuilder()
        return S3AsyncClient.builder()
                .credentialsProvider(staticCredentialsProvider())
                .endpointOverride(URI.create(LOCAL_STACK_ENDPOINT))
                .region(region())
                .build();
    }
    */

    @Bean
    public S3AsyncClient s3AsyncClient() {
        return S3AsyncClient.crtBuilder()
                .credentialsProvider(staticCredentialsProvider())
                .endpointOverride(URI.create(LOCAL_STACK_ENDPOINT))
                .region(region())
                .forcePathStyle(true)
                .build();
    }

    private StaticCredentialsProvider staticCredentialsProvider() {
        AwsCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        return StaticCredentialsProvider.create(awsBasicCredentials);
    }

    private Region region() {
        return region != null ? Region.of(region) : Region.US_EAST_1;
    }

}
