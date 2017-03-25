package com.listWeb.pro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ListHtml extends HttpServlet{
    Log logger = LogFactory.getLog(getClass());
    final String FILE_POSTFIX_HTML = ".html";
    final String FILE_POSTFIX_JSP = ".jsp";
    final String FILE_POSTFIX_ACTION= ".action";
    final String PRO_NAME = "web-clone";
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
         final String uploadFilePath = this.getServletContext().getRealPath("&&");
         final String path = uploadFilePath.substring(0, uploadFilePath.indexOf("&&"));
         logger.debug("File path: " + path);
         final Map<String,String> htmlMap = new HashMap<String,String>();
         final Map<String,String> actionMap = new HashMap<String,String>();
         final Map<String,String> jspMap = new HashMap<String,String>();
         listfile(new File(path),htmlMap, jspMap);//File既可以代表一个文件也可以代表一个目录
         loadActionCofig(request, actionMap);
         request.setAttribute("htmlMap", htmlMap);
         request.setAttribute("jspMap", jspMap);
         request.setAttribute("actionMap", actionMap);
         request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void loadActionCofig(final HttpServletRequest request, final Map<String,String> map){
        logger.debug("loading action.properties..");
        final Properties prop = new Properties();
        ///加载属性列表
        /*final InputStream in = ClassLoader.getSystemResourceAsStream("action.properties");*/
      /*  final String path = request.getContextPath();
        final String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";*/
        final String cofigPath =this.getServletContext().getRealPath("\\")   + "\\action.properties";
        this.getServletContext().getContextPath();
        /*getServletConfig().getServletContext().getResourceAsStream("report1.jasper");*/
        logger.debug("config path: "+ cofigPath);
        try {
            final InputStream in = new FileInputStream(new File(cofigPath));
            prop.load(in);
            final Iterator<String> it=prop.stringPropertyNames().iterator();
              while(it.hasNext()){
                  final String key=it.next();
                  logger.debug(key+" : "+prop.getProperty(key));
                  map.put(key + FILE_POSTFIX_ACTION, prop.getProperty(key));
              }
              in.close();

        } catch (final IOException e) {
            logger.error(e);
            e.printStackTrace();
        }

    }
    public void listfile(final File file,final Map<String,String> hmap, final Map<String,String> jmap){
        if(!file.isFile()){
            final File files[] = file.listFiles();
            for(final File f : files){
                listfile(f,hmap,jmap);
            }
        }else{
            if( file.getName().endsWith(".html") || file.getName().endsWith(".jsp")){
                final String filePath = file.getAbsolutePath();
                logger.debug("filePath:" + filePath);
                final String address = filePath.substring(filePath.indexOf("web")+3);
                final String realPath1 = address.substring(address.indexOf("web")+4);//"加上 \"
                final String RealPath = realPath1.replace('\\', '/');
                logger.debug("address:" + RealPath);
                if(file.getName().endsWith(".html")){
                	if(file.getName().equalsIgnoreCase("index.html")){
                		hmap.put(RealPath, RealPath);
                	}else{
                		hmap.put(file.getName(), RealPath);
                	}
                }else{
                	if(file.getName().equalsIgnoreCase("index.jsp")){
                		jmap.put(RealPath, RealPath);
                	}else{
                		jmap.put(file.getName(), RealPath);
                	}
                }

            }

        }
    }
}
