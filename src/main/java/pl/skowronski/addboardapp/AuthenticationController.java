package pl.skowronski.addboardapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.skowronski.addboardapp.Role.Role;
import pl.skowronski.addboardapp.Role.RoleRepository;
import pl.skowronski.addboardapp.user.User;
import pl.skowronski.addboardapp.user.UserRepository;
import pl.skowronski.addboardapp.user.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;

@Controller
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String saveUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Popraw błędy w formularzu!");
            return "register";
        } else if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            model.addAttribute("message", "Użytkownik " + user.getUserName() + " już istnieje!");
            return "register";
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("message", "Adres " + user.getEmail() + " jest już zarejestrowany!");
            return "register";
        } else if (userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            model.addAttribute("message", "Podany numer telefonu jest już zarejestrowany!");
            return "register";
        } else {
            model.addAttribute("message", "Użytkownik został zarejestrowany!");
            userService.saveUser(user);
        }
        return "register";
    }
}
