package org.br.mineradora.service.impl;


import org.br.mineradora.dto.ProposalDTO;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.event.KafkaEvents;
import org.br.mineradora.repository.ProposalRepository;
import org.br.mineradora.service.ProposalService;
import org.br.mineradora.util.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProposalServiceImpl implements ProposalService {


    private final Logger LOG = LoggerFactory.getLogger(ProposalServiceImpl.class);


    @Inject
    ProposalRepository repository;
    @Inject
    KafkaEvents kafka;


    @Override
    public ProposalDetailsDTO findFullProposal(long id) {
        LOG.info("-- Buscando a proposta com id {} --", id);
        var entity = repository.findByIdOptional(id).orElseThrow(() -> new RuntimeException("not found"));
        return Mapper.toProposalDetailsDTO(entity);
    }

    @Override
    @Transactional
    public void createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        LOG.info("-- Criando uma nova proposta --");
        kafka.sendNewKafkaEvent(buildAndSaveProposal(proposalDetailsDTO));
    }

    @Override
    @Transactional
    public void removeProposal(long id) {
        LOG.info("-- Removendo a proposta com id {} --", id);
        repository.deleteById(id);
    }

    @Transactional
    private ProposalDTO buildAndSaveProposal(ProposalDetailsDTO proposalDetailsDTO) {
        try {
            var entity = Mapper.toProposalEntity(proposalDetailsDTO);
            repository.persist(entity);
            LOG.info("-- Proposta salva com sucesso --");
            return ProposalDTO
                    .builder()
                    .customer(entity.getCustomer())
                    .priceTonne(entity.getPriceTonne())
                    .proposalId(repository.findByCustomer(entity.getCustomer()).get().getId())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("deu ruim");
        }
    }

}
