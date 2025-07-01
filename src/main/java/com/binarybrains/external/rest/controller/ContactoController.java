package com.binarybrains.external.rest.controller;

import com.binarybrains.core.buisness.input.ContactoService;
import com.binarybrains.external.rest.dto.ContactoDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("/contacto")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactoController {
    private final ContactoService contactoService;
    public ContactoController(ContactoService contactoService){
        this.contactoService = contactoService;
    }
    @POST
    @APIResponse(responseCode = "200", name = "Success", description = "Contact registered successfully")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "User not found")
    public Response register(@Valid ContactoDto contactoDto){
        return contactoService.save(contactoDto.toEntity())
                .map(Response::ok)
                .getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @GET
    @APIResponse(responseCode = "200", name = "Success", description = "Request successful")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "User not found")
    public Response read(){
        return  Response.ok().build();
    }

}
