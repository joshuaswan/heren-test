package com.heren.his.readHtml.word;


import com.heren.his.data.Converter;
import org.junit.Test;

import java.io.File;

/**
 * Created by joshua on 2016/5/16.
 */
public class DocToHtml {
    public void translate(String fileName,String toFileName)throws Exception{
        File file = new File(fileName);
        Converter converter = new Converter(file);
        File file1 = new File(toFileName);
        converter.convert(file1);
    }

    @Test
    public void test()throws Exception{
        String[] fileNames = {
                "E:\\工作文档\\测试文档\\李森\\评审文档\\04-1期-测试用例\\DH2011-UC01-28030-分诊系统测试用例",
                "E:\\工作文档\\测试文档\\李森\\评审文档\\04-1期-测试用例\\DH2011-UC01-28080-门诊收费测试用例",
                "E:\\工作文档\\测试文档\\李森\\评审文档\\04-1期-测试用例\\DH2011-UC01-28090-住院收费测试用例",
                "E:\\工作文档\\测试文档\\李森\\评审文档\\04-1期-测试用例\\DH2011-UC01-28100-物价管理测试用例",
                "E:\\工作文档\\测试文档\\李森\\评审文档\\04-1期-测试用例\\DH2011-UMS01-28110-收费账务验收用例",
        };

        for (String s:fileNames) {
            translate(s+".doc",s+ ".html");
        }
    }
}
