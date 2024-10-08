package com.example.shoppingdotcom.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Value("${FIREBASE_SERVICE_ACCOUNT}")
    private String serviceAccountJson;

    @Value("${firebase.bucket-name}")
    private String bucketName;

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        InputStream serviceAccount = new ByteArrayInputStream(serviceAccountJson.getBytes(StandardCharsets.UTF_8));

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(bucketName)
                .build();

        return FirebaseApp.initializeApp(options);
    }
}
