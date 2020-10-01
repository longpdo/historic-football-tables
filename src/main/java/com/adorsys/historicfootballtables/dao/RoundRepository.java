package com.adorsys.historicfootballtables.dao;

import com.adorsys.historicfootballtables.model.Round;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface RoundRepository extends CrudRepository<Round, UUID> {

    public List<Round> findBySeasonId(UUID seasonId);

}
