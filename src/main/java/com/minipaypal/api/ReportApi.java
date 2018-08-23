package com.minipaypal.api;

import com.minipaypal.api.beans.PaySeller;
import com.minipaypal.api.beans.User;
import com.minipaypal.api.exception.MiniPayPalAppException;
import com.minipaypal.manager.user.PaySellerTransactionManager;
import com.minipaypal.manager.user.UserManager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/report")
public class ReportApi {

    @Path("/{accountNumber}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateReport(@PathParam("accountNumber") String accountNumber) {

        User user = UserManager.getUser(accountNumber);
        if(user == null) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.NOT_FOUND.getStatusCode(), "No user found with the given Account Number");
            return Response.status(Response.Status.NOT_FOUND).entity(exception).build();
        }

        List<PaySeller> transactions = PaySellerTransactionManager.getTransactionList(accountNumber);
        return Response.status(Response.Status.OK).entity(transactions).build();
    }

    @Path("/{accountNumber}/{transactionId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(@PathParam("accountNumber") String accountNumber, @PathParam("transactionId") String transactionId) {

        User user = UserManager.getUser(accountNumber);
        if(user == null) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.NOT_FOUND.getStatusCode(), "No user found with the given Account Number");
            return Response.status(Response.Status.NOT_FOUND).entity(exception).build();
        }

        PaySeller transaction = PaySellerTransactionManager.getTransaction(Integer.parseInt(transactionId));
        if(transaction == null) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.NOT_FOUND.getStatusCode(), "No transaction found with the given Account Number and transactionId");
            return Response.status(Response.Status.NOT_FOUND).entity(exception).build();
        }
        return Response.status(Response.Status.OK).entity(transaction).build();

    }

}
