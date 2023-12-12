package com.mycompany.documentmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Documents") @Data @AllArgsConstructor @NoArgsConstructor public class EncryptedDocument {
    private String name;
    private String fileType;
    private int size;
    private String owner;
    private List<Permission> permissions;

}