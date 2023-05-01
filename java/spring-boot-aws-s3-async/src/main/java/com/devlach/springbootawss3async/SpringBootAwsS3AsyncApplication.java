package com.devlach.springbootawss3async;

import com.devlach.springbootawss3async.services.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.ResourceLoader;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import java.net.URI;
import java.util.List;

@SpringBootApplication
@Slf4j
public class SpringBootAwsS3AsyncApplication {

    public static final String BUCKET_NAME = "devlach-spring-boot-aws-s3-async";
    private final S3Service s3Service;

    public SpringBootAwsS3AsyncApplication(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAwsS3AsyncApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            // Create bucket
            s3Service.createBucket(BUCKET_NAME);

            // Upload directory
            ClassLoader classLoader = getClass().getClassLoader();
            String uploadDirectoryPrefix = "uploadDirectory";
            String uploadDirectoryPath = classLoader.getResource("static").getPath() + "/" + uploadDirectoryPrefix;

            int filesNotUploaded = s3Service.uploadDirectory(BUCKET_NAME, uploadDirectoryPath, uploadDirectoryPrefix);
            log.info("Files not uploaded: {}", filesNotUploaded);
            assert filesNotUploaded == 0;

            // Clean up
            // Delete bucket's objects and bucket
            List<String> keyObjectsToDelete = s3Service.listObjects(BUCKET_NAME, uploadDirectoryPrefix);
            int objectsDeleted = s3Service.deleteObjects(BUCKET_NAME, keyObjectsToDelete);
            log.info("Objects deleted: {}", objectsDeleted);
            assert objectsDeleted == keyObjectsToDelete.size();

            s3Service.deleteObjects(BUCKET_NAME, List.of("/" + uploadDirectoryPrefix));
            s3Service.deleteBucket(BUCKET_NAME);

        };
    }

}
