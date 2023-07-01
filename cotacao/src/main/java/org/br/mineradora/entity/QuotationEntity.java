package org.br.mineradora.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "quotation")
public class QuotationEntity {

    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    @Column(name = "currency_price")
    private BigDecimal currencyPrice;
    @Column(name = "pct_change")
    private String pctChange;
    private String pair;
}
