package com.lazyben.exercise.entity;

import java.util.List;

public class SearchResult {
    private final String symptom;
    private final String nodeType;
    private final String relationship;
    private final List<String> result;

    public SearchResult(String symptom, String nodeType, String relationship, List<String> result) {
        this.symptom = symptom;
        this.nodeType = nodeType;
        this.relationship = relationship;
        this.result = result;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getNodeType() {
        return nodeType;
    }

    public String getRelationship() {
        return relationship;
    }

    public List<String> getResult() {
        return result;
    }
}
