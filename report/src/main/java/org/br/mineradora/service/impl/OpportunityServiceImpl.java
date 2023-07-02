package org.br.mineradora.service.impl;

import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.event.KafkaEvents;
import org.br.mineradora.service.OpportunityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class OpportunityServiceImpl implements OpportunityService {

    private final Logger LOG = LoggerFactory.getLogger(OpportunityServiceImpl.class);


    @Override
    public void buildOpportunity(ProposalDTO proposal) {

    }

    @Override
    public void saveQuotation(QuotationDTO quotation) {

    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return null;
    }
}
