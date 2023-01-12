package br.com.acbueno.quarkus.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import br.com.acbueno.quarkus.service.BrandService;
import br.com.acbueno.quarkus.vo.BrandRequestVO;

@Path("/brand")
public class BrandController {

    @Inject
    BrandService brandService;

    @GET
    @Path("/find/name/{brandName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBrandByName(@PathParam("brandName") String brandName) {
        return Response.ok().entity(brandService.getBrandName(brandName)).build();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveBrand(@RequestBody BrandRequestVO brandVO) {
        return Response.ok().entity(brandService.saveBrand(brandVO)).build();
    }

    @GET
    @Path("/list/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllBrand(){
       return Response.ok().entity(brandService.listAllBrands()).build();
    }

    @PUT
    @Path("/update/{id}")
    public Response update(@PathParam("id") Long id,@RequestBody BrandRequestVO brandRequestVO) {
      return Response.ok().entity(brandService.update(id, brandRequestVO)).build();
    }

    @DELETE
    @Path("/delete/{brandName}")
    public Response delete(@PathParam("brandName") String brandName) {
        try {
             brandService.delete(brandName);
            return  Response.ok().build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
