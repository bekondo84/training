package cm.pak.training.controllers.security;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.ImportData;
import cm.pak.training.beans.security.UserData;
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

    @GetMapping("/import")
    public ResponseEntity<ImportData> getImport() {
       return  ResponseEntity.ok(new ImportData());
    }

    @PostMapping("/import")
    public ResponseEntity<ImportData> importData(@RequestBody ImportData source) {
        LOG.info(String.format("------------------------- %s", source));
        return ResponseEntity.ok(source);
    }
}
