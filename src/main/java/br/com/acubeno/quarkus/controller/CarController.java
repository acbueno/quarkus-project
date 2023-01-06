package br.com.acubeno.quarkus.controller;

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

import br.com.acubeno.quarkus.service.CarSevice;
import br.com.acubeno.quarkus.vo.CarRequestVO;

@Path("/car")
public class CarController {

    @Inject
    CarSevice carSevice;

    @GET
    @Path("/find/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarByName(@PathParam("name") String name) {
        return Response.ok().entity(carSevice.getCarByName(name)).build();
    }

    @GET
    @Path("/find/model/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarByModelName(@PathParam("model") String model) {
        return Response.ok().entity(carSevice.getCarByModel(model)).build();
    }

    @GET
    @Path("/find/color/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListCarByColor(@PathParam("color") String color) {
        return Response.ok().entity(carSevice.listCarByColor(color)).build();
    }

    @GET
    @Path("/find/year/factory/{yearFactory}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListCarYearFactory(@PathParam("yearFactory") int year) {
        return Response.ok().entity(carSevice.listCarByYearFactory(year)).build();
    }

    @GET
    @Path("/find/year/model/{yearModel}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListCarYearModel(@PathParam("yearModel") int yearModel) {
        return Response.ok().entity(carSevice.listCarByYearModel(yearModel)).build();
    }

    @GET
    @Path("/find/chassis/{chassis}")
    public Response getCarByChassis(@PathParam("chassis") String chassis) {
        return Response.ok().entity(carSevice.findCarByChassis(chassis)).build();
    }

    @GET
    @Path("find/brand/{brandName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListCarByBrand(@PathParam("brandName") String brandName) {
        return Response.ok().entity(carSevice.listCarByBrand(brandName)).build();
    }

    @POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@RequestBody CarRequestVO carRequestVO) {
        return Response.ok().entity(carSevice.save(carRequestVO)).build();
    }

    @PUT
    @Path("/update/model/{modelName}")
    public Response update(@PathParam("modelName") String modelName, @RequestBody CarRequestVO carRequestVO) {
        return Response.ok().entity(carSevice.update(modelName, carRequestVO)).build();
    }

    @DELETE
    @Path("/delete/model/{modelName}")
    public Response deleteByModel(@PathParam("modelName") String modelName) {
        try {
            carSevice.deleteByModel(modelName);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/delete/name/{name}")
    public Response deleteByName(@PathParam("name") String name) {
        try {
            carSevice.deleteByName(name);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/find/list/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListAllCars() {
        return Response.ok().entity(carSevice.listAllCars()).build();
    }

}
