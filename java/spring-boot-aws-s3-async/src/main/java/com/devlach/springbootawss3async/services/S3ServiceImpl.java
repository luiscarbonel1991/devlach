package com.devlach.springbootawss3async.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.CompletedDirectoryUpload;
import software.amazon.awssdk.transfer.s3.model.UploadDirectoryRequest;
import software.amazon.awssdk.transfer.s3.progress.LoggingTransferListener;

import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiConsumer;

@Service
@Slf4j
public class S3ServiceImpl implements S3Service {

    private final S3AsyncClient s3AsyncClient;

    private static final int DEFAULT_MAX_TICKS = 20;

    public S3ServiceImpl(S3AsyncClient s3AsyncClient) {
        this.s3AsyncClient = s3AsyncClient;
    }

    @Override
    public void createBucket(String bucketName) {
        try {
            s3AsyncClient.createBucket(builder -> builder.bucket(bucketName))
                    .whenComplete(getCreateBucketResponseThrowableBiConsumer(bucketName)).join();
        } catch (S3Exception e) {
            log.error("Error creating bucket {}. {}", bucketName, e.awsErrorDetails().errorMessage());
            throw e; // throw you custom exception here. It will be caught by the controller advice
        }
    }


    @Override
    public void deleteBucket(String bucketName) {
        try {
            s3AsyncClient.deleteBucket(builder -> builder.bucket(bucketName)).whenComplete(getDeleteBucketResponseThrowableBiConsumer(bucketName)).join();
        } catch (S3Exception e) {
            log.error("Error deleting bucket {}. {}", bucketName, e.awsErrorDetails().errorMessage());
            throw e; // throw you custom exception here. It will be caught by the controller advice
        }
    }

    @Override
    public int deleteObjects(String bucketName, List<String> objectKeys) {
        try {
            ObjectIdentifier[] objectIdentifiers = objectKeys.stream().map(objectKey -> ObjectIdentifier.builder().key(objectKey).build()).toArray(ObjectIdentifier[]::new);
            return s3AsyncClient.deleteObjects(DeleteObjectsRequest.builder()
                    .bucket(bucketName)
                    .delete(builder -> builder.objects(
                            objectIdentifiers
                    ).build())
                    .build()).join().deleted().size();
        } catch (S3Exception e) {
            log.error("Error deleting objects from bucket {}. {}", bucketName, e.awsErrorDetails().errorMessage());
            throw e; // throw you custom exception here. It will be caught by the controller advice
        }
    }


    @Override
    public int uploadDirectory(String bucketName, String directoryPath, String prefix) {
        validateS3Key(prefix);
        S3TransferManager s3TransferManager = S3TransferManager.builder()
                .s3Client(s3AsyncClient)
                .build();
        UploadDirectoryRequest.Builder uploadDirectoryBuilder = UploadDirectoryRequest.builder()
                .bucket(bucketName)
                .s3Prefix(prefix)
                .source(Paths.get(directoryPath))
                .uploadFileRequestTransformer(uploadFileRequest -> uploadFileRequest
                        .addTransferListener(LoggingTransferListener.create(DEFAULT_MAX_TICKS))
                        .build());

        CompletedDirectoryUpload completedDirectoryUpload = s3TransferManager.uploadDirectory(uploadDirectoryBuilder.build())
                .completionFuture().join();
        completedDirectoryUpload.failedTransfers()
                .forEach(transfer -> log.warn("Error uploading file {}", transfer.exception().getMessage()));
        return completedDirectoryUpload.failedTransfers().size();
    }

    @Override
    public List<String> listObjects(String bucketName, String prefix) {
        validateS3Key(prefix);
        return s3AsyncClient
                .listObjectsV2(builder -> builder.bucket(bucketName)
                        .prefix(prefix)).join().contents().stream().map(S3Object::key).toList();

    }


    private static BiConsumer<CreateBucketResponse, Throwable> getCreateBucketResponseThrowableBiConsumer(String bucketName) {
        return (resp, err) -> {
            if (resp != null) {
                log.info("Bucket {} created successfully", bucketName);
            } else {
                log.error("Error creating bucket {}. {}", bucketName, err.getMessage());
            }
        };
    }

    private static BiConsumer<DeleteBucketResponse, Throwable> getDeleteBucketResponseThrowableBiConsumer(String bucketName) {
        return (resp, err) -> {
            if (resp != null) {
                log.info("Bucket {} deleted successfully", bucketName);
            } else {
                log.error("Error deleting bucket {}. {}", bucketName, err.getMessage());
            }
        };
    }

    private static void validateS3Key(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be null or empty");
        }

        if (key.startsWith("/")) {
            throw new IllegalArgumentException("Key must not start with /");
        }

        if (key.contains("//")) {
            throw new IllegalArgumentException("Key must not contain //");
        }
    }
}
