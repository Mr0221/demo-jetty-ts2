package filter_model;

public class HtmlFilter implements Filter {
    public String doFilter(final String msg) {
        String r = msg;
        //����msg�е�HTML���
        r = r.replace("<", "&lt;").replace(">", "&gt;");

        return r;
    }
}

