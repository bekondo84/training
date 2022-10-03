package cm.pak.training.controllers.core;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.core.MailTemplateData;
import cm.pak.training.facades.core.MailTemplateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mail-templates")
public class MailTemplateController {
    @Autowired
    private MailTemplateFacade facade;

    @GetMapping
    public ResponseEntity<List<MailTemplateData>> getTemplates() {
        return ResponseEntity.ok(facade.getMailTemplates());
    }

    @PostMapping
    public ResponseEntity<MailTemplateData> save(@RequestBody MailTemplateData source) throws ModelServiceException, ParseException {
        return ResponseEntity.ok(facade.save(source));
    }

    @GetMapping("/{pk}")
    public ResponseEntity<MailTemplateData> getTemplate(@PathVariable("pk") final Long pk){
         return ResponseEntity.ok(facade.getMailTemplate(pk));
    }

    @DeleteMapping("/{pk}")
    public void remove(@PathVariable("pk")final Long pk) {
           facade.remove(pk);
    }
}
