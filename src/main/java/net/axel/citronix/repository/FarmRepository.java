package net.axel.citronix.repository;

import net.axel.citronix.domain.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
