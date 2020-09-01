package pl.skowronski.addboardapp.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.skowronski.addboardapp.model.Advertisement;
import pl.skowronski.addboardapp.repository.AdvertisementRepository;
import pl.skowronski.addboardapp.service.AdvertisementService;
import pl.skowronski.addboardapp.model.Category;
import pl.skowronski.addboardapp.repository.CategoryRepository;
import pl.skowronski.addboardapp.model.User;
import pl.skowronski.addboardapp.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AdvertisementController {

    private final AdvertisementRepository advertisementRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final AdvertisementService advertisementService;


    public AdvertisementController(AdvertisementRepository advertisementRepository, CategoryRepository categoryRepository, UserRepository userRepository, AdvertisementService advertisementService) {
        this.advertisementRepository = advertisementRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.advertisementService = advertisementService;
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
        Advertisement advertisement = advertisementRepository.findById(id).get();
        model.addAttribute("advertisement", advertisement);
        return "editForm";
    }

    @PostMapping("/edit")
    public String confirmEdit(@Valid Advertisement advertisement, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "Popraw błędy w formularzu!");
            return "editForm";
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
            advertisementService.updateAdvertisement(advertisement);
            model.addAttribute("message", "Zaktualizowano ogłoszenie");
            return "editForm";
        }
    }

    @GetMapping("/test")
    @ResponseBody
    public String saveSTH(){
        Advertisement advertisement = new Advertisement();
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        User user = userRepository.findByEmail(email).get();
        advertisement.setUser(user);
        Category category = new Category();
        category.setId(1);
        category.setName("Usługi");
        advertisement.setCategory(category);
        advertisement.setPrice(150.25);
        advertisement.setDescription("Zabawa w chowanego");
        advertisement.setTitle("Sprzedam film z youtube");
        advertisementService.updateAdvertisement(advertisement);
        return advertisement.toString();
    }

    @GetMapping("/add/create")
    public String createAddForm(Model model){
        model.addAttribute("advertisement", new Advertisement());
        return "createForm";
    }

    @PostMapping("/add/create")
    public String crateAdd(@Valid Advertisement advertisement, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("message", "Popraw błędy w formularzu!");
            return "editForm";
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

    @GetMapping("/delete")
    public String deleteAdd(@RequestParam long id){
        advertisementRepository.deleteById(id);
        return "redirect:/home";
    }
}
