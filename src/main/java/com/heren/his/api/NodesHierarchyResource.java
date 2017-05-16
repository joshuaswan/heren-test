package com.heren.his.api;

import com.heren.his.commons.exceptions.OnException;
import com.heren.his.domain.entity.NodesHierarchy;
import com.heren.his.domain.facade.NodesHierarchyFacade;
import com.sun.org.apache.regexp.internal.RE;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by joshua on 2016/5/6.
 */

@Path("nodes-hierarchy")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NodesHierarchyResource {
    private NodesHierarchyFacade nodesHierarchyFacade;

    @Inject
    public NodesHierarchyResource(NodesHierarchyFacade nodesHierarchyFacade) {
        this.nodesHierarchyFacade = nodesHierarchyFacade;
    }

    @POST
    @OnException("新增测试分组失败")
    public Response addNodesHierarchy(NodesHierarchy nodesHierarchy){
        nodesHierarchyFacade.addNodeHierarchy(nodesHierarchy);
        return Response.ok().build();
    }

    @POST
    @Path("test-case")
    @OnException("保存测试用例失败！")
    public Response updateTestCase(NodesHierarchy nodesHierarchy){
        nodesHierarchyFacade.updateTestCase(nodesHierarchy);
        return Response.ok().build();
    }

    @POST
    @Path("{id}")
    @OnException("更新测试分组失败")
    public Response updateNodesHierarchy(@PathParam("id") String id,NodesHierarchy nodesHierarchy){
        nodesHierarchyFacade.updateNodesHierarchy(nodesHierarchy);
        return Response.ok().build();
    }

    @GET
    @Path("test-project/{inputCode}")
    public List<NodesHierarchy> getTestProject(@PathParam("inputCode")String inputCode){
        return nodesHierarchyFacade.getTestProjectByInputCode(inputCode.substring(10));
    }

    @GET
    @Path("all-test-project")
    public List<NodesHierarchy> queryAllTestProject(){
        return nodesHierarchyFacade.queryAllTestProject();
    }

    /**
     * 默认选中查询
     */
    @GET
    @Path("/all-node-hierarchy")
    @OnException("获取默认类型失败！")
    public List<NodesHierarchy> getAllNode() {
        return nodesHierarchyFacade.getAllNode();
    }

    @GET
    @Path("node-hierarchy-delete/{id}")
    @OnException("删除测试分组失败")
    public Response deleteNodeHierarchyById(@PathParam("id") String id){
        nodesHierarchyFacade.deleteNodesHierarchies(id);
        return Response.noContent().build();
    }

    @GET
    @Path("node-hierarchy-id/{id}")
    @OnException("查询测试分组失败")
    public NodesHierarchy searchNodesHierarchiesById(@PathParam("id")String id){
        return nodesHierarchyFacade.searchNodesHierarchiesById(id);
    }

    @GET
    @Path("package-name")
    @OnException("对应包名查找失败！")
    public List<String> getPackageList(){
        return nodesHierarchyFacade.getPackageList();
    }

    @GET
    @Path("class-name/{package-name}")
    @OnException("对应类名查找失败！")
    public List<String> getClassList(@PathParam("package-name")String pathName){
        return nodesHierarchyFacade.getClassList(pathName);
    }

    @GET
    @Path("method-name/{class-name}")
    @OnException("对应方法查找失败！")
    public List<String> getMethodList(@PathParam("class-name")String className){
        return nodesHierarchyFacade.getMethodList(className);
    }
}
