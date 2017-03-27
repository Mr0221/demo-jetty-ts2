package freemarker;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Demo {

    public static void main(final String[] args) {
        final Configuration  cfg = new Configuration ();
        try {
            cfg.setDefaultEncoding("UTF-8");
            cfg.setDirectoryForTemplateLoading(new File("templates"));
            final Template template = cfg.getTemplate("a.tld");
            final Map date = new HashMap();
            date.put("name", "ad");
            List week = new ArrayList();
            final String[] weeks = {"Monday", "Thuesday", "Wensday", "Thourday", "Friday", "Satursday", "Sunday" };
            week = Arrays.asList(weeks);
            date.put("week", week);
            final Writer out = new PrintWriter(new File("a.html"));
            template.process(date, out);
            out.flush();
            out.close();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final TemplateException e) {
            e.printStackTrace();
        }
    }

}
