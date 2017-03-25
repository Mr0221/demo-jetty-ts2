package com.Crawler.pro;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SpiderTest {

	public static void main(String[] args) {
		try {
			Document doc = Jsoup.connect("http://www.mzitu.com/page/2").timeout(10000).get();
			//�ӷ��ص������л�ȡimg��ǩ���б�
			Elements imgs = doc.getElementsByTag("img");
			for(Element img: imgs){
				//Attribute;
				System.out.println( img.attr("src") );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
