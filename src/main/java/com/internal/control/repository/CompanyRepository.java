package com.internal.control.repository;

import com.internal.control.model.Company;
import com.internal.control.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CompanyRepository extends MongoRepository<Company, String> {

    @Query("{ 'companyName' : ?0'}")
    User findByName(String name);

}
