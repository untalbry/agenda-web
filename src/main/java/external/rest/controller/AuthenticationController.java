package external.rest.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import external.rest.dto.AuthenticationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@Path("/authentication")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationController {
    @POST
    @APIResponse(responseCode="200", name="Success", description="Success authentication", content= @Content(schema = @Schema(implementation = AuthenticationResponseDto.class)))
    @APIResponse(responseCode="400", name="Bad request", description="Error in the request")
    @APIResponse(responseCode="404", name="User not found", description="User to authenticate not found")
    @Operation(operationId = "login", description = "Authenticates users with email and password")
    public Response login(AuthenticationResponseDto authenticationDto){
        return Response.ok("Quarkus setup").build();
    }
    @POST
    @APIResponses({
            @APIResponse(responseCode = "200", name = "Success", description = "El registro del nuevo usuario se realizó exitosamente", content = @Content(schema = @Schema(implementation = Boolean.class))),
            @APIResponse(responseCode = "400", name = "Bad request", description = "Error en el registro del usuario, pe. el correo electrónico que ingresa ya se encuentra registrado")
    })
    @Operation(operationId = "signing", description = "Permite el registro de nuevos usuarios")
    @Path("/signin")
    public Response signin() {
        return Response.ok().build();
    }
        @GET
    @Path("/password-reset")
    public void passwordRequestReset(String login) {

    }

    @POST
    @Path("/password-reset")
    public void passwordReset() {

    }


}