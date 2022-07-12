package dev.marconymous.codesnippets.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static dev.marconymous.codesnippets.Utils.UUID_REGEX;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Marc Andri Fuchs
 * @version 1.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UUIDValidNotNull.Validator.class)
public @interface UUIDValidNotNull {
  /**
   * Message if the constraint is violated.
   *
   * @return the message
   */
  String message() default "UUID is not does not match " + UUID_REGEX;

  /**
   * Groups the constraint belongs to.
   *
   * @return the groups
   */
  Class<?>[] groups() default {};

  /**
   * Payload for the constraint.
   *
   * @return the payload
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Validator for the constraint.
   */
  class Validator implements ConstraintValidator<UUIDValidNotNull, String> {
    @Override
    public void initialize(UUIDValidNotNull constraintAnnotation) {
      ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      return value != null && value.matches(UUID_REGEX);
    }
  }
}
