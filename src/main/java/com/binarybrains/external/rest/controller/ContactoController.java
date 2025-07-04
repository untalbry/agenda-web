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
    public Response create(@Valid ContactoDto contactoDto){
        return contactoService.save(contactoDto.toEntity())
                .map(Response::ok)
                .getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @GET
    @Path("/nickname/{nickname}")
    @APIResponse(responseCode = "200", name = "Success", description = "Request successful")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "User not found")
    public Response read(@PathParam("nickname") String nickname){
        return contactoService.getByNickname(nickname).map(Response::ok).getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @PATCH
    @APIResponse(responseCode = "200", name = "Success", description = "Contact updated successfully")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "Contact not found")
    public Response update(@Valid ContactoDto contactoDto){
        return contactoService.update(contactoDto.toEntity()).map(Response::ok).getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @DELETE
    @Path("{id}")
    @APIResponse(responseCode = "200", name = "Success", description = "Contact deleted successfully")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "Contact not found")
    public Response delete(@PathParam("id") Integer idContact){
        return contactoService.deleteContactById(idContact).map(Response::ok).getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
    @GET
    @Path("/{idUser}")
    @APIResponse(responseCode = "200", name = "Success", description = "Request successful")
    @APIResponse(responseCode = "400", name = "Bad request", description = "Error in the request")
    @APIResponse(responseCode = "404", name = "Not found", description = "User not found")
    public Response readByUserId(@PathParam("idUser") Integer idUser){
        return contactoService.getAllByIdUser(idUser).map(Response::ok).getOrElseGet(errorCode -> Response.status(400).entity(errorCode)).build();
    }
}
