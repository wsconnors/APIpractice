package com.example.APIs.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Definition{
    private String definition;
    private ArrayList<String> examples;

    public Definition(String definition, ArrayList<String> examples){
        this.definition = definition;
        this.examples = examples;
    }

    public String getDefinition() {
        return definition;
    }

    public ArrayList<String> getExamples() {
        return examples;
    }

    @Override
    public String toString(){
        return " Definition: " + definition +
                " Examples: " + examples;
    }
}
