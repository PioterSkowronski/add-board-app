package pl.skowronski.addboardapp.advertisement;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.skowronski.addboardapp.category.Category;
import pl.skowronski.addboardapp.category.CategoryRepository;
import pl.skowronski.addboardapp.user.User;
import pl.skowronski.addboardapp.user.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AdvertisementController {

    private final AdvertisementRepository advertisementRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;


    public AdvertisementController(AdvertisementRepository advertisementRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return this.categoryRepository.findAll();
    }

    @RequestMapping("adds/{id}")
    public String showAdd(@PathVariable Long id, Model model) {
        Optional<Advertisement> add = advertisementRepository.findById(id);
        if (!add.isPresent()) {
            return "redirect:/";
        }
        Advertisement advert = add.get();
        model.addAttribute("advert", advert);
        return "showAdd";
    }

    @GetMapping("/edit")
    public String editAdd(@RequestParam long id, Model model){
        Advertisement advert = advertisementRepository.findById(id).get();
        model.addAttribute("advert", advert);
        return "editForm";
    }

    /*@PostMapping("/add/edit")
    public String confirmEdit(@Valid Advertisement advert, BindingResult result, Model model) {
        System.out.println(advert.toString());
        if (result.hasErrors()) {
            model.addAttribute("message", "Popraw błędy!");
            return "register";
        } else {
            model.addAttribute("message", "Ogłoszenie zostało zaktualizowane!");
            advertisementRepository.save(advert);
        }
        return "editForm";
    }*/

    @GetMapping("/add/create")
    public String createAddForm(Model model){
        model.addAttribute("advertisement", new Advertisement());
        return "createForm";
    }

    @PostMapping("/add/create")
    public String crateAdd(@Valid Advertisement advertisement, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("message", "Popraw błędy w formularzu!");
            return "register";
        } else {
            String email;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                email = ((UserDetails) principal).getUsername();
            } else {
                email = principal.toString();
            }
            User user = userRepository.findByEmail(email).get();
            advertisement.setUser(user);
            model.addAttribute("message", "Dodano ogłoszenie!");
            advertisementRepository.save(advertisement);
            return "createForm";
        }
    }
}
