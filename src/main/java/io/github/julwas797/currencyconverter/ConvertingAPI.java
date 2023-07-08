package io.github.julwas797.currencyconverter;

import io.github.julwas797.currencyconverter.service.ConvertingService;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/convert")
@Dependent
public class ConvertingAPI {
    @Inject
    private ConvertingService converter;

    @POST()
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String convert(@FormParam("toConvert") double amount, @FormParam("code") String currencyCode) {
        return String.format("%.2f", converter.getConverted(amount, currencyCode));
    }
}
