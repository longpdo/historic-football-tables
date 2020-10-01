package com.adorsys.historicfootballtables.season;

import com.adorsys.historicfootballtables.round.Round;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

@Entity
public class Season {

    @Id
    @GeneratedValue
    private UUID id;
    private String country;
    private String name;
    private String leagueRank;
    private String season;
    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id")
    private Round[] rounds;

    public Season() {
    }

    public Season(UUID id, String country, String name, String leagueRank, String season, Round[] rounds) {
        this.id = id;
        this.country = country;
        this.name = name;
        this.leagueRank = leagueRank;
        this.season = season;
        this.rounds = rounds;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeagueRank() {
        return leagueRank;
    }

    public void setLeagueRank(String leagueRank) {
        this.leagueRank = leagueRank;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public Round[] getRounds() {
        return rounds;
    }

    public void setRounds(Round[] rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "Season{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", leagueRank='" + leagueRank + '\'' +
                ", season='" + season + '\'' +
                ", rounds=" + Arrays.toString(rounds) +
                '}';
    }
}