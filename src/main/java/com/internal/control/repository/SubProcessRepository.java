package com.internal.control.repository;

import com.internal.control.model.SubProcess;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface SubProcessRepository extends MongoRepository<SubProcess,String> {
    @Query("{ 'name' : ?0'}")
    SubProcess findByName(String name);
}
