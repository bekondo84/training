package cm.pak.training.controllers.security;

import cm.pak.data.FieldData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.UserModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.services.MetaService;
import cm.pak.training.beans.ImportUserData;
import cm.pak.training.beans.security.SetPasswordData;
import cm.pak.training.beans.security.UserData;
import cm.pak.training.controllers.AbstractController;
import cm.pak.training.exceptions.TrainingException;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.security.UserFacade;
import cm.pak.training.populators.security.UserPopulator;
import cm.pak.training.tools.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController extends AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserFacade facade ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private UserPopulator populator;
    @Autowired
    private MetaService metaService;
    @Autowired
    private MessageSource messageSource;


    @GetMapping("/fields")
    public ResponseEntity<Set<FieldData>> getFields() {
       return ResponseEntity.ok(metaService.getExportedFields(UserData.class));
    }
    @GetMapping
    public ResponseEntity<List<UserData>> getUsers(@RequestParam(required = false) String search) {
        List<UserModel> users = searchData(UserModel.class, 0, 50, buildSearchFilter(UserData.class, search));

        if (!CollectionUtils.isEmpty(users)) {
            return ResponseEntity.ok(users
                    .stream()
                    .map(us ->populator.populate(us))
                    .collect(Collectors.toList()));
        }
        return ResponseEntity.ok(new ArrayList<>());
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
    public ResponseEntity<ImportUserData> getImport() {
       return  ResponseEntity.ok(new ImportUserData());
    }

    @PostMapping("/import")
    public ResponseEntity<ImportUserData> importData(@RequestBody ImportUserData source, final HttpSession session) throws TrainingException, UnsupportedEncodingException, ModelServiceException {

        final InputStream is = (InputStream) session.getAttribute(Constants.SESSION_FILE);

        if (Objects.isNull(is)) {
            throw new TrainingException(messageSource.getMessage("filenotfound.exception", null, "filenotfound.exception", Locale.getDefault()));
        }
        if (CollectionUtils.isEmpty(source.getFields())) {
            throw new TrainingException(messageSource.getMessage("headersnotfond.exception", null, "headersnotfond.exception", Locale.getDefault()));
        }
        if (!StringUtils.hasText(source.getType())) {
            throw new TrainingException(messageSource.getMessage("typenotfound.exception", null, "typenotfound.exception", Locale.getDefault()));
        }
        List<String> headers= source.getFields()
                .stream()
                .map(f -> f.getName())
                .collect(Collectors.toList());
        facade.updateUsersFromInputStream(is, source.getType(), headers);
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

    @Override
    protected FlexibleSearch getFlexibleSearch() {
        return flexibleSearch;
    }

    @Override
    protected SettingFacade getSettingFacade() {
        return null;
    }
}
