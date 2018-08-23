package com.minipaypal.api;

import com.minipaypal.api.beans.Transaction;
import com.minipaypal.api.beans.TransactionType;
import com.minipaypal.api.beans.User;
import com.minipaypal.api.exception.MiniPayPalAppException;
import com.minipaypal.manager.user.TransactionManager;
import com.minipaypal.manager.user.UserManager;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/money")
public class CreditDebitApi {

    @Path("/credit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response creditAmount(Transaction transaction) {
        User user = UserManager.getUser(transaction.getAccountNumber());
        if(user == null) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.NOT_FOUND.getStatusCode(), "No User found with the given Account Number");
            return Response.status(Response.Status.NOT_FOUND).entity(exception).build();
        }
        long amount = CurrencyConverterUtils.convert(transaction.getCurrency(), user.getCurrency(), transaction.getAmount());
        user.setAmount(user.getAmount() + amount);
        transaction.setType(TransactionType.CREDIT);
        return Response.status(Response.Status.OK).entity(createTransactionAndReturnId(transaction)).build();
    }

    @Path("/debit")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response debitAmount(Transaction transaction) {
        //validate if a user exists with the account number
        User user = UserManager.getUser(transaction.getAccountNumber());
        if(user == null) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.NOT_FOUND.getStatusCode(), "No User found with the given Account Number");
            return Response.status(Response.Status.NOT_FOUND).entity(exception).build();
        }

        //validate if the user has the required amount to debit
        if(user.getAmount() < transaction.getAmount()) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.BAD_REQUEST.getStatusCode(), "The Amount in your account is less than the withdrawal amount");
            return Response.status(Response.Status.BAD_REQUEST).entity(exception).build();
        }
        user.setAmount(user.getAmount() - transaction.getAmount());
        transaction.setType(TransactionType.DEBIT);
        return Response.status(Response.Status.OK).entity(createTransactionAndReturnId(transaction)).build();
    }

    private String createTransactionAndReturnId(Transaction transaction) {
        int transactionId = TransactionManager.addTransactionDetails(transaction);
        return ApiUtil.constructResponseString("TransactionId", transactionId);
    }
}
