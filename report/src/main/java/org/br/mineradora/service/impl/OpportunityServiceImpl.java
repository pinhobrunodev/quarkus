package org.br.mineradora.service.impl;

import org.br.mineradora.dto.OpportunityDTO;
import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.QuotationDTO;
import org.br.mineradora.entity.OpportunityEntity;
import org.br.mineradora.entity.QuotationEntity;
import org.br.mineradora.event.KafkaEvents;
import org.br.mineradora.repository.OpportunityRepository;
import org.br.mineradora.repository.QuotationRepository;
import org.br.mineradora.service.OpportunityService;
import org.br.mineradora.util.CSVHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OpportunityServiceImpl implements OpportunityService {

    private final Logger LOG = LoggerFactory.getLogger(OpportunityServiceImpl.class);

    @Inject
    OpportunityRepository opportunityRepository;
    @Inject
    QuotationRepository quotationRepository;

    @Override
    @Transactional
    public void buildOpportunity(ProposalDTO proposal) {
        List<QuotationEntity> quotationEntityList =
                quotationRepository.findAll().list();
        Collections.reverse(quotationEntityList); // Inverte a ordem dos registros de cotação, trazendo o valor mais atual como índice 0

        OpportunityEntity opportunity = new OpportunityEntity();
        opportunity.setDate(new Date());
        opportunity.setCustomer(proposal.getCustomer());
        opportunity.setProposalId(proposal.getProposalId());
        opportunity.setPriceTonne(proposal.getPriceTonne());
        opportunity.setLastDollarQuotation(quotationEntityList.get(0).getCurrencyPrice());

        LOG.info("-- Salvando uma nova oportunidade de compra com valor total --");

        opportunityRepository.persist(opportunity);

    }

    @Override
    @Transactional
    public void saveQuotation(QuotationDTO quotation) {
        LOG.info("-- Salvando uma nova cotação com valor: {} --", quotation.getCurrencyPrice());
        quotationRepository.persist(new QuotationEntity(null, quotation.getDate(), quotation.getCurrencyPrice()));
    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        return opportunityRepository.findAll().stream().map(OpportunityDTO::new).collect(Collectors.toList());
    }

    @Override
    public ByteArrayInputStream generateCSVOpportunityReport() {
        return CSVHelper.OpportunitiesToCSV(
                opportunityRepository.findAll().stream().map(OpportunityDTO::new).collect(Collectors.toList())
        );
    }
}
