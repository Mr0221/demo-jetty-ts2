package com.fileUpdate.down;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadServlet extends HttpServlet {

    @Override
    public void service(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
         //�õ�Ҫ���ص��ļ���
        String fileName = request.getParameter("filename");  //23239283-92489-������.avi
        fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //�ϴ����ļ����Ǳ�����/WEB-INF/uploadĿ¼�µ���Ŀ¼����
        final String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
        //ͨ���ļ����ҳ��ļ�������Ŀ¼
        final String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //�õ�Ҫ���ص��ļ�
        final File file = new File(path + "\\" + fileName);
        //����ļ�������
        if(!file.exists()){
            request.setAttribute("message", "��Ҫ���ص���Դ�ѱ�ɾ������");
            request.getRequestDispatcher("/FileUpAndDown/message.jsp").forward(request, response);
            return;
        }
        //�����ļ���
        final String realname = fileName.substring(fileName.indexOf("_")+1);
        //������Ӧͷ��������������ظ��ļ�
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //��ȡҪ���ص��ļ������浽�ļ�������
        final FileInputStream in = new FileInputStream(path + "\\" + fileName);
        //���������
        final OutputStream out = response.getOutputStream();
        //����������
        final byte buffer[] = new byte[1024];
        int len = 0;
        //ѭ�����������е����ݶ�ȡ������������
        while((len=in.read(buffer))>0){
            //��������������ݵ��������ʵ���ļ�����
            out.write(buffer, 0, len);
        }
        //�ر��ļ�������
        in.close();
        //�ر������
        out.close();
    }

    /**
    * @Method: findFileSavePathByFileName
    * @Description: ͨ���ļ����ʹ洢�ϴ��ļ���Ŀ¼�ҳ�Ҫ���ص��ļ�������·��
    * @Anthor:�°�����
    * @param filename Ҫ���ص��ļ���
    * @param saveRootPath �ϴ��ļ�����ĸ�Ŀ¼��Ҳ����/WEB-INF/uploadĿ¼
    * @return Ҫ���ص��ļ��Ĵ洢Ŀ¼
    */
    public String findFileSavePathByFileName(final String filename,final String saveRootPath){
        final int hashcode = filename.hashCode();
        final int dir1 = hashcode&0xf;  //0--15
        final int dir2 = (hashcode&0xf0)>>4;  //0-15
        final String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        final File file = new File(dir);
        if(!file.exists()){
            //����Ŀ¼
            file.mkdirs();
        }
        return dir;
    }

}
