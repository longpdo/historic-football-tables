package com.adorsys.historicfootballtables.season;

import com.adorsys.historicfootballtables.match.Match;
import com.adorsys.historicfootballtables.pojo.LeagueTableRow;
import com.adorsys.historicfootballtables.round.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    public List<Season> getAllSeasons() {
        List<Season> seasons = new ArrayList<>();
        seasonRepository.findAll().forEach(seasons::add);
        return seasons;
    }

    public Season getSeason(UUID id) {
        return seasonRepository.findById(id).orElse(null);
    }

    public void addSeason(Season season) {
        if (season.getCountry().equals("Germany") && season.getRounds().length == 34) {
            seasonRepository.save(season);
        }
        if (season.getCountry().equals("Germany") && season.getRounds().length < 34) {
            fillWithEmptyRounds(season, 34, 9);
            seasonRepository.save(season);
        }
        if (season.getCountry().matches("Spain|England|Italy|France") && season.getRounds().length == 38) {
            seasonRepository.save(season);
        }
        if (season.getCountry().matches("Spain|England|Italy|France") && season.getRounds().length < 38) {
            fillWithEmptyRounds(season, 38, 10);
            seasonRepository.save(season);
        }
    }

    public void removeSeason(UUID id) {
        seasonRepository.deleteById(id);
    }

    public List<LeagueTableRow> getCurrentSeasonTable(UUID id) {
        Round[] rounds = getSeason(id).getRounds();
        // Filter out empty rounds which were added in fillWithEmptyRounds()
        for (Round round: rounds) {
            if (round.getMatches()[0].getDate().equals("0000-00-00")) {
                rounds = Arrays.copyOfRange(rounds, 0, round.getNumber()-1);
                break;
            }
        }

        HashMap<String, LeagueTableRow> emptySeasonTable = initSeasonTableWithClubs(rounds);

        return aggregateSeasonTable(emptySeasonTable, rounds);
    }

    public List<LeagueTableRow> getPastSeasonTable(UUID id, int roundNumber) {
        Round[] rounds = getSeason(id).getRounds();
        // Filter out rounds which are not part of the past season table
        if (roundNumber >= 0) {
            rounds = Arrays.copyOfRange(rounds, 0, roundNumber);
        }

        HashMap<String, LeagueTableRow> emptySeasonTable = initSeasonTableWithClubs(rounds);

        return aggregateSeasonTable(emptySeasonTable, rounds);
    }

    private <T> T[] concatenateArray(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    private void fillWithEmptyRounds(Season season, int maxRounds, int maxMatches) {
        Round[] emptyRounds = new Round[maxRounds-season.getRounds().length];

        for (int i = season.getRounds().length; i < maxRounds; i++) {
            Match[] emptyMatches = new Match[maxMatches];
            for (int j = 0; j < emptyMatches.length; j++) {
                emptyMatches[j] = new Match("0000-00-00", "Home Team", "Away Team", "0:0");
            }
            emptyRounds[i-season.getRounds().length] = new Round(i+1, emptyMatches);
        }

        season.setRounds(concatenateArray(season.getRounds(), emptyRounds));
    }

    private HashMap<String, LeagueTableRow> initSeasonTableWithClubs(Round[] rounds) {
        HashMap<String, LeagueTableRow> seasonTable = new HashMap<>();
        for (Match match: rounds[0].getMatches()) {
            LeagueTableRow rowForHomeTeam = new LeagueTableRow(0, match.getHomeTeam(), 0,0,0,0,0,0,0,0);
            LeagueTableRow rowForAwayTeam = new LeagueTableRow(0, match.getAwayTeam(), 0,0,0,0,0,0,0,0);

            seasonTable.put(match.getHomeTeam(), rowForHomeTeam);
            seasonTable.put(match.getAwayTeam(), rowForAwayTeam);
        }

        return seasonTable;
    }

    private List<LeagueTableRow> aggregateSeasonTable(HashMap<String, LeagueTableRow> seasonTable, Round[] rounds) {
        // Aggregate values for played, goalFor, goalAgainst, won, drawn, lost from all the matches
        for (Round round: rounds) {
            for (Match tmpMatch: round.getMatches()) {
                LeagueTableRow rowOfHomeTeam = seasonTable.get(tmpMatch.getHomeTeam());
                LeagueTableRow rowOfAwayTeam = seasonTable.get(tmpMatch.getAwayTeam());
                int homeTeamScore = Integer.parseInt(tmpMatch.getScore().split(":")[0]);
                int awayTeamScore = Integer.parseInt(tmpMatch.getScore().split(":")[1]);

                rowOfHomeTeam.setPlayed(rowOfHomeTeam.getPlayed() + 1);
                rowOfHomeTeam.setGoalFor(rowOfHomeTeam.getGoalFor() + homeTeamScore);
                rowOfHomeTeam.setGoalAgainst(rowOfHomeTeam.getGoalAgainst() + awayTeamScore);

                rowOfAwayTeam.setPlayed(rowOfAwayTeam.getPlayed() + 1);
                rowOfAwayTeam.setGoalFor(rowOfAwayTeam.getGoalFor() + awayTeamScore);
                rowOfAwayTeam.setGoalAgainst(rowOfAwayTeam.getGoalAgainst() + homeTeamScore);

                if (homeTeamScore > awayTeamScore) {
                    rowOfHomeTeam.setWon(rowOfHomeTeam.getWon() + 1);
                    rowOfAwayTeam.setLost(rowOfAwayTeam.getLost() + 1);
                }
                if (homeTeamScore == awayTeamScore) {
                    rowOfHomeTeam.setDrawn(rowOfHomeTeam.getDrawn() + 1);
                    rowOfAwayTeam.setDrawn(rowOfAwayTeam.getDrawn() + 1);
                }
                if (homeTeamScore < awayTeamScore) {
                    rowOfAwayTeam.setWon(rowOfAwayTeam.getWon() + 1);
                    rowOfHomeTeam.setLost(rowOfHomeTeam.getLost() + 1);
                }
            }
        }

        // Calculate goalDifference and points
        for (LeagueTableRow row: seasonTable.values()) {
            row.setGoalDifference(row.getGoalFor() - row.getGoalAgainst());
            row.setPoints(row.getWon() * 3 + row.getDrawn());
        }

        // Sort seasonTable in descending order based on points
        List<LeagueTableRow> seasonTableSorted = new ArrayList<>(seasonTable.values());
        Collections.sort(seasonTableSorted, Collections.reverseOrder(Comparator.comparing(LeagueTableRow::getPoints)));

        // Set position
        for (int i = 0; i < seasonTableSorted.size(); i++) {
            seasonTableSorted.get(i).setPosition(i+1);
        }

        return seasonTableSorted;
    }
}
