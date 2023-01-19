package com.internal.control.helper;

import com.google.gson.Gson;
import com.internal.control.model.SubProcess;
import com.internal.control.model.User;
import com.internal.control.model.companyProcess;
import com.internal.control.model.Project;
import com.internal.control.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcessHelper {
    @Autowired
    MongoOperations mongoOperation;
    Gson gson = new Gson();
    @Autowired
    ProcessRepository processRepository;


    //save new process
    public String saveProcess(companyProcess companyProcess) {
        try {
                System.out.println("Saving process " + companyProcess.getprocessName() + ", process Id: " + companyProcess.getId());
                processRepository.save(companyProcess);
                return "process saved";
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getProcesses(int companyId)
    {
        try {
            System.out.println("Getting processes for  : '" + companyId + "'}");
            Query query = new Query();
                query.addCriteria(Criteria.where("companyId").is(companyId));
        List<companyProcess> companyProcesses = mongoOperation.find(query, companyProcess.class);
        for(companyProcess companyProcess : companyProcesses)
            System.out.println("Project found:" + companyProcess.getprocessName());
        String json = gson.toJson(companyProcesses);
        return json;
        } catch (Exception ex) {
            System.out.println("Error in getting projects  is :" + ex.getMessage());
            throw ex;
        }
    }

    public String updateProcess(companyProcess process)
    {
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(process.getId()));
            User updateduser = mongoOperation.findOne(query,User.class);
            if(updateduser.equals(null))
            {
                return "could not find process";
            }
            else {
                processRepository.save(process);
                return "Updated Successfully";
            }
        }catch(Exception e)
        {
            return "Error :" + e;
        }
    }

    public String deleteProcess(int processId) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("processId").is(processId));
            companyProcess process = mongoOperation.findOne(query, companyProcess.class);
            System.out.println("Deleting process" + process.getprocessName());
            processRepository.delete(process);
            return "process Deleted";
        } catch (Exception ex) {
            throw ex;
        }
    }
}
