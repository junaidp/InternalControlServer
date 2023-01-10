package com.internal.control.helper;

import com.google.gson.Gson;
import com.internal.control.model.Company;
import com.internal.control.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainHelper {
    @Autowired
    MongoOperations mongoOperation;
    Gson gson = new Gson();
    @Autowired
    CompanyRepository companyRepository;

    public String getUserCompanies(int userId) {
        try {

            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(userId));
            //	BasicQuery query1 = new BasicQuery("{ name : '"+name+"'} , { password: '"+password+"'}");
            List<Company> companies = mongoOperation.find(query, Company.class);
            System.out.println("company found for" + userId + "");
            for (Company company : companies)
                System.out.println(company);

            String json = gson.toJson(companies);
            return json;

        } catch (Exception ex) {
            System.out.println("Error in getting companies for user :" + ex.getMessage());
            throw ex;
        }
    }


    public String saveCompany(Company company) {
        try {
            System.out.println("Saving company" + company.getCompanyName());
            companyRepository.save(company);
            return "company saved";
        } catch (Exception ex) {
            System.out.println("Error in saving company" + ex);
            throw ex;
        }
    }
}
