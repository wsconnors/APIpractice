package com.example.APIs.controllers;

import com.example.APIs.models.Word;
import com.example.APIs.services.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DictionaryController {

    Logger logger = LoggerFactory.getLogger(DictionaryController.class);

//    @Autowired
    private DictionaryService dictionaryService;


    @Autowired
    public DictionaryController(DictionaryService disc){
        dictionaryService=disc;
    }

    @GetMapping("/dictionary/{word}")
    public ResponseEntity<Word> getDefinitionWord(@PathVariable String word){
        logger.info("Searching for word: "+word);

        String lang = "en"; // can add path variable to search different languages
        Word wordObj = dictionaryService.getWordObj(word,lang);

        if (wordObj == null){
            logger.info("No definition found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Definition found");
        return new ResponseEntity<>(wordObj,HttpStatus.OK);
    }

}
