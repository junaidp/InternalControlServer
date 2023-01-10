package com.internal.control.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SubProcess {
    @Id
    private String id;

    private String subProcessName;
    private String processId;

    public SubProcess() {
    }

    public SubProcess(String id, String subProcessName, String processId) {
        this.id = id;
        this.subProcessName = subProcessName;
        this.processId = processId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubProcessName() {
        return subProcessName;
    }

    public void setSubProcessName(String subProcessName) {
        this.subProcessName = subProcessName;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
