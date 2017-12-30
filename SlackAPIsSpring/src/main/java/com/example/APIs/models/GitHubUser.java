package com.example.APIs.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUser {

    @JsonProperty("login")
    private String userName;

    @JsonProperty("bio")
    private String bio;

    @JsonProperty("public_repos")
    private int publicRepos;

    @JsonProperty("name")
    private String fullName;

    public GitHubUser(){
    }

    public GitHubUser(String name,String userName){
        this.fullName = name;
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }
}
