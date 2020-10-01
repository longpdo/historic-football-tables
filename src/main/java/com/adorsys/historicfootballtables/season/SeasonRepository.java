package com.adorsys.historicfootballtables.season;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SeasonRepository extends CrudRepository<Season, UUID> {

}
