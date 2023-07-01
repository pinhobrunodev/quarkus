package org.br.mineradora.client;

import org.br.mineradora.dto.CurrencyPriceDTO;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;



@RegisterRestClient(baseUri="https://economia.awesomeapi.com.br")
@Path("/last")
@ApplicationScoped
public interface CurrencyPriceClient {
    @GET
    @Path("/{pair}")
    CurrencyPriceDTO getPriceByPair(@PathParam("pair") String par);
}
