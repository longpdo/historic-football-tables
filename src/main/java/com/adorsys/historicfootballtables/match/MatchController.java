package com.adorsys.historicfootballtables.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/matches")
    public List<Match> getAllMatches(@RequestParam UUID roundId) {
        return matchService.getAllMatchesByRound(roundId);
    }

    @RequestMapping("/match")
    public Match getMatch(@RequestParam UUID matchId) {
        return matchService.getMatch(matchId);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/match")
    public void updateMatch(@RequestBody Match match) {
        matchService.updateMatch(match);
    }
}
