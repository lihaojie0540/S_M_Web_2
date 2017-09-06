package mvc;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Controller;
import service.SpitterService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller("loginSuccessHandler")
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Resource
    private SpitterService spitterService;
    private Map<String, Object> authDispaatcherMap = null;
    private RequestCache requestCache = new HttpSessionRequestCache();

    public Map<String, Object> getAuthDispaatcherMap() {
        return authDispaatcherMap;
    }

    public void setAuthDispaatcherMap(Map<String, Object> authDispaatcherMap) {
        this.authDispaatcherMap = authDispaatcherMap;
    }

    public RequestCache getRequestCache() {
        return requestCache;
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }

    //根据用户权限不同跳转到不同页面
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse rsp, Authentication aut) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) aut.getPrincipal();
        String path = req.getContextPath();
        String basepath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
        System.out.println((basepath + userDetails.getUsername()));
        System.out.println(userDetails.getAuthorities());
        System.out.println("onAuthenticationSuccess");
        rsp.sendRedirect(basepath + "spitters/loginin/" + userDetails.getUsername());
    }
}
