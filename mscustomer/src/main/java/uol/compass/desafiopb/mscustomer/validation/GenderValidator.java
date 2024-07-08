package uol.compass.desafiopb.mscustomer.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uol.compass.desafiopb.mscustomer.enums.Gender;

public class GenderValidator implements ConstraintValidator<GenderValidation, Gender> {
    @Override
    public void initialize(GenderValidation constraintAnnotation) {
    }
    @Override
    public boolean isValid(Gender gender, ConstraintValidatorContext context) {
        return gender == Gender.M || gender == Gender.F;
    }
}