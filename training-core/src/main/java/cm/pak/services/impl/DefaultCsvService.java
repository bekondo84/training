package cm.pak.services.impl;

import cm.pak.services.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultCsvService implements CsvService {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultCsvService.class);

    @Override
    public <T> List<T> parseCsv(InputStream is, String[] headers, Class<T> type) throws UnsupportedEncodingException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        final List<T> datas = new ArrayList<>();

        reader.lines().skip(1L)
                .forEach(line ->{
                    try {
                        populateObject(headers, type, line)
                                .ifPresent(instance ->datas.add(instance));
                    } catch (Exception e) {
                        LOG.error(String.format("Unable to process line %s with error [%s]", line, e.getMessage()));
                        //e.printStackTrace();
                    }

                });
        return datas ;
    }
    private <T> Optional<T> populateObject(String[] headers, Class<T> type, String line) throws Exception {
        final String[] cols = line.split(";");

        if (cols.length != headers.length) {
            return  Optional.empty();
        }

        final T instance = (T) type.newInstance();

        for(int i = 0; i < headers.length; i++ ) {
            populateObjectField(headers, type, cols, instance, i);
        }
        return Optional.ofNullable(instance);
    }

    private <T> void populateObjectField(String[] headers, Class<T> type, String[] cols, T instance, int i) throws NoSuchFieldException, IllegalAccessException {
        final Field field = type.getDeclaredField(headers[i]);
        field.setAccessible(Boolean.TRUE);
        field.set(instance, cols[i]);
    }

}
