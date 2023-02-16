package com.internal.control.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTest {


    private String email;
    private String name;
    private String password;

    public UserTest(@JsonProperty("name")String name, @JsonProperty("password")String password , @JsonProperty("email")String email)
    {
        this.email = email;
        this.name = name;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

