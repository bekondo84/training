package cm.pak.training.controllers.core;

import cm.pak.training.beans.core.MailData;
import cm.pak.training.facades.core.MailFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mails")
public class MailController {

    @Autowired
    private MailFacade facade;

    @GetMapping
    public ResponseEntity<List<MailData>> getMails() {
        return ResponseEntity.ok(facade.getMails());
    }

    @GetMapping("/{pk}")
    public ResponseEntity<MailData> getMail(@PathVariable("pk") final Long pk) {
        return ResponseEntity.ok(facade.getMail(pk));
    }

    @DeleteMapping("/{pk}")
    public void remove(@PathVariable("pk") final Long pk) {
        facade.remove(pk);
    }
}
