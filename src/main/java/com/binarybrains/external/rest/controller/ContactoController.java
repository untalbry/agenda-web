package com.binarybrains.external.rest.controller;

import com.binarybrains.core.buisness.input.ContactoService;
import com.binarybrains.external.rest.dto.ContactoDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contacto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactoController {
    private final ContactoService contactoService;
    public ContactoController(ContactoService contactoService){
        this.contactoService = contactoService;
    }
    @POST
    public Response register(@Valid ContactoDto contactoDto){
        return contactoService.save(contactoDto.toEntity())
                .map(Response::ok)
                .getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
}
