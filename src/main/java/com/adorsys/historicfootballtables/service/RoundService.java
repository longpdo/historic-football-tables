package com.adorsys.historicfootballtables.service;

import com.adorsys.historicfootballtables.dao.RoundRepository;
import com.adorsys.historicfootballtables.model.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoundService {

    @Autowired
    private RoundRepository roundRepository;

    public List<Round> getAllRoundsBySeason(UUID seasonId) {
//        List<Round> rounds = new ArrayList<>();
//        roundRepository.findBySeasonId(seasonId).forEach(rounds::add);
        List<Round> rounds = roundRepository.findBySeasonId(seasonId);
        Collections.sort(rounds, Comparator.comparing(Round::getNumber));

        return rounds;
    }

    public Round getRound(UUID roundId) {
        return roundRepository.findById(roundId).orElse(null);
    }

    public void updateRound(Round round) {
        roundRepository.save(round);
    }
}
