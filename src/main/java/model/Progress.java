package model;

/**
 * @Author: haojie
 * @Description: 供进度条使用
 * @Date: Created in 上午10:54 17-8-21
 */
public class Progress {
    private long bytesRead;
    private long contentLength;
    private long items;

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public long getItems() {
        return items;
    }

    public void setItems(long items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Progress [bytesRead=" + bytesRead + ",contentLength=" + ",items=" + items;
    }
}
