package com.heren.his.commons;

import com.google.inject.Inject;
import com.heren.his.commons.exceptions.SystemException;
import com.heren.his.domain.facade.TestResultInfoFacade;

/**
 *
 */
public class HerenTestTest extends HerenTest {

    private enum Location {
        MessageAXpath("xpath=//*[@id=\"baseFrameworkheader\"]/div[1]/ul[1]/li[1]/a"),
        MessageCloseButtonXpath("xpath=/html/body/div[4]/div[1]/button");

        private String string;

        private Location(String s) {
            this.string = s;
        }

        public String getString() {
            return string;
        }

        public void setString(String name) {
            this.string = name;
        }

    }

//    @Inject
//    private TestResultInfoFacade testResultInfoFacade;

    @Inject
    public HerenTestTest(){
        super("cmy","1234","mzsf",1,1);
    }

    public String setPluginTest(String testCaseId){
        String result = setPlugin();
        saveScreenShot(testCaseId);
        finishTest();
        return result;
    }

    public String setPluginTestNotSave(){
        String result = setPlugin();
        finishTest();
        return result;
    }

    public String setBrows1024(String testCaseId){
        initPage();
        setSize(1024,768);
        saveScreenShot(testCaseId);
        finishTest();
        return "设置分辨率为1024*768并截图成功！";
    }

    public String setBrows1280( String testCaseId){
        initPage();
        setSize(1280,800);
        saveScreenShot(testCaseId);
        finishTest();
        return "设置分辨率为1280*800并截图成功！";
    }

    public String setBrows1366(String testCaseId){
        initPage();
        setSize(1366,800);
        saveScreenShot(testCaseId);
        finishTest();
        return "设置分辨率为1366*800并截图成功！";
    }

    public String setBrows1440(String testCaseId){
        initPage();
        setSize(1440,900);

        saveScreenShot(testCaseId);
        finishTest();
        return "设置分辨率为1440*900并截图成功！";
    }

    public String openMessageBox(String testCaseId){
        login();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (elementExist(Location.MessageAXpath.getString())){

            click(Location.MessageAXpath.getString());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            saveScreenShot(testCaseId);
            finishTest();
            return "打开消息框成功！";
        }else {
            throw new SystemException("消息按钮定位错误！");
        }
    }

    public String closeMessageBox(String testCaseId){
        login();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (elementExist(Location.MessageAXpath.getString())){
            click(Location.MessageAXpath.getString());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (elementExist(Location.MessageCloseButtonXpath.getString())){
                click(Location.MessageCloseButtonXpath.getString());
            }else {
                throw new SystemException("关闭消息按钮定位错误！");
            }
            saveScreenShot(testCaseId);
            finishTest();
            return "关闭消息框成功！";
        }else {
            throw new SystemException("消息按钮定位错误！");
        }
    }

    @Override
    public void close() {
        super.close();
    }
}