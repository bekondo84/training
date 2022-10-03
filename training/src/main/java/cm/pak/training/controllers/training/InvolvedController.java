package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.InvolvedData;
import cm.pak.training.facades.training.InvolveFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/leaners")
@CrossOrigin
public class InvolvedController {

    @Autowired
    private InvolveFacade facade ;

    @GetMapping("/{session}")
    public ResponseEntity<List<InvolvedData>> getInvolveForSessionID(@PathVariable("session")Long sessoin) {
       return ResponseEntity.ok(facade.getInvolves(sessoin));
    }

    @PostMapping("/{session}")
    public ResponseEntity<InvolvedData> save(@PathVariable("session")Long session, @RequestBody InvolvedData source) throws ModelServiceException, ParseException {
        return ResponseEntity.ok(facade.save(session, source));
    }

    @GetMapping("/{session}/{pk}")
    public ResponseEntity<InvolvedData> getInvolve(@PathVariable("session") Long session, @PathVariable("pk") Long pk){
        return ResponseEntity.ok(facade.getInvolve(pk));
    }

    @DeleteMapping("/{session}/{pk}")
    public void remove(@PathVariable("session") Long session, @PathVariable("pk") Long pk) {
        facade.remove(pk);
    }
}
