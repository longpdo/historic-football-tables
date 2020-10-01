package com.adorsys.historicfootballtables.match;

import com.adorsys.historicfootballtables.round.Round;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor @ToString
public class Match {

    @Id @GeneratedValue
    @Getter
    private UUID id;

    @Getter @Setter @NonNull
    private String date;

    @Getter @Setter @NonNull
    private String homeTeam;

    @Getter @Setter @NonNull
    private String awayTeam;

    @Getter @Setter @NonNull
    private String score;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "round_id")
    private Round round;
}

