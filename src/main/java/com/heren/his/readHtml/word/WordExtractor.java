package com.heren.his.readHtml.word;

import com.heren.his.data.POIDataSamples;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by joshua on 2016/5/1.
 */
public class WordExtractor {
    public String wordFile = "E:\\workspace\\test-sourcecode\\heren-test\\src\\main\\resources\\dataFile\\createdocument_2.docx";
    private String[] paragraphText;

    @Test
    public void createWordFile() throws Exception {


        //Blank Document
        XWPFDocument document = new XWPFDocument();
        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(
                new File(wordFile));
        document.write(out);
        out.close();
        System.out.println(
                "createdocument.docx written successully");

    }

    @Test
    public void CreateParagraph() throws Exception {
        //Blank Document
        XWPFDocument document = new XWPFDocument();
        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(
                new File(wordFile));

        //create Paragraph
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("At tutorialspoint.com, we strive hard to " +
                "provide quality tutorials for self-learning " +
                "purpose in the domains of Academics, Information " +
                "Technology, Management and Computer Programming Languages.");
        document.write(out);
        out.close();
        System.out.println("createparagraph.docx written successfully");
    }

    @Test
    public void readWordFile() throws Exception {

        OPCPackage opcPackage = OPCPackage.create(wordFile);

        XWPFDocument docx = new XWPFDocument(
                opcPackage);
        //using XWPFWordExtractor Class
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        System.out.println(we.getText());

    }

    @Test
    public void testSimple() throws IOException {
//        DocumentException documentException = ;
    }

    @Test
    public void testWithEmbed() throws Exception {
        POIFSFileSystem fileSystem = null;

        HSSFWorkbook workbookA = null, workbookB = null;
        ExcelExtractor excelExtractorA = null, excelExtractorB = null;

        fileSystem = new POIFSFileSystem(POIDataSamples.getDocumentInstance().getFile(wordFile));

        String output = fileSystem.toString();
        System.out.println(output);
    }


    public String[] getParagraphText() {
        return paragraphText;
    }
}
