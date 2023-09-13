package myapp.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * defines a custom annotation that can be used to annotate fields and provides a pattern element to
 * specify the validation pattern.
 */

/**
 * The @Retention(RetentionPolicy.RUNTIME) annotation specifies that this custom annotation should
 * be retained at runtime. It means that the annotation information will be available to the program
 * during runtime, allowing the program to access and process the annotation.
 */
@Target(ElementType.FIELD) @Retention(RetentionPolicy.RUNTIME) public @interface Validate {
    String pattern();
}
