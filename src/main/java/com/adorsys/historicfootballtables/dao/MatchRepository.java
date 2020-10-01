package com.adorsys.historicfootballtables.dao;

import com.adorsys.historicfootballtables.model.Match;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends CrudRepository<Match, UUID> {

    public List<Match> findByRoundId(UUID roundId);
}
