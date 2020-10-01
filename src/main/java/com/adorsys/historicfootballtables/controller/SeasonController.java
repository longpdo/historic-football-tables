package com.adorsys.historicfootballtables.controller;

import com.adorsys.historicfootballtables.model.LeagueTableRow;
import com.adorsys.historicfootballtables.model.Season;
import com.adorsys.historicfootballtables.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SeasonController {

    @Autowired
    private SeasonService seasonService;

    @RequestMapping("/seasons")
    public List<Season> getAllSeasons() {
        return seasonService.getAllSeasons();
    }

    @RequestMapping("/season")
    public Season getSeason(@RequestParam UUID id) {
        return seasonService.getSeason(id);
    }

    @PostMapping(value="/season")
    public void addSeason(@RequestBody Season season) {
        seasonService.addSeason(season);
    }

    @DeleteMapping(value="/season")
    public void removeSeason(@RequestParam UUID id) {
        seasonService.removeSeason(id);
    }

    @RequestMapping("/season/table")
    public List<LeagueTableRow> getCurrentSeasonTable(@RequestParam UUID id) {
        return seasonService.getCurrentSeasonTable(id);
    }

    @RequestMapping("/season/table/{roundNumber}")
    public List<LeagueTableRow> getPastSeasonTable(@RequestParam UUID id, @PathVariable int roundNumber) {
        return seasonService.getPastSeasonTable(id, roundNumber);
    }
}
