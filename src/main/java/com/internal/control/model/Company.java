package com.internal.control.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Company {

    @Id
    private String id;
    private String companyName;
    private int userId;
    private int companyId;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private boolean completed = false;

    public boolean isCompleted() {
        return completed;
    }

    public void setComplleted(boolean completed) {
        this.completed = completed;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


}
