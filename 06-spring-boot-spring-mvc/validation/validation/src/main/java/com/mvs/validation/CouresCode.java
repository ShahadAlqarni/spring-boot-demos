package com.mvs.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CouresCodeConstrationValdition.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CouresCode {
    //define default coures code
    public String value() default "LUV";

    //define default error message
    public String message() default "Invalid course code";

    //define default groups
    public Class<?>[] groups() default {};

    //define default payload
    public Class<? extends Payload>[] payload() default {};

}
