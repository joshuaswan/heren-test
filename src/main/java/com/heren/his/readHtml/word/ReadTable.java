package com.heren.his.readHtml.word;

import java.io.*;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.junit.Test;

/**
 * Created by joshua on 2016/5/1.
 */
public class ReadTable {
    public static String text = null;

    @Test
    public void TransForTxt() throws Exception {
        //用了两天时间就写了这么点代码，还tm的不好使，太他娘的难了
        String fileName = "E:\\工作文档\\测试文档\\李洋洋\\评审文档\\04-1期-测试用例\\DH2011-SRS01-28160-病案流通系统测试用例.doc";
        POIFSFileSystem fileSystem = null;
        fileSystem = new POIFSFileSystem(new FileInputStream(fileName));
        HWPFDocument document = new HWPFDocument(fileSystem);

        org.apache.poi.hwpf.extractor.WordExtractor wordExtractor = new org.apache.poi.hwpf.extractor.WordExtractor(document);

        text = wordExtractor.getText();

        String outputFileName = "E:\\工作文档\\测试文档\\李洋洋\\评审文档\\04-1期-测试用例\\DH2011-SRS01-28160-病案流通系统测试用例.txt";
        PrintWriter file = new PrintWriter(outputFileName);
        file.print(text);
        file.close();
    }

    @Test
    public void readTxt() throws Exception {
        String outputFileName = "E:\\工作文档\\测试文档\\李洋洋\\评审文档\\04-1期-测试用例\\DH2011-SRS01-28160-病案流通系统测试用例.txt";
        BufferedReader fileIn = new BufferedReader(new FileReader(outputFileName));
        fileIn.readLine();
    }

    public static String getTitle(String targetTitle) {
        int titleStart = text.indexOf(targetTitle);
        int titleEnd = text.indexOf("\r", titleStart + targetTitle.length() + 1);
        String title = null;
        if (titleStart > 0) {
            title = text.substring(titleStart + targetTitle.length() * 2 - 1, titleEnd);
        } else {
            return null;
        }
        text = text.substring(titleEnd);
        return title;
    }


    public static String readTitle(String text, String targetTitle, int start, int stop) {
        String textResult = text.substring(start, start);

        return textResult;
    }

    public static void readDocFile() throws Exception {
        String fileName = "E:\\工作文档\\测试文档\\李洋洋\\评审文档\\04-1期-测试用例\\DH2011-SRS01-28160-病案流通系统测试用例.doc";

//        Method call to read the document (demonstrate some useage of POI)

        POIFSFileSystem fileSystem = null;
        fileSystem = new POIFSFileSystem(new FileInputStream(fileName));
        HWPFDocument document = new HWPFDocument(fileSystem);

        org.apache.poi.hwpf.extractor.WordExtractor wordExtractor = new org.apache.poi.hwpf.extractor.WordExtractor(document);

        String text = wordExtractor.getText();
        System.out.println(text);
        int result = 0;
        int enter = 0;

        for (int i = 1; i < 6; i++) {
            result = text.indexOf(String.valueOf(i) + ".", result);
            enter = text.indexOf("\n", result);
            String textResult = text.substring(result + 2, enter);
            System.out.print(result);
            System.out.print("  ");
            System.out.println(enter);
            System.out.println(textResult);
        }
    }
}
