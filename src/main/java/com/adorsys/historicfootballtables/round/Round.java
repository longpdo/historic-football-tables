package com.adorsys.historicfootballtables.round;

import com.adorsys.historicfootballtables.match.Match;
import com.adorsys.historicfootballtables.season.Season;

import javax.persistence.*;
import java.util.Arrays;
import java.util.UUID;

@Entity
public class Round {

    @Id
    @GeneratedValue
    private UUID id;
    private int number;
    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "round_id")
    private Match[] matches;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    private Season season;

    public Round() {
    }

    public Round(int number, Match[] matches) {
        this.number = number;
        this.matches = matches;
    }

    public Round(UUID id, int number, Match[] matches) {
        this.id = id;
        this.number = number;
        this.matches = matches;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Match[] getMatches() {
        return matches;
    }

    public void setMatches(Match[] matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", number=" + number +
                ", matches=" + Arrays.toString(matches) +
                ", season=" + season +
                '}';
    }
}
