package com.adorsys.historicfootballtables.match;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MatchRepository extends CrudRepository<Match, UUID> {

    public List<Match> findByRoundId(UUID roundId);
}
