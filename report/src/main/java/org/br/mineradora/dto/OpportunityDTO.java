package org.br.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Jacksonized
public class OpportunityDTO {

    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;
    private BigDecimal lastDollarQuotation;

}
