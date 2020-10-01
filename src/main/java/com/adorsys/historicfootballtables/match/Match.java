package com.adorsys.historicfootballtables.match;

import com.adorsys.historicfootballtables.round.Round;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Match {

    @Id
    @GeneratedValue
    private UUID id;
    private String date;
    private String homeTeam;
    private String awayTeam;
    private String score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id")
    private Round round;

    public Match() {
    }

    public Match(String date, String homeTeam, String awayTeam, String score) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
    }

    public Match(UUID id, String date, String homeTeam, String awayTeam, String score) {
        this.id = id;
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = score;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", score='" + score + '\'' +
                ", round=" + round +
                '}';
    }
}
