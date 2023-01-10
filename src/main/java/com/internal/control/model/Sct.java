package com.internal.control.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class Sct {
    @Id
    private String Id;
    private String sctName;
    private String sctProcessId;

    public Sct() {
    }

    public Sct(String id, String sctName, String sctProcessId) {
        Id = id;
        this.sctName = sctName;
        this.sctProcessId = sctProcessId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSctName() {
        return sctName;
    }

    public void setSctName(String sctName) {
        this.sctName = sctName;
    }

    public String getSctProcessId() {
        return sctProcessId;
    }

    public void setSctProcessId(String sctProcessId) {
        this.sctProcessId = sctProcessId;
    }
}
