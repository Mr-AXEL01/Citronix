package net.axel.citronix.validation.validator;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import net.axel.citronix.validation.IsUnique;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<IsUnique, Object> {

    private final EntityManager entityManager;
    private Class<?> entityClass;
    private String fieldName;

    @Override
    public void initialize(IsUnique constraintAnnotation) {
        entityClass = constraintAnnotation.entityClass();
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext Context) {
        if (value == null) return true;

        final String queryStr = String.format(
                "SELECT COUNT(e) FROM %s e WHERE e.%s = :value",
                entityClass.getSimpleName(), fieldName
        );

        Long count = entityManager.createQuery(queryStr, Long.class)
                .setParameter("value", value)
                .getSingleResult();

        return count == 0;
    }
}
