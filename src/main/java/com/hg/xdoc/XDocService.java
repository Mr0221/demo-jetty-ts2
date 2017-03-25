package com.hg.xdoc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/** * XDoc服务 * @author xdoc */
public class XDocService {
    public static String DEFAULT_URL = "https://www.baidu.com";
    public static String DEFAULT_KEY = "";
    private String url;
    private String key;
    /** * 服务地址 * @return */
    public String getUrl() {
        return url;
    }
    /** * 服务地址 * @param url */
    public void setUrl(final String url) {
        this.url = url;
    }
    /** * 账号口令 * @return */
    public String getKey() {
        return key;
    }
    /** * 账号口令 * @param key */
    public void setKey(final String key) {
        this.key = key;
    }
    /** * 构造器 */
    public XDocService() {
        this(DEFAULT_URL, DEFAULT_KEY);
    }
    /** * 构造器 * @param url 服务地址 */
    public XDocService(final String url) {
        this(url, DEFAULT_KEY);
    }
    /** * 构造器 * @param url 服务地址 * @param key 账号 */
    public XDocService(final String url, final String key) {
        this.url = url + (url.endsWith("/") ? "xdoc" : "/xdoc");
        this.key = key;
    }
    /**
     * * 转换为其它格式文件 * @param xdoc xdoc文本<br>
     * * URL：文档URL地址，格式支持：xdoc、json、docx、epub、txt、rtf等，支持datauri协议，可传递二进制数据<br>
     * * 纯文本：以"text:"开头的文本<br>
     * * JSON：符合XDOC-JSON规范的JSON文本<br>
     * * XML：符合XDOC-XML规范的XML文本<br>
     * * HTML：用html标签括起来的html文本，如：&lt;html&gt;&lt;h1&gt;Hello&lt;/h1&gt;&lt;/
     * html&gt; * @param file 其它格式文件，如：a.pdf * @throws IOException
     */
    public void to(final String xdoc, final File file) throws IOException {
        final FileOutputStream fout = new FileOutputStream(file);
        to(xdoc, fout, getFormat(file.getName()));
        fout.flush();
        fout.close();
    }
    /**
     * * 转换为其它格式，保存到指定流中 * @param xdoc xdoc * @param out out * @param format
     * format * @throws IOException
     */
    public void to(final String xdoc, final OutputStream out, final String format)
            throws IOException {
        final Map param = new HashMap();
        param.put("_func", "to");
        param.put("_xdoc", xdoc);
        param.put("_format", format);
        invoke(param, out);
    }
    /**
     * * 运行xdoc * @param xdoc xdoc * @param param 参数 * @param file 目标文件 * @throws
     * IOException
     */
    public void run(final String xdoc, final Map param, final File file) throws IOException {
        final FileOutputStream fout = new FileOutputStream(file);
        run(xdoc, param, fout, getFormat(file.getName()));
        fout.flush();
        fout.close();
    }
    /**
     * * 运行xdoc * @param xdoc xdoc * @param param 参数 * @param out 目标流 * @param
     * format 目标格式 * @throws IOException
     */
    public void run(final String xdoc, final Map param, final OutputStream out, final String format)
            throws IOException {
        param.put("_func", "run");
        param.put("_xdoc", xdoc);
        param.put("_format", format);
        invoke(param, out);
    }
    private void invoke(final Map param, final OutputStream out) throws IOException {
        final HttpURLConnection httpConn = (HttpURLConnection) new URL(this.url)
                .openConnection();
        httpConn.setDoOutput(true);
        final OutputStream reqOut = httpConn.getOutputStream();
        reqOut.write(("&_key=").getBytes());
        reqOut.write(encode(this.key).getBytes());
        final Iterator it = param.keySet().iterator();
        String key;
        while (it.hasNext()) {
            key = (String) it.next();
            reqOut.write(("&" + encode(key) + "=").getBytes());
            reqOut.write(encode(param.get(key)).getBytes());
        }
        reqOut.flush();
        reqOut.close();
        pipe(httpConn.getInputStream(), out);
    }
    private static String getFormat(String url) {
        String format = "xdoc";
        int pos = url.lastIndexOf(".");
        if (pos > 0) {
            format = url.substring(pos + 1).toLowerCase();
            if (format.equals("zip")) {
                url = url.substring(0, pos);
                pos = url.lastIndexOf(".");
                if (pos > 0) {
                    format = url.substring(pos + 1).toLowerCase() + ".zip";
                }
            }
        }
        return format;
    }
    private static String encode(final Object str) {
        try {
            return URLEncoder.encode(String.valueOf(str), "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            return String.valueOf(str);
        }
    }
    private static void pipe(final InputStream in, final OutputStream out)
            throws IOException {
        int len;
        final byte[] buf = new byte[4096];
        while (true) {
            len = in.read(buf);
            if (len > 0) {
                out.write(buf, 0, len);
            } else {
                break;
            }
        }
        out.flush();
    }
    /** * 主程序 * @param args */
    public static void main(final String[] args) {
        try {
            new XDocService().to("<html><h1>Hello中国</h1></html>", new File(
                    "d:/a.pdf"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
