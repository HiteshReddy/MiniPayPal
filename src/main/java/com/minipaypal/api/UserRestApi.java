package com.minipaypal.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minipaypal.api.beans.TransactionResponse;
import com.minipaypal.api.beans.User;
import com.minipaypal.api.exception.MiniPayPalAppException;
import com.minipaypal.manager.user.UserManager;

@Path("/user")
public class UserRestApi {

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) {
		
		//check if the user exists with the same email
		if (UserManager.isDuplicateUser(user)) {
			MiniPayPalAppException exception = new MiniPayPalAppException( Status.CONFLICT.getStatusCode(), "A User with the same email id exists in the system");
			return Response.status(Status.CONFLICT).entity(exception).build();
		}
		
		//create a new user
		int acct_number = UserManager.createUser(user);
		String response = ApiUtil.constructResponseString("AccountNumber", acct_number);
        return Response.status(Status.CREATED).entity(response).build();
	}
	
	@GET
	@Path("/{accountNumber}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("accountNumber") String accountNumber) {
		User user = UserManager.getUser(accountNumber);

		//if the user with the account number exits, return user details
		if(user == null) {
			MiniPayPalAppException exception = new MiniPayPalAppException( Status.NOT_FOUND.getStatusCode(), "No User found with the given ID");
			return Response.status(Status.NOT_FOUND).entity(exception).build();
		}
		return Response.status(Status.OK).entity(user).build();
	}

}