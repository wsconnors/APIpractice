package com.example.APIs.ControllerTests;

import com.example.APIs.controllers.DictionaryController;
import com.example.APIs.models.Word;
import com.example.APIs.services.DictionaryService;
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
@WebMvcTest(value = DictionaryController.class, secure = false)
public class DictionaryControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockBean
    DictionaryService dictionaryService;

    @MockBean
    RestTemplate restTemplate;

    Word mockWordObj = new Word("hello");
    String mockJson = "{\"word\":\"hello\",\"source\":null,\"definitions\":null}";

    @Test
    public void getDefinitionWordTestWordExist() throws Exception {

        when(dictionaryService.getWordObj(Mockito.anyString(),Mockito.anyString())).thenReturn(mockWordObj);

        mockMvc.perform(MockMvcRequestBuilders.get("/dictionary/anyWord"))
                .andExpect(content().string(mockJson))
                .andExpect(status().isOk());
    }

    @Test
    public void getDefinitionWordTestWordDoesNotExist() throws Exception {

        when(dictionaryService.getWordObj(Mockito.anyString(),Mockito.anyString())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/dictionary/anyWord"))
                .andExpect(status().isNoContent());
    }
}
