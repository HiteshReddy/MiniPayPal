package com.minipaypal.api;

import com.minipaypal.api.beans.User;
import com.minipaypal.api.exception.MiniPayPalAppException;
import com.minipaypal.manager.user.UserManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/balance")
public class BalanceApi {

    @Path("/{accountNumber}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance(@PathParam("accountNumber") String accountNumber) {
        User user = UserManager.getUser(accountNumber);
        if(user == null) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.NOT_FOUND.getStatusCode(), "No user found with the given Account Number");
            return Response.status(Response.Status.NOT_FOUND).entity(exception).build();
        }
        return Response.status(Response.Status.OK).entity(user.getAmount()).build();
    }

}
