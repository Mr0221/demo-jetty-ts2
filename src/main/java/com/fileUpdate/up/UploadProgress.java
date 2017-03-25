package com.fileUpdate.up;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

/**
 * �ļ��ϴ����ȼ�����
 * @author Administrator
 *
 */
public class UploadProgress implements ProgressListener{
	private HttpSession session;
	/**
	 * arg0 :d��ǰ�Ѿ��ϴ��Ĵ�С
	 * arg1  :�ܹ���С
	 * arg2 ��ǰ�����ϴ��ڼ����ļ�
	 */
	public void update(long arg0, long arg1, int arg2) {
		System.out.println("curent: " + arg0 );
		if(arg1 == 0){
			return;
		}
		String value = "{read:}"+arg0+", total: "+arg1+"item:"+arg2+"}";
		session.setAttribute("progress", value);
	}
	public UploadProgress(HttpSession ses){
		this.session = ses;
	}
}
