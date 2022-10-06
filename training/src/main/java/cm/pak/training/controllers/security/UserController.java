package cm.pak.training.controllers.security;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.ImportData;
import cm.pak.training.beans.security.SetPasswordData;
import cm.pak.training.beans.security.UserData;
import cm.pak.training.exceptions.TrainingException;
import cm.pak.training.facades.security.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserFacade facade ;

    @GetMapping
    public ResponseEntity<List<UserData>> getUsers() {
        return ResponseEntity.ok(facade.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserData> save(@RequestBody UserData source) throws ModelServiceException {
       return ResponseEntity.ok(facade.create(source));
    }

    @GetMapping("/{pk}")
    public ResponseEntity<UserData> getUser(@PathVariable("pk") final Long pk) {
        return ResponseEntity.ok(facade.getUser(pk));
    }

    @DeleteMapping("/{pk}")
    public void remove(@PathVariable("pk") final Long pk) {
        facade.remove(pk);
    }

    @GetMapping("/import/{pk}")
    public ResponseEntity<ImportData> getImport() {
       return  ResponseEntity.ok(new ImportData());
    }

    @PostMapping("/import")
    public ResponseEntity<ImportData> importData(@RequestBody ImportData source) {
        LOG.info("-----------------------------IMPORT -------------- : %s", source);
        return ResponseEntity.ok(source);
    }

    @GetMapping("/setPassword/{pk}")
    public ResponseEntity<SetPasswordData> setPasswordData(@PathVariable("pk")Long pk) {
        final SetPasswordData data = new SetPasswordData();
        data.setPk(pk);
        return ResponseEntity.ok(data);
    }
    @PostMapping("/setPassword/{pk}")
    public ResponseEntity<UserData> setPassword(@PathVariable("pk")Long pk, @RequestBody SetPasswordData source) throws ModelServiceException, TrainingException {
          return ResponseEntity.ok(facade.setPassword(pk,  source));
    }
}
