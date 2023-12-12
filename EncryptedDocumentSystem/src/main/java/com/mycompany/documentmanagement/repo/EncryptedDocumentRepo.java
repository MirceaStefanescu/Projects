package com.mycompany.documentmanagement.repo;

import com.mycompany.documentmanagement.model.EncryptedDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncryptedDocumentRepo extends MongoRepository<EncryptedDocument, String> {
}
