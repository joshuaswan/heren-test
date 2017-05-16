package com.heren.his.domain.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Created by joshua on 2016/4/26.
 */

@Entity
@Table(name = "TEST_CASE")
@XmlRootElement
public class TestCase implements Comparable<TestCase>{

    @Id
    @Column(name = "TEST_CASE_ID")
    private String testCaseId;

    @Column(name="NODE_ID")
    private String nodeId;

    @Column(name = "TEST_CASE_TITLE")
    private String testCaseTitle;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "PRECONDITIONS")
    private String preconditions;

    @Column(name = "RESULT")
    private String result;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "MODIFICATION_TIME")
    private Date modificationTime;

    @Column(name = "EXECUTION_TYPE")
    private Integer executionType;

    @Column(name = "CODE_PATH")
    private String codePath;

    @Column(name = "RUN_STATUS")
    private Integer runStatus;

    @Column(name = "SAVE_PHOTO")
    private Integer savePhoto;

//    @ManyToOne
//    @JoinColumn(name = "TEST_CASE_ID" ,referencedColumnName = "ID",insertable = false,updatable = false)
//    private NodesHierarchy nodesHierarchy;

//    public NodesHierarchy getNodesHierarchy() {
//        return nodesHierarchy;
//    }
//
//    public void setNodesHierarchy(NodesHierarchy nodesHierarchy) {
//        this.nodesHierarchy = nodesHierarchy;
//    }

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "testCaseStepsPk.testCaseId")
    private List<TestCaseSteps> testCaseStepsList;

    public TestCase() {
    }

    public List<TestCaseSteps> getTestCaseStepsList() {
        return testCaseStepsList;
    }

    public void setTestCaseStepsList(List<TestCaseSteps> testCaseStepsList) {
        this.testCaseStepsList = testCaseStepsList;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getTestCaseTitle() {
        return testCaseTitle;
    }

    public void setTestCaseTitle(String testCaseTitle) {
        this.testCaseTitle = testCaseTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(String preconditions) {
        this.preconditions = preconditions;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public int getExecutionType() {
        return executionType;
    }

    public void setExecutionType(int executionType) {
        this.executionType = executionType;
    }

    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    public Integer getSavePhoto() {
        return savePhoto;
    }

    public void setSavePhoto(Integer savePhoto) {
        this.savePhoto = savePhoto;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "testCaseId='" + testCaseId + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", testCaseTitle='" + testCaseTitle + '\'' +
                ", summary='" + summary + '\'' +
                ", preconditions='" + preconditions + '\'' +
                ", result='" + result + '\'' +
                ", createTime=" + createTime +
                ", modificationTime=" + modificationTime +
                ", executionType=" + executionType +
                ", codePath='" + codePath + '\'' +
//                ", nodesHierarchy=" + nodesHierarchy +
                ", testCaseStepsList=" + testCaseStepsList +
                '}';
    }

    @Override
    public int compareTo(TestCase testCase){
        return (this.testCaseId).compareTo(testCase.getTestCaseId());
    }
}
