package com.heren.his.domain.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by joshua on 2016/5/15.
 */
@Embeddable
public class TestCaseStepsPk implements Serializable {

    @Column(name="TEST_CASE_ID")
    @Basic(optional = false)
    private String testCaseId;

    @Column(name="STEP_NUMBER")
    @Basic(optional = false)
    private Integer stepNumber;

    public TestCaseStepsPk() {
    }

    public TestCaseStepsPk(String testCaseId, Integer stepNumber) {
        this.testCaseId = testCaseId;
        this.stepNumber = stepNumber;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "TestCaseStepsPk{" +
                "testCaseId='" + testCaseId + '\'' +
                ", stepNumber=" + stepNumber +
                '}';
    }
}
