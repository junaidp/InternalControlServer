package com.internal.control.api;

import com.internal.control.helper.MainHelper;
import com.internal.control.helper.UserHelper;
import com.internal.control.model.Company;
import com.internal.control.model.User;
import com.internal.control.model.UserTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MainController {

    @Autowired
    UserHelper user;
    @Autowired
    MainHelper main;

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User userEntity)
    {
        userEntity.setUserId(user.getAvaiablaeUserId());
        return user.saveUser(userEntity);
    }

    @PostMapping("/signIn")
    public String singIn(@RequestBody UserTest userTest) throws Exception
    {
        System.out.println(userTest.getName() +","+ userTest.getPassword());
        return user.getUser(userTest.getName(), userTest.getPassword());
    }

    @GetMapping("/getUserCompanies")
    public String getUserCompanies(@RequestParam int userId) throws Exception
    {
        System.out.println("Fetching companies for userId:" + userId);
        return main.getUserCompanies(userId);

    }

    @PostMapping("/saveCompany")
    public String saveCompany(@RequestBody Company company)
    {
        company.setCompanyId(user.getAvaiablaCompanyId());
        return main.saveCompany(company);
    }

    @GetMapping("/deleteAllUsers")
    public String deleteAllusers()
    {
        return user.deleteAllusers();
    }


}
