package thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class Myreject implements RejectedExecutionHandler{
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("�Զ��崦��..");
		System.out.println("��ǰ���ܾ�����Ϊ�� " + r.toString());
	}
}

