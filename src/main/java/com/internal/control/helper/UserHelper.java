package com.internal.control.helper;

import com.google.gson.Gson;
import com.internal.control.model.User;
import com.internal.control.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserHelper {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MongoOperations mongoOperation;
    Gson gson = new Gson();

    public String saveUser(User user) {
        try {
            System.out.println("Saving user" + user.getName() +", pwd:" + user.getPassword());
            userRepository.save(user);
            return "user saved";
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getUser(String name, String password) {
        try {

            System.out.println("{ name : '" + name + "'}");
            System.out.println("{ password : '" + password + "'}");
            Query query = new Query();
            query.addCriteria(Criteria.where("name").is(name).and("password").is(password));
            //	BasicQuery query1 = new BasicQuery("{ name : '"+name+"'} , { password: '"+password+"'}");
            System.out.println("ff");
            User user = mongoOperation.findOne(query, User.class);
            System.out.println(user);

            String json = gson.toJson(user);

            return json;
        } catch (Exception ex) {
            System.out.println("Error is :" + ex.getMessage());
            throw ex;
        }
    }
    public String getUserWithId(String userId) {
        String userJson;
        try {
            Optional<User> user =  userRepository.findById(userId);
            userJson = gson.toJson(user);
        }catch(Exception ex)
        {
            throw ex;
        }
        return userJson;
    }

    public int getAvaiablaeUserId() {
        Long total = userRepository.count();
        int count = total.intValue();
        return count+1;

    }

    public String deleteAll() {
        try {
            userRepository.deleteAll();
            return "user's deleted";
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

    public String deleteAllusers() {
        try {
            userRepository.deleteAll();
            return "All users deletete";
        }catch(Exception ex)
        {
            System.out.println("Error ind eleting all users");
            throw ex;
        }
    }

}
