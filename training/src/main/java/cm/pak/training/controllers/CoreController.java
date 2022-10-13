package cm.pak.training.controllers;

import cm.pak.data.MetaData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.UserModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.services.EmailService;
import cm.pak.services.MetaService;
import cm.pak.training.MailHelper;
import cm.pak.training.beans.AbstractItemData;
import cm.pak.training.beans.security.ChangePasswordData;
import cm.pak.training.beans.security.JwtResponse;
import cm.pak.training.beans.security.UserDTO;
import cm.pak.training.exceptions.TrainingException;
import cm.pak.training.facades.core.ExtensionFacade;
import cm.pak.training.facades.core.SettingFacade;
import cm.pak.training.facades.security.UserFacade;
import cm.pak.training.security.JwtTokenService;
import cm.pak.training.tools.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping
@CrossOrigin
public class CoreController extends AbstractController{
    private static final Logger LOG = LoggerFactory.getLogger(CoreController.class);

    @Autowired
    private ExtensionFacade extensionFacade;
    @Autowired
    private MetaService metaService ;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenService jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ModelService modelService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Value("${spring.mail.templates.path}")
    private String mailTemplatesPath;
    @Autowired
    private UserFacade userFacade;

    public CoreController() {
        
    }

    @GetMapping
    public String home(Authentication authentication) {
          return "/home/template";
    }

    @PostMapping(value = "/api/v1/upload", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public void uploadFile(@RequestPart("file") MultipartFile file, final HttpSession session) throws IOException {
        session.setAttribute(Constants.SESSION_FILE, file.getInputStream());
    }
    @PostMapping("/api/v1/changePassord")
    @ResponseBody
    public ResponseEntity changePasword(@RequestBody ChangePasswordData source, Authentication auth) throws TrainingException, ModelServiceException {
        final UserModel user = flexibleSearch.find(UserModel.class, "code", auth.getName());
        if (!source.getNewPassword().trim().equals(source.getConfirmPasswword().trim())) {
            throw  new TrainingException (messageSource.getMessage("password.notmatche.exception", null, "password.notmatche.exception", Locale.getDefault()));
        }
        userFacade.setPassword(user.getPk(), source.getNewPassword());

        return ResponseEntity.ok().build();
    }

     @ResponseBody
     @GetMapping("/api/v1/meta/{name}")
     public ResponseEntity<MetaData> metaData(@PathVariable("name") String name) throws ClassNotFoundException {
         return ResponseEntity.ok(metaService.getMeta(name));
     }

    @ResponseBody
    @GetMapping("/api/v1/instance/{name}")
     public ResponseEntity emptyInstance(@PathVariable("name") String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return ResponseEntity.ok(metaService.getInstance(name));
    }

    @ResponseBody
    @GetMapping("/api/v1/search/{searchKey}/{value}/{target}")
    public ResponseEntity getItemBySearchKey(@PathVariable("searchKey") String key, @PathVariable("value") Object value, @PathVariable("target") String target) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object entity = Class.forName(target).newInstance();
        final String modelClassName = ((AbstractItemData)entity).getTargetEntity();
        final Class modelClass = Class.forName(modelClassName);
        try {
            Object result = flexibleSearch.find(modelClass, key, value);
            return ResponseEntity.ok(result);
        } catch (NoResultException e) {
            return ResponseEntity.ok(null);
        }

    }

    @GetMapping("/login")
    public String login(final Model model) {
            model.addAttribute("user", new UserDTO());
            return "/security/loginPage";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") final UserDTO user, final Model model)  {
        final Authentication auth;
        try {
            auth = authenticate(user.getUsername(), user.getPassword());
            SecurityContextHolder.getContext().setAuthentication(auth);
            final String token = jwtTokenUtil.generateToken(auth);
            model.addAttribute("token", token);
            return "/home/template";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/security/loginPage";
    }
    @RequestMapping(value = "/auth/token", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO authenticationRequest) throws Exception {

        final Authentication auth = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok(new JwtResponse(jwtTokenUtil.generateToken(auth)));
    }
    @GetMapping("/reset-password")
    public String resetPassord(final Model model) {
         model.addAttribute("user", new UserDTO());
         return "/security/resetPasswordPage";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("user") final UserDTO source, final Model model) throws TrainingException, MessagingException, ModelServiceException {
       try {
           final UserModel user = flexibleSearch.find(UserModel.class, "code", source.getUsername());
           final String password = passwordGenerator();
           userFacade.setPassword(user.getPk(), password);
           final String htmlBody = buildPasswordResetText(user, password);
           emailService.send(user.getEmail(), "password.reset.subject", htmlBody);
           final String message = messageSource.getMessage("reset.success", null, Locale.getDefault());
           model.addAttribute("success", message);
       } catch (NoResultException | EmptyResultDataAccessException ex ) {
           final String message = messageSource.getMessage("notuserfound.exception", null, Locale.getDefault());
           model.addAttribute("error", String.format(message, source.getUsername()));
       } catch (Exception ex) {

       }
        return "/security/resetPasswordPage";
    }

    private String buildPasswordResetText(UserModel source, final String password) {
        final Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("greeting", messageSource.getMessage("greeting.mail", null, Locale.getDefault()));
        templateModel.put("text00", messageSource.getMessage("passwordreset.mail.template00", null, Locale.getDefault()));
        templateModel.put("text01", messageSource.getMessage("passwordreset.mail.template01", null, Locale.getDefault()));
        templateModel.put("text02", String.format(messageSource.getMessage("passwordreset.mail.template02", null, Locale.getDefault()), source.getCode()));
        templateModel.put("text03", String.format(messageSource.getMessage("passwordreset.mail.template03", null, Locale.getDefault()), password));
        final Context context = new Context();
        context.setVariables(templateModel);
        final String htmlBody = MailHelper.thymeleafTemplateEngine(mailTemplatesPath).process("password-reset-template.html", context);
        return htmlBody;
    }

    private String passwordGenerator() {
        final Random generator = new Random();

        StringBuffer password = new StringBuffer();
       final char[] word = new char[6];
        for(int i=1 ; i <=6 ; i++) {
            word[i-1] = (char) (generator.nextInt(26)+'a');
        }
        return new String(word);
    }
    /**
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }
   **/
    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
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
