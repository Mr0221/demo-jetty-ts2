package com.Crawler.pro;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ���湤����
 * @author Eddy
 *
 */
public class SpiderUtil {
    static public Log logger = LogFactory.getLog(SpiderUtil.class);
    public static List<ImageElement> getImageList(final String category, final String page){
        logger.debug("url : http://ww.dbmeizi.com/category/"+category);
        logger.debug("p: " + page);
        //����ͼƬ����
        final List<ImageElement> images = new ArrayList<ImageElement>();
        try{
            //�����ƶ���վ��url
            final Document doc = Jsoup.connect("http://ww.dbmeizi.com/category/"+category).data("p", page).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").timeout(3000).get();
            final Elements eImages = doc.getElementsByTag("img");
            ImageElement image = null;
            for(final Element eImage : eImages){
                image = new ImageElement();
                final String surl = eImage.attr("data-src");
                final String title = eImage.attr("data-title");
                final String burl = eImage.attr("data-bigimg");
                //url ��Ϊ�� ��ЧͼƬ
                if(surl !=null && !"".equals(surl)){
                    image.setSurl(surl);
                    image.setBurl(burl);
                    image.setTitle(title);
                    images.add(image);
                }
            }
        }catch(final Exception e){
            e.printStackTrace();
        }
        //���ؼ���
        return images;
    }

    public static List<ImageElement> getImageList_me( final String page){
        //����ͼƬ����
        final List<ImageElement> images = new ArrayList<ImageElement>();
        try{
            //�����ƶ���վ��url
            final Document doc = Jsoup.connect("http://www.mzitu.com/taiwan/page/"+page).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").timeout(6000).get();
            logger.debug("url : http://www.mzitu.com/taiwan/page/"+page);
            final Elements eImages = doc.getElementsByTag("img");
            ImageElement image = null;
            for(final Element eImage : eImages){
                image = new ImageElement();
                final String surl = eImage.attr("src");
                final String title = eImage.attr("alt");
                final String burl = eImage.attr("data-original");
                //url ��Ϊ�� ��ЧͼƬ
                if(surl !=null && !"".equals(surl)){
                    logger.debug(image.getTitle());
                    image.setSurl(surl);
                    image.setBurl(burl);
                    image.setTitle(title);
                    images.add(image);
                }
            }
        }catch(final Exception e){
            e.printStackTrace();
        }
        //���ؼ���
        return images;
    }
    public static void main(final String[] args) {
        System.out.println(getImageList_me("1"));
    }
}
