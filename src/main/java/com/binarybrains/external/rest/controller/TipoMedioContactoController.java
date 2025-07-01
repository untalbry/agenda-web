package com.binarybrains.external.rest.controller;

import com.binarybrains.core.buisness.input.TipoMedioContactoService;
import com.binarybrains.external.rest.dto.TipoMedioContactoDto;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/tipo-medio-contacto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoMedioContactoController {
    private final TipoMedioContactoService tipoMedioContactoService;
    @Inject
    public TipoMedioContactoController(TipoMedioContactoService tipoMedioContactoService){
        this.tipoMedioContactoService = tipoMedioContactoService;
    }
    @POST
    @APIResponse(responseCode = "200", name = "Success", description = "Type of contact registered successfully")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    public Response create(@Valid TipoMedioContactoDto tipoMedioContactoDto){
        return tipoMedioContactoService.save(tipoMedioContactoDto.toEntity())
                .map(Response::ok)
                .getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @PUT
    public Response edit(){
        //TODO:
        return Response.ok().build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(){
        //TODO:
        return Response.ok().build();
    }
    @GET
    @Path("/{name}")
    @APIResponse(responseCode = "200", name = "Success", description = "Type of contact got successfully")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "Type of contact not found")
    public Response read(@PathParam("name") String name){
        return tipoMedioContactoService.readMedioContactoByName(name)
                .map(medioContactos -> Response.ok(medioContactos).build())
                .getOrElseGet(errorCode -> Response.status(400).entity(errorCode).build());
    }


}
