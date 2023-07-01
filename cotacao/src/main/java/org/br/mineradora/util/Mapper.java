package org.br.mineradora.util;

import org.br.mineradora.dto.CurrencyPriceDTO;
import org.br.mineradora.entity.QuotationEntity;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.Date;

@ApplicationScoped
public class Mapper {

    public static QuotationEntity toQuotationEntity(CurrencyPriceDTO currencyPriceDTO){
        return QuotationEntity
                .builder()
                .date(new Date())
                .pair(Constants.USD_BRL)
                .pctChange(currencyPriceDTO.getUSDBRL().getPctChange())
                .currencyPrice(new BigDecimal(currencyPriceDTO.getUSDBRL().getBid()))
                .build();
    }
}
