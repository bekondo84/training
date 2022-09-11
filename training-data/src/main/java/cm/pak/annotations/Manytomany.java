package cm.pak.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Manytomany {
    int sequence() default 0 ;
    String group() ;
    boolean editable() default true ;
    boolean deletable() default true;
    String  metadata() default "" ;
    String source() default "";
}
