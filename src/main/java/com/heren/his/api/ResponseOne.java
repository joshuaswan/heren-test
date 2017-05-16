package com.heren.his.api;

import com.heren.his.data.ReadHtmlBySelenium;
import com.heren.his.domain.facade.NodesHierarchyFacade;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by joshua on 2016/4/13.
 */

@Path("practice")
public class ResponseOne {

    private ReadHtmlBySelenium readHtmlBySelenium;
    private NodesHierarchyFacade nodesHierarchyFacade;

    @Inject
    public ResponseOne(ReadHtmlBySelenium readHtmlBySelenium, NodesHierarchyFacade nodesHierarchyFacade) {
        this.readHtmlBySelenium = readHtmlBySelenium;
        this.nodesHierarchyFacade = nodesHierarchyFacade;
    }

    @GET
    public Response responsePractice(){
        return Response.ok("hello").build();
    }


    @GET
    @Path("html")
    public void htmlTest() throws Exception {
        readHtmlBySelenium.test();
    }


    @GET
    @Path("table")
    public void tableTest() throws Exception {
        readHtmlBySelenium.testCase();
    }
//    @GET
//    @Path("input-code")
    public Response inputCode(){
        nodesHierarchyFacade.updateTestProjectInputCode();
        return Response.ok().build();
    }
}
