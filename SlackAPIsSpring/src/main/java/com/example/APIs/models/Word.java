package com.example.APIs.models;

import java.util.List;

public class Word {

    private String word;
    private String source;
    private List<Definition> definitions;

    public Word(String word, String source, List<Definition> definitions){
        this.word = word;
        this.source = source;
        this.definitions = definitions;
    }

    public Word(String word){
        this.word = word;
    }

    public String getWord(){
        return this.word;
    }

    public String getSource() {
        return source;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    @Override
    public String toString(){
        String definitionsToString = "";

        if( definitions != null){
            definitionsToString = definitions.toString();
        }

        return "Word: " + word + ", Source: " + source + " Definitions: "+ definitionsToString;
    }
}
