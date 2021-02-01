package main;

import javax.lang.model.element.TypeElement;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface WhenDisabled{
    String defaultReturn() default "";
}
