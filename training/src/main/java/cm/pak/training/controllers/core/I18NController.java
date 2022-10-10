package cm.pak.training.controllers.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/i18n")
public class I18NController {

    @Autowired
    private MessageSource source;

    @GetMapping
    public ResponseEntity<Map<String, String>> translate(@RequestParam List<String> keys) {
        final Map<String, String> i18n = new HashMap<>();

        if (!CollectionUtils.isEmpty(keys)) {
            keys.forEach(key -> i18n.put(key, source.getMessage(key, null, Locale.getDefault())));
        }
        return ResponseEntity.ok(i18n);
    }
}
