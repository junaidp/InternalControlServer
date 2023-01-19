package com.internal.control.helper;

import com.google.gson.Gson;
import com.internal.control.model.Project;
import com.internal.control.model.SubProcess;
import com.internal.control.model.User;
import com.internal.control.repository.ProjectRepository;
import com.internal.control.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProjectHelper {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    MongoOperations mongoOperation;
    Gson gson = new Gson();

    public String saveProject(Project project) {
        try {
            System.out.println("Saving Project" + project.getProjectName() +", user:" + project.getUserId());
            projectRepository.save(project);
            return "project saved";
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String getProjects(int userId, int companyId) {
        try {
            System.out.println("Getting project for  : '" + userId + "'}");
            Query query = new Query();
            if(companyId>0)
            query.addCriteria(Criteria.where("userId").is(userId).and("companyId").is(companyId));
           else query.addCriteria(Criteria.where("userId").is(userId));

            List<Project> projects = mongoOperation.find(query, Project.class);
            for(Project project : projects)
            System.out.println("Project found:" + project.getProjectName());

            String json = gson.toJson(projects);

            return json;
        } catch (Exception ex) {
            System.out.println("Error in getting projects  is :" + ex.getMessage());
            throw ex;
        }
    }
    //deleting project by Id
    public String deleteProjectbyId(String projectId) {
        try {
            Project project = projectRepository.findById(projectId).get();
            System.out.println("Deleting Project" + project.getProjectName());
            projectRepository.deleteById(projectId);
            return "project Deleted";
        } catch (Exception ex) {
            throw ex;
        }
    }
    //deleting project by Name
    public String deleteProjectbyName(String projectname) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("projectName").is(projectname));
            Project project = mongoOperation.findOne(query,Project.class);
            projectRepository.delete(project);
            return "project Deleted";
        } catch (Exception ex) {
            throw ex;
        }
    }
     public int getAvaiablaeUserId() {
        Long total = projectRepository.count();
        int count = total.intValue();
        return count+1;

    }


    public String deleteAll() {
        try {
            projectRepository.deleteAll();
            return "project's deleted";
        }
        catch(Exception ex)
        {
            throw ex;
        }
    }

 }
