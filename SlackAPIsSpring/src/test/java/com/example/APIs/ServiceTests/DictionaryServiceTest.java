//package com.example.dictionary.services;
//
//import Word;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.web.client.RestTemplate;
//
//import static junit.framework.TestCase.fail;
//import static org.mockito.Mockito.when;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(value = DictionaryService.class, secure = false)
//public class DictionaryServiceTest {
//
//    @Autowired
//    private DictionaryService dictionaryService;
//
//    @MockBean
//    private RestTemplate restTemplate;
//
//    ResponseEntity<WordJson> mockResponse = new ResponseEntity<>(new WordJson("me"),HttpStatus.OK);
//
//    WordJson mockWordJson = new WordJson("me");
//
//
//
//    @Test
//    public void getWordObjTestWordExist(){
//
//        when(restTemplate.exchange(Mockito.anyString(),Mockito.any(),Mockito.any(),Mockito.<Class<WordJson>>any()))
//                .thenReturn(mockResponse);
//
//        Word actual;
//        try {
//            actual = dictionaryService.getWordObj("anyString", "anyString");
//            System.out.println("in try"+actual);
//        }catch (Exception e){
//            actual = null;
//        }
//
////        System.out.println(actual);
////        Assert.assertEquals();
//
//    }
//}
