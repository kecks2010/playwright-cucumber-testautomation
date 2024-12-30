package de.mirko_werner.playwright_cucumber.models;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Documented
public @interface EndPoint {

    String value() default "";
}
