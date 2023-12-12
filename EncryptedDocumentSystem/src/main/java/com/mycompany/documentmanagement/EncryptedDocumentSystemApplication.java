package com.mycompany.documentmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EncryptedDocumentSystemApplication {
    private static final Logger logger = LoggerFactory.getLogger(EncryptedDocumentSystemApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(EncryptedDocumentSystemApplication.class, args);
            logger.info("Starting EncryptedDocumentSystemApplication");
        } catch (NullPointerException e) {
            logger.error("Error starting EncryptedDocumentSystemApplication: " + e.getMessage(), e);
        }
    }
}
