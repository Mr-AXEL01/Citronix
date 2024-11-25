package net.axel.citronix.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.domain.dtos.farm.FarmResponseDTO;
import net.axel.citronix.domain.dtos.farm.FarmSearchDTO;
import net.axel.citronix.domain.entities.Farm;
import net.axel.citronix.mapper.FarmMapper;
import net.axel.citronix.repository.FarmCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FarmCustomRepositoryImpl implements FarmCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private final FarmMapper mapper;

    @Override
    public List<FarmResponseDTO> searchFarms(FarmSearchDTO criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> query = cb.createQuery(Farm.class);
        Root<Farm> farmRoot = query.from(Farm.class);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.name() != null && !criteria.name().isEmpty()) {
            predicates.add(cb.like(cb.lower(farmRoot.get("name")), "%" + criteria.name().toLowerCase() + "%"));
        }

        if (criteria.location() != null && !criteria.location().isEmpty()) {
            predicates.add(cb.like(cb.lower(farmRoot.get("location")), "%" + criteria.location().toLowerCase() + "%"));
        }

        if (criteria.minSize() != null) {
            predicates.add(cb.greaterThanOrEqualTo(farmRoot.get("size"), criteria.minSize()));
        }

        if (criteria.maxSize() != null) {
            predicates.add(cb.lessThanOrEqualTo(farmRoot.get("size"), criteria.maxSize()));
        }

        if (criteria.createdAfter() != null) {
            predicates.add(cb.greaterThanOrEqualTo(farmRoot.get("creationDate"), criteria.createdAfter()));
        }

        if (criteria.createdBefore() != null) {
            predicates.add(cb.lessThanOrEqualTo(farmRoot.get("creationDate"), criteria.createdBefore()));
        }

        query.where(cb.and(predicates.toArray(new Predicate[0])));


        List<Farm> farms = entityManager.createQuery(query).getResultList();

        return farms.stream().map(mapper::toResponseDto).toList();
    }
}
