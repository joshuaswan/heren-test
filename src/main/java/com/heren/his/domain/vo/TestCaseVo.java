package com.heren.his.domain.vo;

import com.heren.his.domain.entity.TestCase;
import com.heren.his.domain.entity.TestCaseSteps;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by joshua on 2016/5/19.
 */
public class TestCaseVo {

    private TestCase testCase;

    private List<TestCaseSteps> testCaseStepsList;

    public TestCaseVo() {
    }

    public TestCaseVo(TestCase testCase, List<TestCaseSteps> testCaseStepsList) {
        this.testCase = testCase;
        this.testCaseStepsList = testCaseStepsList;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public List<TestCaseSteps> getTestCaseStepsList() {
        return testCaseStepsList;
    }

    public void setTestCaseStepsList(List<TestCaseSteps> testCaseStepsList) {
        this.testCaseStepsList = testCaseStepsList;
    }
}
