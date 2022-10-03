package cm.pak.training.controllers.training;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.training.ClassRoomData;
import cm.pak.training.facades.training.ClassRoomFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classrooms")
@CrossOrigin
public class ClassRoomController {

    @Autowired
    private ClassRoomFacade facade ;

    @GetMapping
    public ResponseEntity<List<ClassRoomData>> getClassrooms() {
        return ResponseEntity.ok(facade.getClassRooms());
    }
    @GetMapping("/{pk}")
    public ResponseEntity<ClassRoomData> getClassroom(@PathVariable("pk") final Long pk) {
           return ResponseEntity.ok(facade.getClassRoom(pk));
    }
    @PostMapping
    public ResponseEntity<ClassRoomData> save(@RequestBody ClassRoomData source) throws ModelServiceException {
        return ResponseEntity.ok(facade.save(source));
    }
    @DeleteMapping("/{pk}")
    public void remove(@PathVariable("pk") Long pk) {
        facade.remove(pk);
    }
}
