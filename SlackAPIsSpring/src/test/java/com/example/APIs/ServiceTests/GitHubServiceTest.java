package com.example.APIs.ServiceTests;

import com.example.APIs.models.GitHubUser;
import com.example.APIs.services.GitHubService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GitHubService.class, secure = false)
public class GitHubServiceTest {

    @Autowired
    GitHubService gitHubService;

    @MockBean
    RestTemplate restTemplate;

    GitHubUser mockUser;

    @Before
    public void setUp(){
        mockUser = new GitHubUser("Wesley","wsconnors");
    }

    @Test
    public void getUserByUserNameTest(){
        when(restTemplate.getForObject(Mockito.anyString(),Mockito.any())).thenReturn(mockUser);

//        GitHubUser actual = gitHubService.getUserByUserName("anyString");
//
//        Assert.assertEquals(mockUser.getFullName(),actual.getFullName());

    }



}
