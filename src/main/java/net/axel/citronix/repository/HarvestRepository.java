package net.axel.citronix.repository;

import net.axel.citronix.domain.entities.Harvest;
import net.axel.citronix.domain.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {

    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Harvest h WHERE h.season = :season AND YEAR(h.harvestDate) = :year")
    Boolean existsBySeasonAndYear(Season season, int year);
}
