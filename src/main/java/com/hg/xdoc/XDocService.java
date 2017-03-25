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
/** * XDoc���� * @author xdoc */
public class XDocService {
    public static String DEFAULT_URL = "https://www.baidu.com";
    public static String DEFAULT_KEY = "";
    private String url;
    private String key;
    /** * �����ַ * @return */
    public String getUrl() {
        return url;
    }
    /** * �����ַ * @param url */
    public void setUrl(final String url) {
        this.url = url;
    }
    /** * �˺ſ��� * @return */
    public String getKey() {
        return key;
    }
    /** * �˺ſ��� * @param key */
    public void setKey(final String key) {
        this.key = key;
    }
    /** * ������ */
    public XDocService() {
        this(DEFAULT_URL, DEFAULT_KEY);
    }
    /** * ������ * @param url �����ַ */
    public XDocService(final String url) {
        this(url, DEFAULT_KEY);
    }
    /** * ������ * @param url �����ַ * @param key �˺� */
    public XDocService(final String url, final String key) {
        this.url = url + (url.endsWith("/") ? "xdoc" : "/xdoc");
        this.key = key;
    }
    /**
     * * ת��Ϊ������ʽ�ļ� * @param xdoc xdoc�ı�<br>
     * * URL���ĵ�URL��ַ����ʽ֧�֣�xdoc��json��docx��epub��txt��rtf�ȣ�֧��datauriЭ�飬�ɴ��ݶ���������<br>
     * * ���ı�����"text:"��ͷ���ı�<br>
     * * JSON������XDOC-JSON�淶��JSON�ı�<br>
     * * XML������XDOC-XML�淶��XML�ı�<br>
     * * HTML����html��ǩ��������html�ı����磺&lt;html&gt;&lt;h1&gt;Hello&lt;/h1&gt;&lt;/
     * html&gt; * @param file ������ʽ�ļ����磺a.pdf * @throws IOException
     */
    public void to(final String xdoc, final File file) throws IOException {
        final FileOutputStream fout = new FileOutputStream(file);
        to(xdoc, fout, getFormat(file.getName()));
        fout.flush();
        fout.close();
    }
    /**
     * * ת��Ϊ������ʽ�����浽ָ������ * @param xdoc xdoc * @param out out * @param format
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
     * * ����xdoc * @param xdoc xdoc * @param param ���� * @param file Ŀ���ļ� * @throws
     * IOException
     */
    public void run(final String xdoc, final Map param, final File file) throws IOException {
        final FileOutputStream fout = new FileOutputStream(file);
        run(xdoc, param, fout, getFormat(file.getName()));
        fout.flush();
        fout.close();
    }
    /**
     * * ����xdoc * @param xdoc xdoc * @param param ���� * @param out Ŀ���� * @param
     * format Ŀ���ʽ * @throws IOException
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
    /** * ������ * @param args */
    public static void main(final String[] args) {
        try {
            new XDocService().to("<html><h1>Hello�й�</h1></html>", new File(
                    "d:/a.pdf"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
