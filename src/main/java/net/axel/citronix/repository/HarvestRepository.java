package net.axel.citronix.repository;

import net.axel.citronix.domain.entities.Harvest;
import net.axel.citronix.domain.enums.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    Boolean existsBySeason(Season season);
}
