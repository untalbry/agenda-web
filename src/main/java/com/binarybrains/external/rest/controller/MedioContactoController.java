package com.binarybrains.external.rest.controller;

import com.binarybrains.core.buisness.input.MedioContactoService;
import com.binarybrains.external.rest.dto.MedioContactoDto;
import com.binarybrains.external.rest.dto.MedioContactoUpdateDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/medio-contacto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MedioContactoController {
    private final MedioContactoService medioContactoService;
    @Inject
    public MedioContactoController(MedioContactoService medioContactoService){
        this.medioContactoService = medioContactoService;
    }
    @POST
    @APIResponse(responseCode = "200", name = "Success", description = "Contact medium updated successfully")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    public Response create(@Valid MedioContactoDto medioContactoDto){
        return  medioContactoService.save(medioContactoDto.toEntity()).map(Response::ok).getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @PATCH
    @APIResponse(responseCode = "200", name = "Success", description = "Contact medium updated successfully")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "Contact medium not found")
    public Response update(@Valid MedioContactoUpdateDto medioContactoUpdateDto){
        return  medioContactoService.update(medioContactoUpdateDto.toEntity()).map(Response::ok).getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @DELETE
    @Path("{id}")
    @APIResponse(responseCode = "200", name = "Success", description = "Contact medium deleted successfully")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "Contact medium not found")
    public Response delete(@PathParam("id") Integer idMedioContacto){
        return medioContactoService.delete(idMedioContacto).map(Response::ok).getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @GET
    @Path("{id}")
    @APIResponse(responseCode = "200", name = "Success", description = "Contact medium deleted successfully")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "Contact medium not found")
    public Response read(@PathParam("id") Integer idMedioContacto){
        return  medioContactoService.getById(idMedioContacto).map(Response::ok).getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
}
