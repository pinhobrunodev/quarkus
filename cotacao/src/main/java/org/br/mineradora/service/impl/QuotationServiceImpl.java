package org.br.mineradora.service.impl;

import org.br.mineradora.client.CurrencyPriceClient;
import org.br.mineradora.dto.CurrencyPriceDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.entity.QuotationEntity;
import org.br.mineradora.event.KafkaEvents;
import org.br.mineradora.repository.QuotationRepository;
import org.br.mineradora.service.QuotationService;
import org.br.mineradora.util.Constants;
import org.br.mineradora.util.Mapper;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class QuotationServiceImpl implements QuotationService {

    private final Logger LOG = LoggerFactory.getLogger(QuotationServiceImpl.class);

    @Inject
    @RestClient
     CurrencyPriceClient client;
    @Inject
     QuotationRepository repository;
    @Inject
    KafkaEvents kafka;

    @Override
    public void getCurrencyPrice() {
        var currencyPriceInfo = client.getPriceByPair(Constants.USD_BRL);
        LOG.info("-- Resposta da API Externa --");
        LOG.info(currencyPriceInfo.getUSDBRL().toString());
        if (updateCurrentInfoPrice(currencyPriceInfo)) {
            kafka.sendNewKafkaEvent(QuotationDTO
                    .builder()
                    .currencyPrice(new BigDecimal(currencyPriceInfo.getUSDBRL().getBid()))
                    .date(new Date())
                    .build());
        }
    }

    private boolean updateCurrentInfoPrice(CurrencyPriceDTO actualCurrency) {

        BigDecimal currentDollarPrice = new BigDecimal(actualCurrency.getUSDBRL().getBid());
        boolean updatedPrice = false;
        List<QuotationEntity> quotationEntityList = repository.findAll().list();

        if (quotationEntityList.isEmpty()) {
            LOG.info("-- [ATUALIZANDO] Salvando primeiro registro de Cotação --");
            saveQuotation(actualCurrency);
            updatedPrice = true;
        } else {
            BigDecimal lastSavedDollarPrice = quotationEntityList.get(quotationEntityList.size() - 1).getCurrencyPrice();
            if (currentDollarPrice.floatValue() > lastSavedDollarPrice.floatValue()) {
                LOG.info("-- [ATUALIZANDO] Consulta atual da cotação maior que a anterior --");
                updatedPrice = true;
                saveQuotation(actualCurrency);
            }
        }
        LOG.info("-- Sem atualização --");
        return updatedPrice;
    }

    private void saveQuotation(CurrencyPriceDTO actualCurrency) {
        repository.persist(Mapper.toQuotationEntity(actualCurrency));
    }
}
