package com.heren.his.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Blob;

/**
 * Created by joshua on 2016/6/6.
 */
@Entity
@Table(name = "TEST_RESULT_INFO")
@XmlRootElement
public class TestResultInfo {

    @Id
    @Column(name = "TEST_CASE_ID")
    private String testCaseId;

    @Column(name = "RESULT_PHOTOGRAPH")
    private Blob resultPhotograph;

    public TestResultInfo() {
    }

    public TestResultInfo(String testCaseId, Blob resultPhotograph) {
        this.testCaseId = testCaseId;
        this.resultPhotograph = resultPhotograph;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    public Blob getResultPhotograph() {
        return resultPhotograph;
    }

    public void setResultPhotograph(Blob resultPhotograph) {
        this.resultPhotograph = resultPhotograph;
    }
}
