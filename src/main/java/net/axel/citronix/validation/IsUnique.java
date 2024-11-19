package net.axel.citronix.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import net.axel.citronix.validation.validator.UniqueValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueValidator.class)
public @interface IsUnique {

    String message() default "The value must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> entityClass();
}
