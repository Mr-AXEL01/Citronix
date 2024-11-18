package net.axel.citronix.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import net.axel.citronix.validation.validator.ExistsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ExistsValidator.class)
public @interface Exists {

    String message() default "Entity does not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();

    Class<?> entityClass();
}
