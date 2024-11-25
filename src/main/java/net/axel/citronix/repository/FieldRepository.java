package net.axel.citronix.repository;

import net.axel.citronix.domain.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository extends JpaRepository<Field, Long> {
}
