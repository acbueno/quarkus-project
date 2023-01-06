package br.com.acubeno.quarkus.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.acubeno.quarkus.service.EngineService;
import br.com.acubeno.quarkus.vo.EngineRequestVO;

@Path("/engine")
public class EngineController {


    @Inject
    EngineService engineService;

    @GET
    @Path("/list/{codeNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEgineByCodeNumber(@PathParam("codeNumber") String codeNumber) {
        return Response.ok().entity(engineService.getEngineByCodeNumber(codeNumber)).build();
    }


    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@RequestBody EngineRequestVO engineVO) {
        return Response.ok().entity(engineService.save(engineVO)).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, @RequestBody EngineRequestVO engineVO) {
        return Response.ok().entity(engineService.update(id, engineVO)).build();
    }

    @GET
    @Path("/list/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllEngine(){
        return Response.ok().entity(engineService.listAllEngine()).build();
    }

}
