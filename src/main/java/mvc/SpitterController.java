package mvc;

import model.Comment;
import model.Spitter;
import model.Spittle;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.SpitterService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller("spitterController")
@RequestMapping("/spitters")
public class SpitterController {

    @Resource
    private SpitterService spitterService;

    /**
     * @Description 获取用户发表的所有推文和评论
     * @Date: 下午3:51 17-8-14
     */
    @RequestMapping(value = {"/{username}/spittles"}, method = RequestMethod.GET)
    public String listSpittlesForSpitter(@PathVariable String username, Map<String, Object> model, Model models) {
        Spitter spitter = spitterService.getSpitter(username);
        System.out.println("portrait" + spitter.getUserportrait());
        Spittle newspittle = new Spittle();
        models.addAttribute(newspittle);
        model.put("modelsp", spitter);
        List<Spittle> spittles = spitterService.getSpittlesForSpitter(spitter);
        model.put("modelsl", spittles);
        //添加所有推文涉及的所有评论
        List<Integer> spittlesid = new ArrayList<Integer>();
        for (Spittle x : spittles) {
             spittlesid.add(x.getId());
        }
        List<Comment> comments = new ArrayList<Comment>();
        if (!spittlesid.isEmpty()) {
            comments = spitterService.getAllComments(spittlesid);
        }
        model.put("modelcom", comments);
        return "spitters/spittles";
    }

    /**
     * @Description  修改用户信息
     * @Date: 下午3:53 17-8-14
     */
    @RequestMapping(value = "/{username}/edit", method = RequestMethod.GET)
    public String editSpitterProfie(@PathVariable String username, Model model) {
        Spitter spitter = spitterService.getSpitter(username);
        model.addAttribute(spitter);
        return "spitters/edit";
    }

    /**
     * @Description  将更新的信息保存
     * @Date: 上午10:21 17-8-16
     */
    @RequestMapping(value = "/{username}/update", method = RequestMethod.POST)
    public String updateSpitterInformation(@Validated Spitter spitter,
                                           BindingResult bindingResult,
                                           @RequestParam(value = "image", required = false)
                                           MultipartFile image,
                                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "redirect:/spitters/" + spitter.getUsername() + "/edit";
        }
        try {
            if (!image.isEmpty()) {
                validateImage(image);
                saveImage(image.getOriginalFilename(), image, request);
            }
        } catch (Exception e) {
            bindingResult.reject(e.getMessage());
            return "redirect:/spitters/" + spitter.getUsername() + "/edit";
        }
        spitter.setUserportrait(image.getOriginalFilename());
        spitterService.updateSpitter(spitter);
        return "redirect:/spitters/" + spitter.getUsername() + "/spittles";
    }

    /**
     * @Description 展示用户的所有公文包
     * @Date: 下午4:11 17-8-14
     */
    @RequestMapping(value = "/{username}/profiles", method = RequestMethod.GET)
    public String showSpitterProfile(@PathVariable String username, Map<String, Object> model) {
        Spitter spitter = spitterService.getSpitter(username);
        model.put("modelsp", spitter);
        return "spitters/profiles";
    }

    /**
     * @Description 展示用户的所有画作
     * @Date: 下午4:10 17-8-14
     */
    @RequestMapping(value = "/{username}/arts", method = RequestMethod.GET)
    public String showSpitterArts(@PathVariable String username, Map<String, Object> model) {
        Spitter spitter = spitterService.getSpitter(username);
        model.put("modelsp", spitter);
        return "spitters/arts";
    }

    /**
     * @Description 创建新的公文包
     * @Date: 下午4:11 17-8-14
     */
    @RequestMapping(value = "/{username}/addProfiles}", method = RequestMethod.GET)
    public String createSpitterProfile(Model model) {
        Spitter spitter = new Spitter();
        model.addAttribute(spitter);
        return "spitters/edit";
    }

    /**
     * @Description 保存注册的用户
     * @Date: 下午4:05 17-8-14
     */
    @RequestMapping(value = "/adds", method = RequestMethod.POST)
    public String addSpitterFromForm(@Validated Spitter spitter,
                                     BindingResult bindingResult,
                                     @RequestParam(value = "image", required = false)
                                     MultipartFile image,
                                     HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            if (!image.isEmpty()) {
                validateImage(image);
                saveImage(image.getOriginalFilename(), image, request);
            }
        } catch (Exception e) {
            bindingResult.reject(e.getMessage());
            return "register";
        }
        spitter.setUserportrait(image.getOriginalFilename());
        spitterService.saveSpitter(spitter);
        System.out.println("redirect:/spitters/" + spitter.getUsername() + "/spittles");
        return "redirect:/spitters/" + spitter.getUsername() + "/spittles";
    }

    /**
     * @Description 验证上传的图片格式
     * @Date: 下午4:00 17-8-14
     */
    private void validateImage(MultipartFile image) throws Exception {
        if (!image.getContentType().equals("image/jpeg")) {
            throw new ImageUploadException("Only JPG images accepted");
        }
    }

    /**
     * @Description 保存用户上传的图像
     * @Date: 下午3:58 17-8-14
     */
    private void saveImage(String filename, MultipartFile image, HttpServletRequest request) throws ImageUploadException {
        try {
            String path = "/resources/images";
            String realPath = request.getSession().getServletContext().getRealPath(path);
            System.out.println("realPath=" + realPath);
            File file = new File(realPath + "/" + image.getOriginalFilename());
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            throw new ImageUploadException("Unable to save iamge", e);
        }
    }




}