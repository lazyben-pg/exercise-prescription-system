package com.lazyben.exercise.controller;

import com.lazyben.exercise.entity.Constant;
import com.lazyben.exercise.entity.SearchResult;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class NodeController {

    private final Driver driver;

    public NodeController(Driver driver) {
        this.driver = driver;
    }

    /**
     * 单条查询处方接口实现
     *
     * @param symptom      查询的症状或健康人群症状
     * @param nodeType     症状 or 健康人群
     * @param relationship 想要查找的关系
     * @return 查询结果
     */
    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public SearchResult getMovieTitles(@RequestParam("symptom") String symptom,
                                       @RequestParam(value = "nodetype", required = false, defaultValue = Constant.DEFAULT_NODE_TYPE) String nodeType,
                                       @RequestParam("relationship") String relationship) {
        return getPrescriptionWithRelationShip(symptom, nodeType, relationship);
    }

    /**
     * 根据节点名称和关系查询某一条处方
     *
     * @param symptom      查询的症状或健康人群症状
     * @param nodeType     症状 or 健康人群
     * @param relationship 想要查找的关系
     */
    private SearchResult getPrescriptionWithRelationShip(String symptom, String nodeType, String relationship) {
        try (Session session = driver.session()) {
            String cypher = cypherBuilder(nodeType, symptom, relationship);
            System.out.println(cypher);
            final List<String> list = session.run(cypher)
                    .list(r -> r.get("m").asNode().get("name").asString());
            System.out.println(list);
            return new SearchResult(symptom, nodeType, relationship, list);
        }
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
        return Stream.of(Constant.RELATIONSHIPS)
                .map((relationship) -> getPrescriptionWithRelationShip(symptom, nodeType, relationship))
                .collect(Collectors.toList());
    }

    /**
     * 构造 cypher 查询语句
     *
     * @param nodeType     节点类型
     * @param nodeName     节点名称
     * @param relationType 关系类型
     */
    private String cypherBuilder(String nodeType, String nodeName, String relationType) {
        // MATCH (n:`症状`)-[r:`有氧强度`]-(m) where n.name='超重或超胖' RETURN m
        return String.format("MATCH (n:`%s`)-[r:`%s`]-(m) where n.name=\"%s\" RETURN m", nodeType, relationType, nodeName);
    }
}
