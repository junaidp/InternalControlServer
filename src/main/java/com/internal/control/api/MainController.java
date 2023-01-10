package com.internal.control.api;

import com.internal.control.helper.*;
import com.internal.control.model.*;
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

    @Autowired
    ProcessHelper process;

    @Autowired
    SubProcessHelper subprocess;

    @Autowired
    SctHelper sct;

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


    @PostMapping("/saveProcess")
    public String saveProcess(@RequestBody companyProcess processEntity)
    {
        return process.saveProcess(processEntity);
    }
    @GetMapping("/getProcess")
    public String getProcess(@RequestParam int companyId){
            return process.getProcesses(companyId);
    }

    @PostMapping("/deleteProcess")
    public String deleteProcess(@RequestParam int processId)
    {
        return process.deleteProcess(processId);
    }
    @GetMapping("/getUserCompanies")
    public String getUserCompanies(@RequestParam int userId) throws Exception
    {
        System.out.println("Fetching companies for userId:" + userId);
        return main.getUserCompanies(userId);
    }

    //save new subprocess
    @PostMapping("/saveSubProcess")
    public String saveSubProcess(@RequestBody SubProcess subprocessEntity){
        return subprocess.saveSubProcess(subprocessEntity);
    }
    @PostMapping("/deleteSubProcess")
    public String deletSubProcess(@RequestParam int subprocessid)
    {
        return subprocess.deleteSubProcess(subprocessid);
    }

    @GetMapping("/getSubProcess")
    public String getSubProcess(@RequestParam int subprocessid)
    {
        return subprocess.getsubProcesses(subprocessid);
    }

    @PostMapping("/saveSctProcess")
    public String saveSctProcess(@RequestBody Sct sctEntity)
    {
        return sct.saveSctProcess(sctEntity);
    }


    @PostMapping("/deleteSctProcess")
    public String deleteSctProcess(@RequestParam int sctid)
    {
        return sct.deleteSctProcess(sctid);
    }
    @GetMapping("/getSctProcess")
    public String getSctProcess(@RequestParam int sctprocessid)
    {
        return sct.getSctProcesses(sctprocessid);
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
