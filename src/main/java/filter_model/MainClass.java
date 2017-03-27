package filter_model;

public class MainClass {
    public static void main(final String[] args) {
        /*//��Ҫ�����˵����
        final String msg = "����ҵ�ˣ�)��������Ϣ��<script>";

        //ʵ����������
        final MsgProcessor mp = new MsgProcessor(msg);
        final String r = mp.process();

        System.out.println(r);*/

         //��Ҫ�����˵����
        final String msg = "����ҵ�ˣ�����������Ϣ��<script>";

        //��һ����������
        final FilterChain chain = new FilterChain();
        chain.addFilter(new HtmlFilter()).addFilter(new SensitiveFilter());
        //ʵ����������
        final MsgProcessor mp = new MsgProcessor(msg,chain);
        final String r = mp.process();

        System.out.println(r);
    }
}
