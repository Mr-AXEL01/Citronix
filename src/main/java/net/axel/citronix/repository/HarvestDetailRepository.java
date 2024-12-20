package net.axel.citronix.repository;

import net.axel.citronix.domain.embeddeds.HarvestDetailKey;
import net.axel.citronix.domain.entities.HarvestDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HarvestDetailRepository extends JpaRepository<HarvestDetail, HarvestDetailKey> {
}
