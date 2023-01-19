package com.internal.control.repository;

import com.internal.control.model.GLCodes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface GLCodeRepository extends MongoRepository<GLCodes,Long> {
    @Query("{ 'name' : ?0'}")
    GLCodes findByName(String name);

}
