package mvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import model.Comment;
import model.Spitter;
import model.Spittle;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.multipart.MultipartFile;
import service.SpitterService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller("homeController")
public class HomeController {
    public static final int DEFAULT_SPITTLES_PER_RESULT = 25;

    @Resource
    private SpitterService spitterServiceImpl;

    /**
     * @Description 供评论测试使用
     * @Date: 下午4:23 17-8-25
     */
    @RequestMapping(value = "/uploadComment", method = RequestMethod.POST)
    @ResponseBody
    public String addComment(@RequestParam(required = false) String spittleId, String spitterId, String comment, HttpServletRequest req) {
        int a = Integer.parseInt(spittleId);
        int b = Integer.parseInt(spitterId);
        Spitter spitter = spitterServiceImpl.getSpitter(b);
        Comment acomment = new Comment();
        acomment.setSpittleid(a);
        acomment.setName(spitter.getUsername());
        acomment.setComment(comment);
        if (!comment.equals("")) {
            spitterServiceImpl.addComment(acomment);
        }
        List<Comment> comments = spitterServiceImpl.finCommentBySpittleId(a);
        Gson gson = new Gson();
        return gson.toJson(comments);
    }

    /**
     * @Description  供进度条使用
     * @Date: 下午2:20 17-8-22
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String addProgress(@RequestParam(required = false) MultipartFile image,
                              HttpServletRequest request)
            throws IOException {
        String path = "/resources/images";
        String realPath = request.getSession().getServletContext().getRealPath(path);
        System.out.println("realPath=" + realPath);
        File file = new File(realPath + "/" + image.getOriginalFilename());
        FileUtils.writeByteArrayToFile(file, image.getBytes());
        return image.getOriginalFilename();
    }

    @RequestMapping("/admin")
    public String showAdminPage() {
        return "admin";
    }

    @RequestMapping("/accessdenied")
    public String showAccessDeniedPage() {
        return "accessdenied";
    }

    @RequestMapping({"/", "/home"})
    public String showHomePage(Map<String, Object> model) {
        List<Spittle> s = spitterServiceImpl.getRecentSpittles(DEFAULT_SPITTLES_PER_RESULT);
        for (Spittle x : s) {
            System.out.println(x.getSpitter().getUsername());
            System.out.println(x.getSpitter().getUserportrait());
        }
        model.put("modelsl", s);
        return "home";
    }

    @RequestMapping(value = "/spitters/home/{username}", method = RequestMethod.GET)
    public String showHomePageWithSign(@PathVariable String username, Map<String, Object> model, Model models) {
        Spitter spitter = spitterServiceImpl.getSpitter(username);
        models.addAttribute(spitter);
        List<Spittle> s = spitterServiceImpl.getRecentSpittles(DEFAULT_SPITTLES_PER_RESULT);
        for (Spittle x : s) {
            System.out.println(x.getSpitter().getUsername());
            System.out.println(x.getSpitter().getUserportrait());
        }
        model.put("modelsl", s);
        return "homewithsign";
    }

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping("/404")
    public String showPageNotFound() {
        return "404";
    }

    @RequestMapping("/register")
    public String showRegisterPage(Model model) {
        Spitter spitter = new Spitter();
        model.addAttribute(spitter);
        return "register";
    }






}
