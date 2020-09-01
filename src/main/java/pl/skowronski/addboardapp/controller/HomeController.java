package pl.skowronski.addboardapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.skowronski.addboardapp.model.Advertisement;
import pl.skowronski.addboardapp.repository.AdvertisementRepository;
import pl.skowronski.addboardapp.model.Category;
import pl.skowronski.addboardapp.repository.CategoryRepository;
import pl.skowronski.addboardapp.model.User;
import pl.skowronski.addboardapp.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private final AdvertisementRepository advertisementRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public HomeController(AdvertisementRepository advertisementRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @RequestMapping("/")
    public String home(Model model) {
        List<Advertisement> adverts = advertisementRepository.findAll();
        for (Advertisement advert : adverts) {
        }
        List<Category> categories = categoryRepository.findAll();
        List<User> users = userRepository.findAll();
        List<String> cats = (List<String>) model.getAttribute("category");
        model.addAttribute("adverts", adverts);
        model.addAttribute("categories", categories);
        return "landingPage";
    }

    @PostMapping("/")
    public String homeFiltered(HttpServletRequest request, Model model) {
        List<Advertisement> adverts = advertisementRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<User> users = userRepository.findAll();
        String minimum = request.getParameter("min");
        String maximum = request.getParameter("max");
        double min = 0;
        double max = 1000000;

        if(!minimum.equals("")){
            min= Double.parseDouble(minimum);
        }

        if(!maximum.equals("")){
            max = Double.parseDouble(maximum);
        }

        if (min > max) {
            double tmp;
            tmp = min;
            min = max;
            max = tmp;
        }
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        final double min1 = min;
        final double max1 = max;
        String[] cats = request.getParameterValues("category");
        List<String> categoryList = Arrays.asList(cats);
        List<Advertisement> adds = adverts.stream().filter(el -> el.getPrice() >= min1).filter(el -> el.getPrice() <= max1).collect(Collectors.toList());
        model.addAttribute("adverts", adds);
        model.addAttribute("categories", categories);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        return "filteredList";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        User user = userRepository.findByEmail(email).get();
        List<Advertisement> adverts = advertisementRepository.findAllByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("adverts", adverts);
        return "home";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/boot")
    public String boot(Model model){
        List<Advertisement> adverts = advertisementRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<User> users = userRepository.findAll();
        List<String> cats = (List<String>) model.getAttribute("category");
        logger.debug("wypisz coś");
        model.addAttribute("adverts", adverts);
        model.addAttribute("categories", categories);
        return "bootstrap";
    }

    @RequestMapping("/search")
    public String showSearchedContent(@RequestParam String value, Model model){
        List<Advertisement> adverts = advertisementRepository.findByContent(value);
        model.addAttribute("adverts",adverts);
        return "searchPage";
    }


}