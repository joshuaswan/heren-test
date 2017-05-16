package com.heren.his.commons;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class HerenTestTest1 {

    private HerenTest herenTest;
    private WebDriver driver;

    @Before
    public void defineHerenTest() {
        herenTest = new HerenTest("cmy", "1234", "mzsf", 1, 1);
    }
    @After
    public void testFinishTest() throws Exception {
        herenTest.finishTest();
    }

    @Test
    public void testSetPlugin() throws Exception {
        herenTest.setPlugin();
    }

    @Test
    public void testLogin(){
        herenTest.login();
    }

    @Test
    public void testInitPage() throws Exception {
        herenTest.initPage();
    }

    @Test
    public void testOpenPage() throws Exception {
        herenTest.login();
        herenTest.openPage();
    }

    @Test
    public void testShortAltKeys() throws Exception {
        herenTest.initPage();
        herenTest.shortAltKeys("c");
        if (herenTest.elementExist("xpath=/html/body/div/section/iframe[2]")) {
            HerenTest.LOGGER.info("关闭页面错误");
        } else {
            HerenTest.LOGGER.info("成功");
        }
        Thread.sleep(5000);
    }

    @Test
    public void testSaveScreenShot() throws Exception {
        herenTest.initPage();
    }

    @Test
    public void testSetSize() throws Exception {
        herenTest.initPage();
        herenTest.setSize(1024, 768);
        Thread.sleep(10000);
    }

    @Test
    public void testMaxSize() throws Exception {
        herenTest.initPage();
        herenTest.maxSize();
        Thread.sleep(10000);
    }

    @Test
    public void testFullScreen() throws Exception {
        testLogin();
        herenTest.fullScreenKey();
        Thread.sleep(1000);
    }

    @Test
    public void testFullScreenClick() throws Exception {
        testLogin();
        Thread.sleep(10000);
        herenTest.fullScreenClick();
        Thread.sleep(10000);
    }
}