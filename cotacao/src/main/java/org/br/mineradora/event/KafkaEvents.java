package org.br.mineradora.event;

import org.br.mineradora.dto.QuotationDTO;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KafkaEvents {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvents.class);

    @Channel("quotation-channel") // canal que tem acesso a um topico do kafka
    Emitter<QuotationDTO> quotationRequestEmitter; // emissor que vai enviar mensagens para o topico do kafka , informando tambem o tipo de msg

   public void sendNewKafkaEvent(QuotationDTO quotation) {
        LOG.info("-- Enviando Cotação para Tópico Kafka --");
        LOG.info(quotation.toString());
        quotationRequestEmitter.send(quotation).toCompletableFuture().join();
    }

}
