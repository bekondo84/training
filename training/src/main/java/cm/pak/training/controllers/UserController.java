package cm.pak.training.controllers;

import cm.pak.training.beans.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public String create(@ModelAttribute("user") UserData user, Model model){

        return null ;
    }
}
