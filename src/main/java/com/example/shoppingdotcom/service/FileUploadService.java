package com.example.shoppingdotcom.service;

import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadService {

    @Value("${firebase.bucket-name}")
    private String bucketName;

    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Get Firebase bucket instance
        var bucket = StorageClient.getInstance().bucket(bucketName);

        // Upload the file to Firebase Storage
        Blob blob = bucket.create(file.getOriginalFilename(), file.getInputStream(), file.getContentType());

        // Get the public URL of the uploaded file
        return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucket.getName(),
                blob.getName());
    }
}
