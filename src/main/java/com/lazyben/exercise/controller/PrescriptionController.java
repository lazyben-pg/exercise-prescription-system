package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.Constant;
import com.lazyben.exercise.entity.HumanStature;
import com.lazyben.exercise.entity.SearchResult;
import com.lazyben.exercise.service.HumanStatureService;
import com.lazyben.exercise.service.NodeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrescriptionController {
    private final HumanStatureService humanStatureService;
    private final NodeSearchService nodeSearchService;

    @Autowired
    public PrescriptionController(HumanStatureService humanStatureService, NodeSearchService nodeSearchService) {
        this.humanStatureService = humanStatureService;
        this.nodeSearchService = nodeSearchService;
    }

    @PostMapping(path = "/prescription", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SearchResult> getPrescriptionFromHumanStatureData(@RequestBody HumanStature statureData) {
        System.out.println(statureData);
        double[] data = statureData.createData();
        final String humanStature = Constant.HUMANSTATURES[humanStatureService.getHumanStature(data)];
        if ("肥胖".equals(humanStature) || "超重".equals(humanStature)) {
            return nodeSearchService.getPrescription("超重或超胖", Constant.DEFAULT_NODE_TYPE);
        }
        return null;
    }
}
