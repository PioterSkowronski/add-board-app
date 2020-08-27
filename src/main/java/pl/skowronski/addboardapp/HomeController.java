package pl.skowronski.addboardapp;

import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.skowronski.addboardapp.advertisement.Advertisement;
import pl.skowronski.addboardapp.advertisement.AdvertisementRepository;
import pl.skowronski.addboardapp.category.Category;
import pl.skowronski.addboardapp.category.CategoryRepository;
import pl.skowronski.addboardapp.user.User;
import pl.skowronski.addboardapp.user.UserRepository;

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
        List<Category> categories = categoryRepository.findAll();
        List<User> users = userRepository.findAll();
        List<String> cats = (List<String>)model.getAttribute("category");
        model.addAttribute("adverts", adverts);
        model.addAttribute("categories", categories);
        return "adada";
    }

    @PostMapping("/")
    public String homeFiltered(HttpServletRequest request, Model model){
        List<Advertisement> adverts = advertisementRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<User> users = userRepository.findAll();
        String minStr = request.getParameter("min");
        logger.debug("minStr: " + minStr);
        Double min = 0.00;
        Double max = 0.00;
        if(minStr.equals(null)){
            min = 0.00;
        } else {
            min = Double.parseDouble(minStr);
        }
        String maxStr = request.getParameter("max");
        if(maxStr.equals(null)){
            max = 10000000.00;
        } else {
            max = Double.parseDouble(maxStr);
        }
        if(min > max){
            double tmp;
            tmp = min;
            min = max;
            max = tmp;
        }
        final double min1 = min;
        final double max1 = max;
        String[] cats = request.getParameterValues("category");
        List<String> categoryList = Arrays.asList(cats);
        List<Advertisement> adds = adverts.stream().filter(el -> el.getPrice() >= min1).filter(el -> el.getPrice() <= max1).collect(Collectors.toList());
        model.addAttribute("adverts", adds);
        model.addAttribute("categories", categories);
        model.addAttribute("categoryList", categoryList);
        return "adada";
    }
}