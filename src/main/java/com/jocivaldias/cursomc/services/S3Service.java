package com.jocivaldias.cursomc.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.xspec.S;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jocivaldias.cursomc.services.exception.FileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    @Value("${s3.bucket}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3;

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    public URI uploadFile(MultipartFile multipartFile){

        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = null;
            is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(is, fileName, contentType);
        } catch (IOException e) {
            throw new FileException("Erro de IO: " + e.getMessage());
        }

    }

    public URI uploadFile(InputStream is, String fileName, String contentType){
        try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            LOG.info("Iniciando UPLOAD");
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, is, meta));
            LOG.info("Upload Finalizado");
            return amazonS3.getUrl(bucketName, fileName).toURI();
        } catch (URISyntaxException e) {
            throw new FileException("Erro ao convertir URL para URI");
        }

    }

}
