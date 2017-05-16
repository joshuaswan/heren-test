package com.heren.his.readHtml;


import com.heren.his.data.ReadHtmlBySelenium;
import org.hibernate.annotations.RowId;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by joshua on 2016/5/16.
 */
public class ParseHTML {

    @Test
    public void testSelenium() throws Exception {
        ReadHtmlBySelenium readHtmlBySelenium = new ReadHtmlBySelenium();

        readHtmlBySelenium.testCase();
    }

    String htmlFile = "E:\\workspace\\test-sourcecode\\heren-test\\src\\main\\resources\\htmlFile\\DH2011-SRS01-28160-病案流通系统测试用例.html";
    @Test
    public void test() throws IOException, XPathExpressionException {
        File in = new File(htmlFile);
//        Document document = Jsoup.parse(in,"UTF-8");
//        XPath xPath = XPathFactory.newInstance().newXPath();
//        NodeList nodeList = (NodeList) xPath.evaluate("/html/body",document.getAllElements(), XPathConstants.NODESET);
//
//        for (int i=0;i<nodeList.getLength();i++){
//            Element element = (Element) nodeList.item(i);
//        }
//        Elements elements = document.getElementsByTag("span").attr("class","s3");
//        System.out.println(elements.toString());
    }

    @Test
    public void xPathTest() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        org.w3c.dom.Document document = documentBuilder.parse(new FileInputStream("E:\\workspace\\test-sourcecode\\heren-test\\src\\main\\webapp\\page.html"));
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList) xPath.evaluate("/html/body/p/div[3]/a",document.getDocumentElement(), XPathConstants.NODESET);

        for (int i=0;i<nodeList.getLength();i++){
            Element element = (Element) nodeList.item(i);
            System.out.println(element.toString());
        }
    }

    @Test
    public void xpathtest2()throws Exception{
        int responseCode = 0;
        String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=9%20Edinburgh%20Place,%20Centrall&sensor=false&components=country:HK&language=en";

        URL url = new URL(api);

        HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
        httpConnection.connect();
        responseCode = httpConnection.getResponseCode();
        if(responseCode == 200) {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
            Document document = builder.parse(httpConnection.getInputStream());
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            XPathExpression expr = xpath.compile("/GeocodeResponse/status");
            String status = (String)expr.evaluate(document, XPathConstants.STRING);
            if(status.equals("OK")) {
                expr = xpath.compile("//*[@id=\"collapsible6\"]/div[1]/div[2]/div[1]/span[2]");
                Object results = expr.evaluate(document, XPathConstants.NODESET);
                NodeList nodes = (NodeList) results;
                System.out.println(nodes.getLength());

                for (int i = 0; i < nodes.getLength(); i++){
                    System.out.println("latitude: " + nodes.item(i).getNodeValue());
                }

                expr = xpath.compile("//geometry/location/lng");
                String lng = (String)expr.evaluate(document, XPathConstants.STRING);
                System.out.println("longitude: " + lng);
            } else
                throw new Exception("Error from the API - response status: "+status);
        }
    }
}
