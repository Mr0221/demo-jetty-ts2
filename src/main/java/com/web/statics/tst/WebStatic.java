package com.web.statics.tst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebStatic {
    private static boolean beReady2Url(final URL url, final String FileName){
        Boolean isReady = false;
        try {
            final URLConnection urlCnnt = url.openConnection();
            final InputStream in = urlCnnt.getInputStream();
            final BufferedReader reader = new BufferedReader(
                    new InputStreamReader(in, "gbk"));
            final StringBuffer strBuf = new StringBuffer();
            final File file = new File("html/statics/"+FileName+".html");
            while (reader.ready()) {
                strBuf.append(reader.readLine() + "\n");
            }
            in.close();
            reader.close();
            final BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file), "gbk"));
            writer.write(strBuf.toString());
            writer.close();
            isReady = true;
            return isReady;
        } catch (final MalformedURLException e) {
            e.printStackTrace();
            return isReady;
        } catch (final IOException e) {
            e.printStackTrace();
            return isReady;
        }
    }

    private static boolean beReady2Url2(final String inPath, final String outPath){
        boolean isReady = false;
        try {
            final FileInputStream in = new FileInputStream (inPath);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(in,"utf-8"));
            String jspContent = "";
            final StringBuffer strBuf = new StringBuffer();
            while((jspContent=reader.readLine())!=null){
                strBuf.append(jspContent);
            }
            reader.close();
            in.close();
            final Map<String, String>values = new HashMap<String, String>();
            values.put("##title##", "Today is nice");
            values.put("##Author##", "ey");
            values.put("##content##", "hahahhahahahah!");
            final Iterator<String> iter = values.keySet().iterator();
            String contentReal = strBuf.toString();
            while(iter.hasNext()){
                final String key = iter.next();
                contentReal = contentReal.replaceAll(key, values.get(key));
            }
            final File f = new File(outPath);
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "utf-8"));
            writer.write(contentReal);
            writer.close();
            isReady = true;
            return isReady;
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
            return isReady;
        } catch (final IOException e) {
            e.printStackTrace();
            return isReady;
        }

    }


    public static void main(final String[] args) {
         try {
			beReady2Url(new URL("http://www.qq.com"), "qq123");
		} catch (MalformedURLException e) {
			System.out.println("error");
			e.printStackTrace();
		}
        /*beReady2Url2("src\\main\\webapp\\webstatic.html", "html/statics/tstModel.html");*/

}
}
