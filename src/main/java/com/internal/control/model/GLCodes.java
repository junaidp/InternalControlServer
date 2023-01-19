package com.internal.control.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document
public class GLCodes {

    public double GLcodes;

    public String Description;

    public GLCodes(double GLcodes, String description) {
        this.GLcodes = GLcodes;
        Description = description;
    }

    public GLCodes() {

    }

    public double getGLCodes() {
        return GLcodes;
    }

    public void setGLCodes(double GLcodes) {
        this.GLcodes = GLcodes;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
