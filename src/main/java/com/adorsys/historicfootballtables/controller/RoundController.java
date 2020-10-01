package com.adorsys.historicfootballtables.controller;

import com.adorsys.historicfootballtables.model.Round;
import com.adorsys.historicfootballtables.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class RoundController {

    @Autowired
    private RoundService roundService;

    @RequestMapping("/rounds")
    public List<Round> getAllRoundsBySeason(@RequestParam UUID seasonId) {
        return roundService.getAllRoundsBySeason(seasonId);
    }

    @RequestMapping("/round")
    public Round getRound(@RequestParam UUID roundId) {
        return roundService.getRound(roundId);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/round")
    public void updateRound(@RequestBody Round round) {
        roundService.updateRound(round);
    }
}
