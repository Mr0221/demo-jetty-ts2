package filter_model;

public class SensitiveFilter implements Filter {
    public String doFilter(final String msg) {
        String r = msg;
        //�������д�
        r = r.replace("����", "").replace("����ҵ", "��ҵ");

        return r;
    }
}
