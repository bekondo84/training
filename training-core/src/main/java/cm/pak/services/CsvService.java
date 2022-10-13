package cm.pak.services;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface CsvService {

    <T> List<T> parseCsv(final InputStream is, final String[] headers, Class<T> type) throws UnsupportedEncodingException;
}
