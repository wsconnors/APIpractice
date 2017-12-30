package com.example.APIs.services;

import com.example.APIs.models.GitHubUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;

@Service
public class GitHubService {

    Logger logger = LoggerFactory.getLogger(GitHubService.class);

    private HashMap<String, GitHubUser> storedUsers = new HashMap<>();

    @Autowired
    RestTemplate restTemplate;

    public GitHubUser getUserByUserName(String userName){

        if (storedUsers.containsKey(userName)) {
            logger.info("Local call");
            return storedUsers.get(userName);
        }
        try {
            GitHubUser user = this.callAPI(userName);
            storedUsers.put(userName, user);
            return user;
        } catch (Exception e){
            logger.warn(e.getMessage());
            return null;
        }

    }

    private GitHubUser callAPI(String userName) throws Exception{
        logger.info("API call");
        String uri = "https://api.github.com/users/" + userName;
        return restTemplate.getForObject(uri, GitHubUser.class);
    }
}
