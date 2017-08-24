package mvc;

import model.Progress;
import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Author: haojie
 * @Description: 进度条监听器
 * @Date: Created in 上午10:58 17-8-21
 */
@Component
public class FileUploadProgressListener implements ProgressListener {
    private HttpSession session;
    public void setSession(HttpSession session) {
        this.session = session;
        Progress status = new Progress();
        session.setAttribute("status", status);
    }
    public void update(long byteRead, long contentLength, int items) {
        Progress status = (Progress) session.getAttribute("status");
        status.setBytesRead(byteRead);
        status.setContentLength(contentLength);
        status.setItems(items);
    }
}
