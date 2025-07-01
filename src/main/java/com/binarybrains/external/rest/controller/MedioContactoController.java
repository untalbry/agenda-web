package com.binarybrains.external.rest.controller;

import com.binarybrains.core.buisness.input.MedioContactoService;
import com.binarybrains.external.rest.dto.MedioContactoDto;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
    public Response register(@Valid MedioContactoDto medioContactoDto){
        return medioContactoService.save(medioContactoDto.toEntity())
                .map(Response::ok)
                .getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @PUT
    public Response edit(){
        //TODO:
        return Response.ok().build();
    }
    @DELETE
    public Response delete(){
        //TODO:
        return Response.ok().build();
    }
    @GET
    public Response read(){
        //TODO:
        return Response.ok().build();
    }


}
