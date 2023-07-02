package org.br.mineradora.exception;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Exception e) {
        if (e instanceof BusinessException) return Response.status(Response.Status.BAD_REQUEST).entity(new MessageError(e.getMessage())).build();

        //Default Error
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro: Por favor entre em contato com o suporte").build();
    }
}
