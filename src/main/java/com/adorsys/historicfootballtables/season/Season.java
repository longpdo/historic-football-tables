package com.adorsys.historicfootballtables.season;

import com.adorsys.historicfootballtables.round.Round;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor @ToString
public class Season {

    @Id @GeneratedValue
    @Getter
    private UUID id;

    @Getter @Setter @NonNull
    private String country;

    @Getter @Setter @NonNull
    private String name;

    @Getter @Setter @NonNull
    private String leagueRank;

    @Getter @Setter @NonNull
    private String season;

    @OrderColumn @OneToMany(cascade = CascadeType.ALL) @JoinColumn(name = "season_id")
    @Getter @Setter @NonNull
    private Round[] rounds;
}