package mvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import model.Spitter;
import model.Spittle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.SpitterService;

import java.io.IOException;
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


    /**
     * @Description  仅供进度条测试使用
     * @Date: 下午2:20 17-8-22
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String add(String name,
                      @RequestParam(required = false) MultipartFile file,
                      HttpServletRequest request)
                        throws IOException {
        System.out.println("filesize=" + file.getSize());
        System.out.println("filetype=" + file.getContentType());
        System.out.println("filename=" + file.getName());
        System.out.println("fileorigin=" + file.getOriginalFilename());
        return "success";
    }
}
