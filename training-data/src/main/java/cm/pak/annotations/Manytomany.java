package cm.pak.annotations;

public @interface Manytomany {
    int sequence() default 0 ;
    String group() ;
    boolean editable() default true ;
    boolean updatable() default true;
    String  metadata() default "" ;
    String source() default "";
}
