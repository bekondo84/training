package cm.pak.training.controllers;

import cm.pak.data.MetaData;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.services.MetaService;
import cm.pak.training.beans.AbstractItemData;
import cm.pak.training.beans.security.JwtResponse;
import cm.pak.training.beans.security.UserDTO;
import cm.pak.training.facades.core.ExtensionFacade;
import cm.pak.training.security.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@Controller
@RequestMapping
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

    @GetMapping
    public String home(Authentication authentication) {
          return "/home/template";
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
            model.addAttribute("token", jwtTokenUtil.generateToken(auth));
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
}
