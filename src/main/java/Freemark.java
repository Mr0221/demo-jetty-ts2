import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 使用freemark生成html
 * @author
 *
 */
public class Freemark {
    /**
     * freemark模板配置
     */
    private final Configuration configuration;
    /**
     * freemark模板的名字
     */
    private String templateName;
    /**
     * 生成文件名
     */
    private String fileName;
    /**
     * 生成文件路径
     */
    private String filePath;

    /**
     * freemark初始化
     * @param templatePath 模板文件位置
     */
    public Freemark(final String templatePath) {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(this.getClass(),templatePath);
    }


    public static void main(final String[] args){
        final Freemark freemark = new Freemark("template/");
        freemark.setTemplateName("wordTemplate.ftl");
        freemark.setFileName("doc_"+new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date())+".html");
        freemark.setFilePath("bin\\doc\\");
        freemark.createHTML();
    }

    private void createHTML(){

        Template t = null;
        try {
            t = configuration.getTemplate(templateName);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final File outFile = new File(filePath+fileName);

        System.out.println(outFile.getAbsolutePath());
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        }

        final Map map = new HashMap<String, Object>();
        map.put("user", "xxxooo");
        map.put("url", "https://www.baidu.com");
        map.put("name", "百度");
        try {
            t.process(map, out);
            out.close();
        } catch (final TemplateException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(final String filePath) {
        this.filePath = filePath;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }
}
