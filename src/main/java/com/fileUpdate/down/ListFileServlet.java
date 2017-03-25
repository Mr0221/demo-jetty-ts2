package com.fileUpdate.down;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class ListFileServlet extends HttpServlet {

    @Override
    public void service(final ServletRequest request, final ServletResponse response) throws ServletException, IOException {
          //��ȡ�ϴ��ļ���Ŀ¼
        final String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //�洢Ҫ���ص��ļ���
        final Map<String,String> fileNameMap = new HashMap<String,String>();
        //�ݹ����filepathĿ¼�µ������ļ���Ŀ¼�����ļ����ļ����洢��map������
        listfile(new File(uploadFilePath),fileNameMap);//File�ȿ��Դ���һ���ļ�Ҳ���Դ���һ��Ŀ¼
        //��Map���Ϸ��͵�listfile.jspҳ�������ʾ
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("/FileUpAndDown/listfile.jsp").forward(request, response);
    }

    /**
    * @Method: listfile
    * @Description: �ݹ����ָ��Ŀ¼�µ������ļ�
    * @Anthor:�°�����
    * @param file ������һ���ļ���Ҳ����һ���ļ�Ŀ¼
    * @param map �洢�ļ�����Map����
    */
    public void listfile(final File file,final Map<String,String> map){
        //���file����Ĳ���һ���ļ�������һ��Ŀ¼
        if(!file.isFile()){
            //�г���Ŀ¼�µ������ļ���Ŀ¼
            final File files[] = file.listFiles();
            //����files[]����
            if(files == null && files.length==0){
            	return;
            }
            for(final File f : files){
                //�ݹ�
                listfile(f,map);
            }
        }else{
            /**
             * �����ļ������ϴ�����ļ�����uuid_�ļ�������ʽȥ���������ģ�ȥ���ļ�����uuid_����
                file.getName().indexOf("_")�����ַ����е�һ�γ���"_"�ַ���λ�ã�����ļ��������ڣ�9349249849-88343-8344_��_��_��.avi
                ��ôfile.getName().substring(file.getName().indexOf("_")+1)����֮��Ϳ��Եõ���_��_��.avi����
             */
            final String realName = file.getName().substring(file.getName().indexOf("_")+1);
            //file.getName()�õ������ļ���ԭʼ���ƣ����������Ψһ�ģ���˿�����Ϊkey��realName�Ǵ����������ƣ��п��ܻ��ظ�
            map.put(file.getName(), realName);
        }
    }

}
