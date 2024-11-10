package com.mehmetyalvacli.microservice.repository;

import com.mehmetyalvacli.microservice.models.TransactionLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLogsRepository extends MongoRepository<TransactionLog, String> {
}