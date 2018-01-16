package com.example.APIs.ServiceTests;

import com.example.APIs.models.Definition;
import com.example.APIs.models.Word;
import com.example.APIs.services.DictionaryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DictionaryService.class, secure = false)
public class DictionaryServiceTest {

    @Autowired
    private DictionaryService dictionaryService;

    @MockBean
    private RestTemplate restTemplate;


    private ResponseEntity<String> mockGoodResponse;
    private ResponseEntity<String> mockPartialResponse;
    private ResponseEntity<String> mockBadResponse;
    private ResponseEntity<String> mockNoResponse;

    private Word mockWordObj;

    @Before
    public void setUp(){
        String mockGoodReturn = "{\"metadata\":{\"provider\":\"ME\"},\"results\":[{\"id\":\"TESTWORD\",\"lexicalEntries\":[{\"entries\":[{\"senses\":[{\"definitions\":[\"this is annoying to type out\"],\"examples\":[{\"text\":\"this stupid shit\"}]}]}]}]}]}";
        mockGoodResponse = new ResponseEntity<>(mockGoodReturn, HttpStatus.OK);

        String mockPartialReturn = "{\"metadata\":{\"provider\":\"ME\"},\"results\":[{\"id\":\"TESTWORD\"}]}";
        mockPartialResponse = new ResponseEntity<>(mockPartialReturn, HttpStatus.OK);

        String mockBadReturn = "{}";
        mockBadResponse = new ResponseEntity<>(mockBadReturn,HttpStatus.OK);

        mockNoResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ArrayList<String> mockExamples = new ArrayList<>();
        mockExamples.add("this stupid shit");
        ArrayList<Definition> mockDefinitionArray = new ArrayList<>();
        Definition mockDefinition = new Definition("this is annoying to type out",mockExamples);
        mockDefinitionArray.add(mockDefinition);
        mockWordObj = new Word("TESTWORD","ME",mockDefinitionArray);

    }

    @Test
    public void getWordObjTestWordGoodReturn(){

        when(restTemplate.exchange(Mockito.anyString(),Mockito.any(),Mockito.any(),Mockito.<Class<String>>any()))
                .thenReturn(mockGoodResponse);

        Word actual = dictionaryService.getWordObj("TESTWORD","en");

        Assert.assertEquals(mockWordObj.toString(),actual.toString());
    }

    @Test
    public void getWordObjTestWordPartialReturn(){
        when(restTemplate.exchange(Mockito.anyString(),Mockito.any(),Mockito.any(),Mockito.<Class<String>>any()))
                .thenReturn(mockPartialResponse);

        Word actual = dictionaryService.getWordObj("TESTWORD","en");

        Assert.assertEquals(mockWordObj.getSource(),actual.getSource());
    }

    @Test
    public void getWordObjTestWordBadReturn(){
        when(restTemplate.exchange(Mockito.anyString(),Mockito.any(),Mockito.any(),Mockito.<Class<String>>any()))
                .thenReturn(mockBadResponse);

        Word actual = dictionaryService.getWordObj("TESTWORD","en");

        Assert.assertEquals(mockWordObj.getWord(),actual.getWord());
    }

    @Test
    public void getWordObjTestWordNoReturn(){
        when(restTemplate.exchange(Mockito.anyString(),Mockito.any(),Mockito.any(),Mockito.<Class<String>>any()))
                .thenReturn(mockNoResponse);

        Word actual = dictionaryService.getWordObj("TESTWORD","en");

        Assert.assertNull(actual);
    }
}
