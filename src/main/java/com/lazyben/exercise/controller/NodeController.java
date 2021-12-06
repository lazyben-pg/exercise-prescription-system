package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.Constant;
import com.lazyben.exercise.entity.SearchResult;
import com.lazyben.exercise.service.NodeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("search")
public class NodeController {
    private final NodeSearchService nodeSearchService;

    @Autowired
    public NodeController(NodeSearchService nodeSearchService) {
        this.nodeSearchService = nodeSearchService;
    }

    /**
     * 单条查询处方接口实现
     *
     * @param symptom      查询的症状或健康人群症状
     * @param nodeType     症状 or 健康人群
     * @param relationship 想要查找的关系
     * @return 查询结果
     */
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchResult getMovieTitles(@RequestParam("symptom") String symptom,
                                       @RequestParam(value = "nodetype", required = false, defaultValue = Constant.DEFAULT_NODE_TYPE) String nodeType,
                                       @RequestParam("relationship") String relationship) {
        return nodeSearchService.getMovieTitles(symptom, nodeType, relationship);
    }

    /**
     * 某一种症状的所有处方查询
     *
     * @param symptom  查询的症状或健康人群症状
     * @param nodeType 症状 or 健康人群
     */
    @GetMapping(path = "/prescription", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SearchResult> getPrescription(@RequestParam("symptom") String symptom,
                                              @RequestParam(value = "nodetype", required = false, defaultValue = Constant.DEFAULT_NODE_TYPE) String nodeType) {
        return nodeSearchService.getPrescription(symptom, nodeType);
    }
}
