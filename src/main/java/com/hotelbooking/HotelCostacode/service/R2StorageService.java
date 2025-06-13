package com.hotelbooking.HotelCostacode.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hotelbooking.HotelCostacode.exception.OurException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class R2StorageService {

    @Value("${cloud.r2.access.key}")
    private String accessKey;

    @Value("${cloud.r2.secret.key}")
    private String secretKey;

    @Value("${cloud.r2.bucket.name}")
    private String bucketName;

    @Value("${cloud.r2.endpoint.url}")
    private String endpointUrl;  // https://account_id.r2.cloudflarestorage.com

    public String uploadImage(MultipartFile photo) {
        try {
            String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(
                            new BasicAWSCredentials(accessKey, secretKey)))
                    .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                            endpointUrl, "auto")) // "auto" es crucial para R2
                    .build();

            InputStream inputStream = photo.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(photo.getContentType());
            metadata.setContentLength(photo.getSize());

            s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));

            // Construir URL pública correcta para R2
            return "https://pub-de31c08d6d634f879ba841ecdf9c8c41.r2.dev/" + fileName;

        } catch (Exception e) {
            throw new OurException("Error uploading to R2: " + e.getMessage());
        }
    }
}