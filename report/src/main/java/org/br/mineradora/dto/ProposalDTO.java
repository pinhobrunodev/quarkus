package org.br.mineradora.dto;

import io.quarkus.arc.All;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Jacksonized
public class ProposalDTO {
    private Long proposalId;
    private String customer;
    private BigDecimal priceTonne;
}
