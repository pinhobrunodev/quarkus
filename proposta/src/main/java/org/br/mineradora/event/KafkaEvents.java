package org.br.mineradora.event;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.dto.ProposalDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvents {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    @Channel("proposal-channel")
    Emitter<ProposalDTO> quotationRequestEmitter;

    public void sendNewKafkaEvent(ProposalDTO quotation) {
        LOG.info("-- Enviando uma nova proposta para Tópico Kafka --");
        LOG.info(quotation.toString());
        quotationRequestEmitter.send(quotation).toCompletableFuture().join();
    }
}
