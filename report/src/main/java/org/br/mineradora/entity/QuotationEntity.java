package org.br.mineradora.entity;

import io.quarkus.arc.All;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "quotation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuotationEntity {

    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    @Column(name = "currency_price")
    private BigDecimal currencyPrice;
}
