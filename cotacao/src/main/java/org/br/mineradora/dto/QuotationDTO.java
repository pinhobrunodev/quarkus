package org.br.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.Date;

@Jacksonized
@Data
@Builder
@AllArgsConstructor
public class QuotationDTO { // Responsavel para encaminhar ao kafka para o microservico de relatorio

    private Date date;
    private BigDecimal currencyPrice;
}
