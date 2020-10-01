package com.adorsys.historicfootballtables.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor @ToString
public class Round {

    @Id @GeneratedValue
    @Getter
    private UUID id;

    @Getter @Setter @NonNull
    private int number;

    @OrderColumn @OneToMany(cascade = CascadeType.ALL) @JoinColumn(name = "round_id")
    @Getter @Setter @NonNull
    private Match[] matches;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "season_id")
    private Season season;
}
