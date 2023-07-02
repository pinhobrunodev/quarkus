package org.br.mineradora.controller;

import org.br.mineradora.service.OpportunityService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path(value = "/api/opportunity")
public class OpportunityController {

    @Inject
    OpportunityService service;

    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateCSVReport() {
        return Response.ok(service.generateCSVOpportunityReport(), MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition",
                        "attachement; filename = " + new Date() + "--oportunidades-venda.csv").build();
    }
}
