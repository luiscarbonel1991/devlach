package com.devlach.springbootawss3async.services;

import java.util.List;

public interface S3Service {

    void createBucket(String bucketName);

    void deleteBucket(String bucketName);

    int deleteObjects(String bucketName, List<String> keys);

    int uploadDirectory(String bucketName, String directoryPath, String prefix);

    List<String> listObjects(String bucketName, String prefix);
}
