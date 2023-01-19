package com.internal.control.helper;

import com.google.gson.Gson;
import com.internal.control.model.Sct;
import com.internal.control.model.SubProcess;
import com.internal.control.model.User;
import com.internal.control.model.companyProcess;
import com.internal.control.repository.ProcessRepository;
import com.internal.control.repository.SctRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SctHelper {
    @Autowired
    MongoOperations mongoOperation;
    Gson gson = new Gson();
    @Autowired
    SctRepository sctrepository;

    public String saveSctProcess(Sct sct) {
        try {
            System.out.println("Saving sct " + sct.getSctName() + ", sct Id: " + sct.getId());
            sctrepository.save(sct);
            return "sct saved";
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getSctProcesses(int sctProcessId)
    {
        try {
            System.out.println("Getting sctprocesses for  : '" + sctProcessId + "'}");
            Query query = new Query();
            query.addCriteria(Criteria.where("sctprocessId").is(sctProcessId));
            List<Sct> scts = mongoOperation.find(query, Sct.class);
            for(Sct sct : scts)
                System.out.println("sctprocesses found:" + sct.getSctName());
            String json = gson.toJson(scts);
            return json;
        } catch (Exception ex) {
            System.out.println("Error in getting projects  is :" + ex.getMessage());
            throw ex;
        }
    }

    public String updateSct(Sct sct)
    {
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(sct.getId()));
            User updateduser = mongoOperation.findOne(query,User.class);
            if(updateduser.equals(null))
            {
                return "could not find Sct";
            }
            else {
                sctrepository.save(sct);
                return "Updated Successfully";
            }
        }catch(Exception e)
        {
            return "Error :" + e;
        }
    }

    public String deleteSctProcess(int sctProcessId) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("sctProcessId").is(sctProcessId));
            Sct sct = mongoOperation.findOne(query, Sct.class);
            System.out.println("Deleting process" + sct.getSctName());
            sctrepository.delete(sct);
            return "process Deleted";
        } catch (Exception ex) {
            throw ex;
        }
    }
}
