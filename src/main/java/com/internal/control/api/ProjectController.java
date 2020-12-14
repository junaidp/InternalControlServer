package com.internal.control.api;

import com.internal.control.helper.MainHelper;
import com.internal.control.helper.ProjectHelper;
import com.internal.control.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("project")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProjectController {

    @Autowired
    ProjectHelper projectHelper;

    @PostMapping("/saveProject")
    public String saveProject(@RequestBody Project projectEntity)
    {
        projectEntity.setProjectId(projectHelper.getAvaiablaeUserId());
        return projectHelper.saveProject(projectEntity);
    }

    @GetMapping("/getProjects")
    public String getProjects(@RequestParam int userId, @RequestParam int companyId) throws Exception
    {
        System.out.println("Fetching projects for userId:" + userId);
        return projectHelper.getProjects(userId, companyId );

    }

   }
