package pl.skowronski.addboardapp.advertisement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.skowronski.addboardapp.category.CategoryRepository;
import pl.skowronski.addboardapp.user.User;
import pl.skowronski.addboardapp.user.UserRepository;

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

    @RequestMapping("adds/{id}")
    public String showAdd(@PathVariable Integer id, Model model){
        Optional<Advertisement> add = advertisementRepository.findById(id);
        if(!add.isPresent()){
            return "redirect:/";
        }
        Advertisement advert = add.get();
        model.addAttribute("advert", advert);
        return "showAdd";
    }
}
