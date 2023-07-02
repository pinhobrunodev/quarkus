package org.br.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Jacksonized
public class QuotationDTO {
    private Date date;
    private BigDecimal currencyPrice;
}
