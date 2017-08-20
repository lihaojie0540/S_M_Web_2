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

    //根据用户权限不同跳转到不同页面
    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse rsp, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(userDetails.getUsername());
        String path = req.getContextPath();
        System.out.println("path:" + path);
        String basepath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/";
        System.out.println((basepath + userDetails.getUsername()));
        System.out.println(userDetails.getAuthorities());
        rsp.sendRedirect(basepath + "spitters/" + userDetails.getUsername() + "/spittles");
    }

    public Map<String, Object> getAuthDispaatcherMap() {
        return authDispaatcherMap;
    }

    public void setAuthDispaatcherMap(Map<String, Object> authDispaatcherMap) {
        this.authDispaatcherMap = authDispaatcherMap;
    }

    public RequestCache getRequestCache() {
        return requestCache;
    }

    @Override
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
