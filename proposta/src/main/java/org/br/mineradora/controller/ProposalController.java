package org.br.mineradora.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.service.ProposalService;

import java.net.URI;


@Path("/api/proposal")
public class ProposalController {

    @Inject
    ProposalService service;

    @GET
    @Path("/{id}")
    public Response findDetailsProposal(@PathParam("id") long id) {
        return Response.ok(service.findFullProposal(id)).build();
    }

    @POST
    public Response createProposal(ProposalDetailsDTO proposalDetailsDTO) {
        service.createNewProposal(proposalDetailsDTO);
        return Response.created(URI.create("/api/proposal")).build();
    }


    @DELETE
    @Path("/{id}")
    public Response removeProposal(@PathParam("id") long id) {
        service.removeProposal(id);
        return Response.noContent().build();
    }

}
