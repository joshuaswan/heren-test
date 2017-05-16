package com.heren.his.domain.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joshua on 2016/4/26.
 */

@Entity
@Table(name = "NODES_HIERARCHY")
@XmlRootElement
public class NodesHierarchy implements Comparable<NodesHierarchy>{

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "INPUT_CODE")
    private String inputCode;

    @Column(name = "NODE_TYPE_ID")
    private Integer nodeTypeId;

    @Column(name = "NODE_ORDER")
    private Integer nodeOrder;

    @OneToMany( cascade = CascadeType.ALL,mappedBy = "nodeId")//之前写成对应testCaseId时无法使用，只有该为nodesHierarchy之后才可以，因为testCaseId中没有对应id属性
    private List<TestCase> testCaseList;

    public NodesHierarchy() {
    }

    public List<TestCase> getTestCaseList() {
        return testCaseList;
    }

    public void setTestCaseList(List<TestCase> testCaseList) {
        this.testCaseList = testCaseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public NodesHierarchy(String id, String name, String details, String parentId, String inputCode, Integer nodeTypeId, Integer nodeOrder) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.parentId = parentId;
        this.inputCode = inputCode;
        this.nodeTypeId = nodeTypeId;
        this.nodeOrder = nodeOrder;
        this.testCaseList = new ArrayList<>();
    }

    public NodesHierarchy(String name, String details, String parentId, String inputCode, Integer nodeTypeId, Integer nodeOrder) {
        this.name = name;
        this.details = details;
        this.parentId = parentId;
        this.inputCode = inputCode;
        this.nodeTypeId = nodeTypeId;
        this.nodeOrder = nodeOrder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public Integer getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(Integer nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public void setNodeOrder(Integer nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    @Override
    public String toString() {
        return "NodesHierarchy{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", parentId='" + parentId + '\'' +
                ", inputCode='" + inputCode + '\'' +
                ", nodeTypeId=" + nodeTypeId +
                ", nodeOrder=" + nodeOrder +
                '}';
    }

    @Override
    public int compareTo(NodesHierarchy nodesHierarchy) {
        return this.id.compareTo(nodesHierarchy.id);
    }
}
