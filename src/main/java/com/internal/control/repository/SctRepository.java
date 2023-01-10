package com.internal.control.repository;

import com.internal.control.model.Sct;
import com.internal.control.model.companyProcess;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SctRepository extends MongoRepository<Sct,String> {
    @Query("{ 'name' : ?0'}")
    companyProcess findByName(String name);
}
