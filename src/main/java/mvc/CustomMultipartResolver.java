package mvc;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: haojie
 * @Description: 多类型处理
 * @Date: Created in 下午1:37 17-8-21
 */
@Component
public class CustomMultipartResolver extends CommonsMultipartResolver {
    @Autowired
    private FileUploadProgressListener progressListener;

    private void fileUploadProgressListener(FileUploadProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    public MultipartParsingResult pareseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);
        progressListener.setSession(request.getSession());
        fileUpload.setProgressListener(progressListener);
        try {
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
                    return parseFileItems(fileItems, encoding);
        } catch (FileUploadBase.FileSizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(fileUpload.getFileSizeMax(), ex);
        } catch (FileUploadException ex) {
            throw new MultipartException("Could not parse multipart request", ex);
        }
    }
}