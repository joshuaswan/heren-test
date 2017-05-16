package com.heren.his.domain.entity;

import javax.persistence.*;
import java.io.ObjectInput;
import java.io.Serializable;

/**
 * Created by joshua on 2016/5/15.
 */
@Embeddable
public class TestCasePk implements Serializable {

    @Basic(optional = false)
    @Column(name="NODE_ID")
    private String nodeId;

    @Basic(optional = false)
    @Column(name = "TEST_CASE_ID")
    private String testCaseId;

    public TestCasePk() {
    }

    public TestCasePk(String nodeId, String testCaseId) {
        this.nodeId = nodeId;
        this.testCaseId = testCaseId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof TestCasePk)){
            return false;
        }
        TestCasePk other = (TestCasePk) obj;
        if ((this.testCaseId == null && other.testCaseId != null) || (this.testCaseId != null && !this.testCaseId.equals(other.testCaseId))){
            return false;
        }
        if ((this.nodeId == null && other.nodeId != null) || (this.nodeId != null && !this.nodeId.equals(other.testCaseId))){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash =0;
        hash += (nodeId != null ? nodeId.hashCode() : 0);
        hash += (testCaseId != null ? testCaseId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "TestCasePk{" +
                "nodeId='" + nodeId + '\'' +
                ", testCaseId='" + testCaseId + '\'' +
                '}';
    }
}
