package mvc;

import model.Spittle;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.SpitterService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: haojie
 * @Description:
 * @Date: Created in 下午2:13 17-8-15
 */
@Controller
public class SpittleController {

    @Resource
    private SpitterService spitterService;

    /**
     * @Description  删除发布的推文
     * @Date: 下午2:16 17-8-15
     */
    @RequestMapping("/spittles/{spittleid}")
    public String deleteSpittle(@PathVariable Integer spittleid) {
        String username = spitterService.findSpitterBySpittleid(spittleid);
        spitterService.deleteSpittle(spittleid);
        return "redirect:/spitters/" + username + "/spittles";
    }

    /**
     * @Description  保存发布的推文
     * @Date: 下午5:06 17-8-16
     */
    @RequestMapping(value = "/spittles/adds", method = RequestMethod.POST)
    public String addSpittle(@Validated Spittle spittle) {
        spitterService.saveSpittle(spittle);
        return "redirect:/spitters/" + spittle.getSpitter().getUsername() + "/spittles";
    }
}
