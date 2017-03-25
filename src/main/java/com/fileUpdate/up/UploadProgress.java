package com.fileUpdate.up;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;

/**
 * 文件上传进度监听器
 * @author Administrator
 *
 */
public class UploadProgress implements ProgressListener{
	private HttpSession session;
	/**
	 * arg0 :d当前已经上传的大小
	 * arg1  :总共大小
	 * arg2 当前正在上传第几个文件
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
