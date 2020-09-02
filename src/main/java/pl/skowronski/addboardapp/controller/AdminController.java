package pl.skowronski.addboardapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.skowronski.addboardapp.model.Advertisement;
import pl.skowronski.addboardapp.model.Category;
import pl.skowronski.addboardapp.model.User;
import pl.skowronski.addboardapp.repository.AdvertisementRepository;
import pl.skowronski.addboardapp.repository.CategoryRepository;
import pl.skowronski.addboardapp.repository.UserRepository;
import pl.skowronski.addboardapp.service.AdvertisementService;
import pl.skowronski.addboardapp.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final AdvertisementService advertisementService;


    public AdminController(AdvertisementRepository advertisementRepository, UserRepository userRepository, CategoryRepository categoryRepository, UserService userService, AdvertisementService advertisementService) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.advertisementService = advertisementService;
    }

    @GetMapping("")
    public String adminPage(Model model) {
        List<Advertisement> advertisements = advertisementRepository.findAll();
        model.addAttribute("advertisements", advertisements);
        return "admin";
    }

    @GetMapping("/adds")
    public String getAdds(Model model) {
        List<Advertisement> advertisements = advertisementRepository.findAll();
        model.addAttribute("advertisements", advertisements);
        return "admin";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        User admin = userRepository.findByEmail(advertisementService.getEmailOfLoggedUser()).get();
        model.addAttribute("admin", admin);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "adminUsers";
    }

    @GetMapping("/categories")
    public String getCategories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "adminCategories";
    }

    @GetMapping("/category/delete")
    public String deleteCategory(@RequestParam long id, Model model) {
        Category category;
        if (categoryRepository.findById(id).isPresent()) {
            category = categoryRepository.findById(id).get();
        } else {
            model.addAttribute("message", "Nie ma takiej kategorii!");
            return "error";
        }
        categoryRepository.deleteById(id);
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("message", "Usunięto kategorię " + category.getName());
        model.addAttribute("categories", categories);
        return "adminCategories";
    }

    @GetMapping("/category/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("Category", new Category());
        return "adminCategoryAdd";
    }

    @PostMapping("/category/add")
    public String addCategory(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Popraw błędy w formularzu!");
            return "adminCategoryAdd";
        }
        model.addAttribute("message", "Dodano kategorię!");
        categoryRepository.save(category);
        return "adminCategoryAdd";
    }

    @GetMapping("/advertisement/delete")
    public String deleteAdd(@RequestParam long id, Model model) {
        if (advertisementRepository.findById(id).isPresent()) {
            advertisementRepository.deleteById(id);
            List<Advertisement> advertisements = advertisementRepository.findAll();
            model.addAttribute("advertisements", advertisements);
            model.addAttribute("message", "Usunięto ogłoszenie");
            return "admin";
        }
        model.addAttribute("message", "Ogłoszenie, które chcesz usunąć, nie istnieje!");
        return "error";
    }

    @GetMapping("/users/status")
    public String switchStatus(@RequestParam long id, Model model) {
        User user;
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
        } else {
            model.addAttribute("message", "Nie ma takiego użytkownika!");
            return "error";
        }
        if (user.getStatus().equals("VERIFIED")) {
            user.setStatus("NOT VERIFIED");
        } else {
            user.setStatus("VERIFIED");
        }
        userService.updateUser(user);
        User admin = userRepository.findByEmail(advertisementService.getEmailOfLoggedUser()).get();
        model.addAttribute("admin", admin);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("message", "Zmieniono status użytkownika " + user.getUserName());
        return "adminUsers";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam long id, Model model) {
        User user;
        if (userRepository.findById(id).isPresent()) {
            user = userRepository.findById(id).get();
        } else {
            model.addAttribute("message", "Nie ma takiego użytkownika!");
            return "error";
        }
        userRepository.deleteById(id);
        User admin = userRepository.findByEmail(advertisementService.getEmailOfLoggedUser()).get();
        model.addAttribute("admin", admin);
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("message", "Usunięto użytkownika " + user.getUserName());
        return "adminUsers";
    }

}
