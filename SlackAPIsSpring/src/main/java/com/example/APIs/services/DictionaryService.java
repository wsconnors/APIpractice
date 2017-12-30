package com.example.APIs.services;

import com.example.APIs.models.Definition;
import com.example.APIs.models.Word;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


@Service
public class DictionaryService {

    Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    @Autowired
    private RestTemplate restTemplate;

    String uri = "https://od-api.oxforddictionaries.com/api/v1/entries/";

    public Word getWordObj(String word, String lang){
        HttpHeaders headers = new HttpHeaders();

        headers.set("app_id", "e5cc32c5");
        headers.set("app_key", "b921fe46f5da4ea9dd15b103db888d28");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {

            ResponseEntity<String> response = restTemplate.exchange(uri + lang + "/" + word, HttpMethod.GET, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root  = mapper.readTree(response.getBody());
            JsonNode results = root.get("results");
            JsonNode senses = results.get(0).get("lexicalEntries").get(0).get("entries").get(0).get("senses");

            ArrayList<Definition> definitionsList = new ArrayList<>();

            for(JsonNode sense: senses){
                if(sense.get("definitions") != null) {
                    definitionsList.add(getDefinition(sense));
                }
            }

            String source = root.get("metadata").get("provider").textValue();

            return new Word(word,source,definitionsList);
        } catch (Exception e){
            logger.warn("Error: "+e.getMessage());
            return null;
        }

    }

    private Definition getDefinition(JsonNode sense){
        JsonNode examples = sense.get("examples");

        ArrayList<String> exampleList = null;
        if (examples != null) {
            exampleList = getExamples(examples);
        }
        String definition = sense.get("definitions").get(0).textValue();
        return new Definition(definition, exampleList);
    }

    private ArrayList<String> getExamples(JsonNode examples){
        ArrayList<String> exampleList = new ArrayList<>();
        for (JsonNode example : examples) {
            exampleList.add(example.get("text").textValue());
        }
        return exampleList;
    }
}
