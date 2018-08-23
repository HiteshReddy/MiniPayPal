package com.minipaypal.api;

import com.minipaypal.api.beans.PaySeller;
import com.minipaypal.api.beans.User;
import com.minipaypal.api.exception.MiniPayPalAppException;
import com.minipaypal.manager.user.PaySellerTransactionManager;
import com.minipaypal.manager.user.UserManager;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class PaySellerApi {

    private double sellerTransactionFee = 0.25;

    @Path("/paySeller")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response paySeller(PaySeller paySeller) {

        //validate if buyer and seller are available
        User buyer = UserManager.getUser(paySeller.getBuyerAccountNumber());
        if(buyer == null) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.NOT_FOUND.getStatusCode(), "No Buyer found with the given Account Number");
            return Response.status(Response.Status.NOT_FOUND).entity(exception).build();
        }
        User seller = UserManager.getUser(paySeller.getSellerAccountNumber());
        if(seller == null) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.NOT_FOUND.getStatusCode(), "No Seller found with the given Account Number");
            return Response.status(Response.Status.NOT_FOUND).entity(exception).build();
        }

        //validate if buyer has the required amount
        if(buyer.getAmount() < paySeller.getAmount()) {
            MiniPayPalAppException exception = new MiniPayPalAppException( Response.Status.BAD_REQUEST.getStatusCode(), "The Available amount in your account is: " + buyer.getAmount() + ". Add money to pay seller.");
            return Response.status(Response.Status.BAD_REQUEST).entity(exception).build();
        }

        long convertedAmount = CurrencyConverterUtils.convert(paySeller.getCurrency(), seller.getCurrency(), paySeller.getAmount());

        buyer.setAmount(buyer.getAmount() - paySeller.getAmount());

        long amount = (long)(convertedAmount - convertedAmount*sellerTransactionFee);
        seller.setAmount(seller.getAmount() + amount);

        int transactionId = PaySellerTransactionManager.addPaySellerTransactionDetails(paySeller);
        String response = ApiUtil.constructResponseString("PaySeller", transactionId);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

}
