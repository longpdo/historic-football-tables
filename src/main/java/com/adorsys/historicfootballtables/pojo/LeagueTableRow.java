package com.adorsys.historicfootballtables.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LeagueTableRow {

    private int position;
    private String club;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int goalFor;
    private int goalAgainst;
    private int goalDifference;
    private int points;
}
