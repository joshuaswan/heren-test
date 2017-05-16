package com.heren.his.api;

import com.google.inject.persist.Transactional;
import com.heren.his.domain.entity.TestCaseSteps;
import com.heren.his.domain.facade.TestCaseStepsFacade;

import javax.inject.Inject;
import javax.validation.constraints.AssertFalse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by joshua on 2016/5/6.
 */

@Path("test-case-steps")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestCaseStepsResource {

    private TestCaseStepsFacade testCaseStepsFacade;

    @Inject
    public TestCaseStepsResource(TestCaseStepsFacade testCaseStepsFacade) {
        this.testCaseStepsFacade = testCaseStepsFacade;
    }

    @Transactional
    public List<TestCaseSteps> getTestStep(long parentId){
        StringBuilder sql = new StringBuilder("select * from nodes_hierarchy n where ");
        sql.append("t.");

        return null;
    }
}
