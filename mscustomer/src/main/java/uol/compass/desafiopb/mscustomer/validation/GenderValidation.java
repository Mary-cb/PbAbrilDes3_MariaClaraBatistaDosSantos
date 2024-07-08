package uol.compass.desafiopb.mscustomer.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenderValidation {
    String message() default "Invalid gender. Only 'M' and 'F' are allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
