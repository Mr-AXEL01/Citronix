package net.axel.citronix.repository;

import net.axel.citronix.domain.entities.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Tree, Long> {
}
