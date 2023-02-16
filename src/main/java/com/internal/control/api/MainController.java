package com.internal.control.api;

import com.internal.control.crawler.CrawlerTest;
import com.internal.control.helper.*;
import com.internal.control.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    GLcodesHelper glcode;

    @Autowired
    CrawlerTest crawlerTest;

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

    @GetMapping("/getallusers")
    public String getAllUsers()
    {
        return user.getAllusers();
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestBody MultipartFile file)
    {

        return glcode.uploadFile(file);
    }

    @GetMapping("/deleteAllRecords")
    public String deleteAllRecords()
    {
        return glcode.deleteAllrecords();
    }

    @GetMapping("/readFile")
    public String readFile(){
        return glcode.readFile();
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

    @PostMapping("/updateProcess")
    public String updateProcess(@RequestBody companyProcess updateprocess)
    {
        return process.updateProcess(updateprocess);
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

    @PostMapping("/updatesubprocess")
    public String updateSubProcess(@RequestBody SubProcess updatesubprocess)
    {
        return subprocess.updateSubprocess(updatesubprocess);
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

    @PostMapping("/updatesctprocess")
    public String updateSctProcess(@RequestBody Sct updatesct)
    {
        return sct.updateSct(updatesct);
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

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody User updateuser)
    {
        return user.updateUser(updateuser);
    }
    @GetMapping("/deleteAllUsers")
    public String deleteAllusers()
    {
        return user.deleteAllusers();
    }

}
