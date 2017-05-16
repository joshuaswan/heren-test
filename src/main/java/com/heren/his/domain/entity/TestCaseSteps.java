package com.heren.his.domain.entity;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by joshua on 2016/4/26.
 */

@Entity
@Table(name = "TEST_CASE_STEPS")
@XmlRootElement
public class TestCaseSteps {

    @EmbeddedId
    private TestCaseStepsPk testCaseStepsPk;

    @Column(name = "ACTIONS")
    private String actions;

    @Column(name = "EXPECTED_RESULTS")
    private String expectedResults;

    @Column(name = "ACTIVE")
    private Integer active;

    @Column(name = "EXECUTION_TYPE")
    private Integer executionType;

    @Column(name = "CODE_PATH")
    private String codePath;

//    @JoinColumn(name = "STEP_NUMBER", referencedColumnName = "TEST_CASE_ID",updatable = false,insertable = false)
//    @ManyToOne
//    private TestCase testCase;
//
//    public TestCase getTestCase() {
//        return testCase;
//    }
//
//    public void setTestCase(TestCase testCase) {
//        this.testCase = testCase;
//    }

    public TestCaseSteps() {
    }

    public TestCaseStepsPk getTestCaseStepsPk() {
        return testCaseStepsPk;
    }

    public void setTestCaseStepsPk(TestCaseStepsPk testCaseStepsPk) {
        this.testCaseStepsPk = testCaseStepsPk;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getExpectedResults() {
        return expectedResults;
    }

    public void setExpectedResults(String expectedResults) {
        this.expectedResults = expectedResults;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getExecutionType() {
        return executionType;
    }

    public void setExecutionType(Integer executionType) {
        this.executionType = executionType;
    }

    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    @Override
    public String toString() {
        return "TestCaseSteps{" +
                "testCaseStepsPk=" + testCaseStepsPk +
                ", actions='" + actions + '\'' +
                ", expectedResults='" + expectedResults + '\'' +
                ", active=" + active +
                ", executionType=" + executionType +
                ", codePath='" + codePath + '\'' +
//                ", testCase=" + testCase +
                '}';
    }
}
