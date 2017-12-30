package com.example.APIs.services;

import com.example.APIs.models.Definition;
import com.example.APIs.models.Word;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;


@Service
public class DictionaryService {

    Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    @Autowired
    private RestTemplate restTemplate;

    private String uri = "https://od-api.oxforddictionaries.com/api/v1/entries/";

    public Word getWordObj(String word, String lang){
        HttpHeaders headers = new HttpHeaders();

        headers.set("app_id", "e5cc32c5");
        headers.set("app_key", "b921fe46f5da4ea9dd15b103db888d28");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(uri + lang + "/" + word, HttpMethod.GET, entity, String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            ObjectMapper mapper = new ObjectMapper();

            try {
                JsonNode root  = mapper.readTree(response.getBody());
                try {
                    JsonNode results = root.get("results");
                    try{
                        String source;
                        ArrayList<Definition> definitionsList;
                        try{
                            source = root.get("metadata").get("provider").textValue();
                        }catch (NullPointerException ne){
                            logger.warn("Invalid source format");
                            source = null;
                        }
                        try{
                            JsonNode senses = results.get(0).get("lexicalEntries").get(0).get("entries").get(0).get("senses");
                            definitionsList = new ArrayList<>();
                            for(JsonNode sense: senses){
                                try{
                                    String definition = sense.get("definitions").get(0).textValue();
                                    ArrayList<String> exampleList;
                                    try{
                                        JsonNode examples = sense.get("examples");
                                        exampleList = new ArrayList<>();
                                        for (JsonNode example : examples) {
                                            try{
                                                exampleList.add(example.get("text").textValue());
                                            }catch (NullPointerException ne){
                                                logger.warn("Invalid example format");
                                            }
                                        }
                                    }catch (NullPointerException ne) {
                                        logger.warn("Invalid examples format");
                                        exampleList = null;
                                    }
                                    definitionsList.add(new Definition(definition,exampleList));
                                }catch (NullPointerException ne){
                                    logger.warn("Invalid definition format");
                                }
                            }
                        }catch (NullPointerException ne){
                            logger.warn("Invalid definitions format");
                            definitionsList = null;
                        }
                        return new Word(word,source,definitionsList);
                    } catch (NullPointerException ne){
                        logger.warn("Invalid format");

                    }
                }catch (NullPointerException ne){
                    logger.warn("Body of response is null");
                    return null;
                }
            } catch (IOException ioe){
                logger.warn("Response does not have a body");
                logger.warn(ioe.getMessage());
                return null;
            }

        }

        return null;

    }
}
