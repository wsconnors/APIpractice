package com.example.APIs.ControllerTests;


import com.example.APIs.controllers.GitHubController;
import com.example.APIs.models.GitHubUser;
import com.example.APIs.services.GitHubService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GitHubController.class, secure = false)
public class GitHubControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GitHubService gitHubService;

    @MockBean
    RestTemplate restTemplate;

    GitHubUser mockUser = new GitHubUser("Wesley","wsconnors");
    String mockJson = "{\"login\":\"wsconnors\",\"bio\":null,\"public_repos\":0,\"name\":\"Wesley\"}";

    @Test
    public void getUserTestUserExist() throws Exception {
        when(gitHubService.getUserByUserName(Mockito.anyString())).thenReturn(mockUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/gitHub/users/anyString"))
                .andExpect(content().string(mockJson))
                .andExpect(status().isOk());

    }

    @Test
    public void getUserTestUserDoesNotExist() throws Exception {
        when(gitHubService.getUserByUserName(Mockito.anyString())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/gitHub/users/anyString"))
                .andExpect(status().isNoContent());

    }
}
