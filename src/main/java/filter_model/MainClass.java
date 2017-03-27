package filter_model;

public class MainClass {
    public static void main(final String[] args) {
        /*//需要被过滤的语句
        final String msg = "被就业了：)，敏感信息，<script>";

        //实例化处理类
        final MsgProcessor mp = new MsgProcessor(msg);
        final String r = mp.process();

        System.out.println(r);*/

         //需要被过滤的语句
        final String msg = "被就业了：），敏感信息，<script>";

        //搞一个过过滤链
        final FilterChain chain = new FilterChain();
        chain.addFilter(new HtmlFilter()).addFilter(new SensitiveFilter());
        //实例化处理类
        final MsgProcessor mp = new MsgProcessor(msg,chain);
        final String r = mp.process();

        System.out.println(r);
    }
}
