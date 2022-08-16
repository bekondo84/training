package cm.pak.converters;

public interface Converter <T extends Object, Y extends Object>{

    Y convert( final T source) ;
}
