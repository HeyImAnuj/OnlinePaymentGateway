package controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.user;
import service.UserService;
import util.JWTUtil;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	private UserService userService = new UserService();

	// Register a new user
	@POST
	@Path("/register")
	public Response registerUser(user userData) {
		System.out.println("Regiter resource called --");
		boolean isRegistered = userService.registerUser(userData.getName(), userData.getEmail(),
				userData.getPassword(), userData.getRole());
		if (isRegistered) {
			return Response.status(Response.Status.CREATED).entity("{\"message\": \"User registered successfully\"}")
					.build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \"User already exists\"}").build();
		}
	}

	// Login user
	@POST
	@Path("/login")
	public Response loginUser(user userData) {
		String username = userData.getEmail();
		boolean loggedInUser = userService.loginUser(username, userData.getPassword());
		if (loggedInUser) {
			String token = JWTUtil.generateToken(username);
			return Response.ok("{\"token\": \"" + token + "\"}").build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\": \"Invalid email or password\"}")
					.build();
		}
	}

	@GET
	@Path("/adminOnly")
	public Response adminOnlyEndpoint(@HeaderParam("Authorization") String authHeader) {
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Missing or invalid token!").build();
        }

        String token = authHeader.substring(7);
        
		
		String email = JWTUtil.validateToken(token);

		if (email == null) {
			return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\": \"Invalid token\"}").build();
		}
		
		
		user loggedInUser = userService.getUserByEmail(email);

		if (loggedInUser != null && "ADMIN".equals(loggedInUser.getRole())) {
			return Response.ok("{\"message\": \"Welcome, Admin!\"}").build();
		} else {
			return Response.status(Response.Status.FORBIDDEN).entity("{\"error\": \"Access denied\"}").build();
		}
	}

}
