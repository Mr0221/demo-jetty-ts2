package filter_model;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {

    public List<Filter> filters= new ArrayList<Filter>();

    public FilterChain addFilter(final Filter f){
        filters.add(f);
        return this;
    }

    public String doFilter(final String msg) {//ִ��filters�е�doFilter��������
        String r = msg;
        for(final Filter f : filters){
            r = f.doFilter(r);
        }
        return r;
    }
}

