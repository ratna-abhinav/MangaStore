package com.example.shoppingdotcom.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Value("classpath:firebase-service-config.json")
    private Resource serviceAccountResource;

    @Value("${firebase.bucket-name}")
    private String bucketName;

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        InputStream serviceAccount = serviceAccountResource.getInputStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(bucketName)
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
