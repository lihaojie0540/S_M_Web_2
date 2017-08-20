package mvc;

import javax.annotation.Resource;

import model.Spitter;
import model.Spittle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.SpitterService;

import java.util.List;
import java.util.Map;

@Controller("homeController")
public class HomeController {
    public static final int DEFAULT_SPITTLES_PER_RESULT = 25;

    @Resource
    private SpitterService spitterServiceImpl;

    @RequestMapping({"/", "/home"})
    public String showHomePage(Map<String, Object> model) {
        List<Spittle> s = spitterServiceImpl.getRecentSpittles(DEFAULT_SPITTLES_PER_RESULT);
        model.put("spittles", s);
        return "home";
    }

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @RequestMapping("/register")
    public String showRegisterPage(Model model) {
        Spitter spitter = new Spitter();
        model.addAttribute(spitter);
        return "register";
    }

    @RequestMapping("/404")
    public String showPageNotFound() {
        return "404";
    }

    @RequestMapping("/accessdenied")
    public String showAccessDeniedPage() {
        return "accessdenied";
    }

}
