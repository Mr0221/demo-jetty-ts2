package filter_model;

public class HtmlFilter implements Filter {
    public String doFilter(final String msg) {
        String r = msg;
        //过滤msg中的HTML标记
        r = r.replace("<", "&lt;").replace(">", "&gt;");

        return r;
    }
}

