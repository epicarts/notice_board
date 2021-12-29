package com.osstem.notice.service.common;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class FileS3Service implements FileService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public String saveFile(MultipartFile file) throws IOException {
        String fileName = createFileName(file.getOriginalFilename());
        File uploadFile = convert(file)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

        // s3에 저장
        putS3(uploadFile, fileName);
        uploadFile.delete();// 로컬 파일 삭제

        return getFileUrl(fileName);
    }

    private void putS3(File uploadFile, String fileName) {
        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    public void deleteFile(String fileName) {
        amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    public String getFileUrl(String fileName) {
        return amazonS3Client.getUrl(bucket,fileName).toString();
    }


    // MultipartFile 파일 객체로 리턴
    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream( convertFile );
        fos.write( file.getBytes() );
        fos.close();
        return Optional.of(convertFile);
    }

    private String createFileName(String originalFileName) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString() + '_' + originalFileName;
    }


}
