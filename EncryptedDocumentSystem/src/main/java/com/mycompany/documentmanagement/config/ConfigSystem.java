package com.mycompany.documentmanagement.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.documentmanagement.model.Permission;
import com.mycompany.documentmanagement.model.Role;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Configuration
@Component
public class ConfigSystem {
    private static final Logger logger = LogManager.getLogger(ConfigSystem.class);
    private final MongoClient mongoClient;
    private final String databaseName;
    private final String rolesCollectionName;
    private final String permissionsCollectionName;

    public ConfigSystem(MongoClient mongoClient,
                        @Value("${spring.data.mongodb.database}") String databaseName,
                        @Value("${spring.data.mongodb.rolesCollection}")
                        String rolesCollectionName,
                        @Value("${spring.data.mongodb.permissionsCollection}")
                        String permissionsCollectionName) {
        this.mongoClient = mongoClient;
        this.databaseName = databaseName;
        this.rolesCollectionName = rolesCollectionName;
        this.permissionsCollectionName = permissionsCollectionName;
    }

    @PostConstruct
    public void loadDefaultEnums() {
        try {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> rolesCollection = database.getCollection(rolesCollectionName);
            MongoCollection<Document> permissionsCollection =
                    database.getCollection(permissionsCollectionName);
            Set<String> existingRoleNames = new HashSet<>(
                    rolesCollection.distinct("name", String.class).into(new ArrayList<>()));
            Set<String> existingPermissionNames = new HashSet<>(
                    permissionsCollection.distinct("name", String.class).into(new ArrayList<>()));
            for (Role role : Role.values()) {
                if (!existingRoleNames.contains(role.getName())) {
                    Document roleDoc = new Document().append("name", role.getName())
                            .append("description", role.getDescription());
                    rolesCollection.insertOne(roleDoc);
                    existingRoleNames.add(role.getName());
                    logger.info("Role '{}' inserted into the database.", role.getName());
                } else {
                    logger.info("Role '{}' already exists in the database.", role.getName());
                }
            }
            for (Permission permission : Permission.values()) {
                if (!existingPermissionNames.contains(permission.getName())) {
                    Document permissionDoc = new Document().append("name", permission.getName())
                            .append("description", permission.getDescription());
                    permissionsCollection.insertOne(permissionDoc);
                    existingPermissionNames.add(permission.getName());
                    logger.info("Permission '{}' inserted into the database.", permission.getName());
                } else {
                    logger.info("Permission '{}' already exists in the database.", permission.getName());
                }
            }
        } catch (Exception e) {
            // Log and handle the exception appropriately
            logger.error("Error occurred during loading default enums: " + e.getMessage(), e);
            throw new RuntimeException("Error occurred during loading default enums.", e);
        }
    }
}
