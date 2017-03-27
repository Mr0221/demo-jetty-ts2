package filter_model;

public class MsgProcessor {
     private final String msg;
     /*private final Filter[] filters = {new HtmlFilter(),new SensitiveFilter()};*/
     private FilterChain chain = new FilterChain();
     /*public MsgProcessor(final String msg){
         this.msg = msg;
     }*/
     public MsgProcessor(final String msg,final Filter Chain){
         this.msg = msg;
         this.chain = chain;
     }

     /*public String process(){
         String r = msg;
         for(final Filter f : filters){
             r = f.doFilter(r);
         }
         return r;
     }*/
     public String process(){
         return chain.doFilter(msg);
     }


      /*  public String process(){
            String r = msg;
            //过滤msg中的HTML标记
            r = r.replace("<", "&lt;").replace(">", "&gt;");
            //过滤敏感词
            r = r.replace("敏感", "").replace("被就业", "就业");

            return r;
        }*/
}
