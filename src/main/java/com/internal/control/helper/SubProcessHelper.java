package com.internal.control.helper;

import com.google.gson.Gson;
import com.internal.control.model.SubProcess;
import com.internal.control.model.companyProcess;
import com.internal.control.repository.ProcessRepository;
import com.internal.control.repository.SubProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SubProcessHelper {
    @Autowired
    MongoOperations mongoOperation;
    Gson gson = new Gson();
    @Autowired
    SubProcessRepository subProcessRepository;


    //save new process
    public String saveSubProcess(SubProcess subprocess) {
        try {
            System.out.println("Saving sub process " + subprocess.getSubProcessName() + ", sub process Id: " + subprocess.getId());
            subProcessRepository.save(subprocess);
            return "subprocess saved";
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getsubProcesses(int processId)
    {
        try {
            System.out.println("Getting sub processes for  : '" + processId + "'}");
            Query query = new Query();
            query.addCriteria(Criteria.where("processId").is(processId));
            List<SubProcess> subprocesses = mongoOperation.find(query, SubProcess.class);
            for(SubProcess subprocess : subprocesses)
                System.out.println("Sub Process found:" + subprocess.getSubProcessName());
            String json = gson.toJson(subprocesses);
            return json;
        } catch (Exception ex) {
            System.out.println("Error in getting processes  is :" + ex.getMessage());
            throw ex;
        }
    }

    public String deleteSubProcess(int subprocessId) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("subprocessId").is(subprocessId));
            SubProcess subprocess = mongoOperation.findOne(query, SubProcess.class);
            System.out.println("Deleting sub process" + subprocess.getSubProcessName());
            subProcessRepository.delete(subprocess);
            return "process Deleted";
        } catch (Exception ex) {
            throw ex;
        }
    }

}
