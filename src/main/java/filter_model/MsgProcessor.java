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
            //����msg�е�HTML���
            r = r.replace("<", "&lt;").replace(">", "&gt;");
            //�������д�
            r = r.replace("����", "").replace("����ҵ", "��ҵ");

            return r;
        }*/
}
