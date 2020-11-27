package com.internal.control;

import com.internal.control.helper.MainHelper;
import com.internal.control.helper.UserHelper;
import com.internal.control.model.Company;
import com.internal.control.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class ControlApplicationTests {
    @Autowired
    UserHelper user;
    @Autowired
    MainHelper main;
    @Test
    void contextLoads() {
    }

    @Test
    void saveUser()
    {
        System.out.println("saveUser testcase running");
        User user1 = new User();
        user1.setCreationDate(new Date());
        user1.setName("u1");
        user1.setPassword("u1p");
        user1.setUserId(user.getAvaiablaeUserId());
        user.saveUser(user1);


    }

    @Test
    void saveCompany(){
        Company company = new Company();
        company.setUserId(31);
        company.setCompanyName("Junaid's company");
        main.saveCompany(company);
    }

    @Test
    void testGetUser() {
        String j = user.getUser("u1", "u1p");
        System.out.println(j);
    }

}
