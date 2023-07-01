package org.br.mineradora.util;

import jakarta.enterprise.context.ApplicationScoped;
import org.br.mineradora.dto.ProposalDetailsDTO;
import org.br.mineradora.entity.ProposalEntity;

import java.util.Date;

@ApplicationScoped
public class Mapper {

    public static ProposalEntity toProposalEntity(ProposalDetailsDTO proposalDetailsDTO){
        return ProposalEntity
                .builder()
                .country(proposalDetailsDTO.getCountry())
                .created(new Date())
                .customer(proposalDetailsDTO.getCustomer())
                .priceTonne(proposalDetailsDTO.getPriceTonne())
                .tonnes(proposalDetailsDTO.getTonnes())
                .proposalValidityDays(proposalDetailsDTO.getProposalValidityDays())
                .build();
    }

    public static ProposalDetailsDTO toProposalDetailsDTO(ProposalEntity entity) {
        return ProposalDetailsDTO
                .builder()
                .proposalId(entity.getId())
                .country(entity.getCountry())
                .customer(entity.getCustomer())
                .priceTonne(entity.getPriceTonne())
                .tonnes(entity.getTonnes())
                .proposalValidityDays(entity.getProposalValidityDays())
                .build();
    }
}
