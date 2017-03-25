package com.freemaker.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FreemakerServlet
 */
public class FreemakerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public FreemakerServlet() {
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

    }

    /* 生成友情链接的静态页cuxiao.html
    *
    * @param context
    * @param data
    */
    public static void createIndexFriendLink(final ServletContext context,
        final Map<String, Object> data) {
    try {
    //cuxiao.ftl是项目中建立的ftl文件，cuxiao.html是生成的静态页面名
      /*  return crateHTML(context, data, "/cuxiao.ftl", "cuxiao.html");*/
    } catch (final Exception e) {
        e.printStackTrace();
    }
    /**
     * 生成静态页面主方法
     *
     * @param context
     *            ServletContext
     * @param data
     *            一个Map的数据结果集
     * @param templatePath
     *            ftl模版路径
     * @param targetHtmlPath
     *            生成静态页面的路径
     */
   /* public static void crateHTML(final ServletContext context,
            final Map<String, Object> data, final String templatePath, final String targetHtmlPath) {
        // 加载模版
        freemarkerCfg.setServletContextForTemplateLoading(context, "/");
        freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
        final String filePath = ServletActionContext.getServletContext().getRealPath(
                "/static");
        final File file = new File(filePath);
        if(!file.exists() || !file.isDirectory()){
            file.mkdir();
        }
        final File f = new File(file,"/all_css");
        if(!f.exists() || !f.isDirectory()){
            f.mkdir();
        }
        try {
            freemarkerCfg.setDirectoryForTemplateLoading(new File(filePath));
            // 设置包装器，并将对象包装为数据模型
            freemarkerCfg.setObjectWrapper(new DefaultObjectWrapper());
            // 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
            // 否则会出现乱码
            final Template template = freemarkerCfg
                    .getTemplate(templatePath, "UTF-8");
            template.setEncoding("UTF-8");
            // 静态页面路径
            final String htmlPath = filePath + "/" + targetHtmlPath;
            final File htmlFile = new File(htmlPath);
            final Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(htmlFile), "UTF-8"));
            // 处理模版
            template.process(data, out);
            out.flush();
            out.close();

        } catch (final Exception e) {
            e.printStackTrace();
        }
*/
    }
}
