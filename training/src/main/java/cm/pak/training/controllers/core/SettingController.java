package cm.pak.training.controllers.core;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.core.SettingData;
import cm.pak.training.facades.core.SettingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/settings")
@CrossOrigin
public class SettingController {

    @Autowired
    private SettingFacade settingFacade;

    @GetMapping
    public ResponseEntity<SettingData> getSetting() {
        return ResponseEntity.ok(settingFacade.getSetting());
    }

    @PostMapping
    public ResponseEntity<SettingData> save(@RequestBody SettingData source) throws ModelServiceException, ParseException {
        return ResponseEntity.ok(settingFacade.save(source));
    }
}
