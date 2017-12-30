package com.example.APIs.controllers;


import com.example.APIs.models.GitHubUser;
import com.example.APIs.services.GitHubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GitHubController {

    Logger logger = LoggerFactory.getLogger(GitHubController.class);

    @Autowired
    GitHubService gitHubService;

    @GetMapping("/gitHub/users/{userName}")
    public ResponseEntity<GitHubUser> getUser(@PathVariable String userName){
        logger.info("Attempting to find user with user name: {}",userName);

        GitHubUser user = gitHubService.getUserByUserName(userName);

        if(user == null){
            logger.info("No user found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("User found");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
