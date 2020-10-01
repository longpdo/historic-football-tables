package com.adorsys.historicfootballtables.service;

import com.adorsys.historicfootballtables.dao.MatchRepository;
import com.adorsys.historicfootballtables.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getAllMatchesByRound(UUID roundId) {
        List<Match> matches = matchRepository.findByRoundId(roundId);
        Collections.sort(matches, Comparator.comparing(Match::getDate));

        return matches;
    }

    public Match getMatch(UUID matchId) {
        return matchRepository.findById(matchId).orElse(null);
    }

    public void updateMatch(Match match) {
        matchRepository.save(match);
    }
}