package com.adorsys.historicfootballtables.dao;

import com.adorsys.historicfootballtables.model.Season;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SeasonRepository extends CrudRepository<Season, UUID> {

}
