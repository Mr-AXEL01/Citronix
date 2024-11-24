package net.axel.citronix.repository;

import net.axel.citronix.domain.entities.Harvest;
import net.axel.citronix.domain.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Boolean existsByHarvest(Harvest harvest);
}
