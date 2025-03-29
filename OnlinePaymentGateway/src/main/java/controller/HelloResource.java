package controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import util.JWTUtil;


@Path("/hello")
public class HelloResource {
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Missing or invalid token!").build();
        }

        String token = authHeader.substring(7);
        String username = JWTUtil.validateToken(token);//username is email

        if (username == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid or expired token!").build();
        }

        return Response.ok("Welcome " + username + " to Online Payment Gateway!").build();
    }
}
