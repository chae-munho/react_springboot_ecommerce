package com.phegondev.Phegon.Eccormerce.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/*
//aws용 코드
@Service
@Slf4j
public class AwsS3Service {

    private final String bucketName = "phegon-ecommerce";

    @Value("${aws.s3.access}")
    private String awsS3AccessKey;
    @Value("${aws.s3.secrete}")
    private String awsS3SecreteKey;


    public String saveImageToS3(MultipartFile photo){
        try {
            String s3FileName = photo.getOriginalFilename();
            //create aes credentials using the access and secrete key
            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey, awsS3SecreteKey);

            //create an s3 client with config credentials and region
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                    .withRegion(Regions.US_EAST_2)
                    .build();

            //get input stream from photo
            InputStream inputStream = photo.getInputStream();

            //set metedata for the onject
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpeg");

            //create a put request to upload the image to s3
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3FileName, inputStream, metadata);
            s3Client.putObject(putObjectRequest);

            return "https://" + bucketName + ".s3.us-east-2.amazonaws.com/" + s3FileName;

        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Unable to upload image to s3 bucket: " + e.getMessage());
        }
    }
}
*/
//aws s3 사용 못 할 경우 사용하는 코드
@Service
@Slf4j
public class AwsS3Service {

    private final String uploadDir = System.getProperty("user.dir") + "/uploads/";

    // 허용할 이미지 확장자 목록
    private static final String[] allowedExtensions = {"jpg", "jpeg", "png", "gif", "bmp"};

    public String saveImageToLocal(MultipartFile photo) {
        try {
            // 원본 파일명에서 확장자 추출
            String originalFilename = photo.getOriginalFilename();
            if (originalFilename == null) {
                throw new IllegalArgumentException("파일 이름이 비어 있습니다.");
            }

            String extension = getExtension(originalFilename).toLowerCase();
            boolean isValidExtension = false;
            for (String ext : allowedExtensions) {
                if (ext.equals(extension)) {
                    isValidExtension = true;
                    break;
                }
            }

            if (!isValidExtension) {
                throw new IllegalArgumentException("허용되지 않은 파일 형식입니다: " + extension);
            }

            // uploads 폴더 생성
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // UUID 기반 새로운 파일명 생성
            String uuid = UUID.randomUUID().toString();
            String newFileName = uuid + "." + extension;

            // 파일 저장
            String filePath = uploadDir + newFileName;
            File dest = new File(filePath);
            photo.transferTo(dest);

            // 저장된 파일 경로 반환
            return "/uploads/" + newFileName;

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("로컬에 이미지 저장 실패: " + e.getMessage());
        }
    }

    private String getExtension(String filename) {
        int dotIndex = filename.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            throw new IllegalArgumentException("파일 확장자가 없습니다.");
        }
        return filename.substring(dotIndex + 1);
    }
}