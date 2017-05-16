package com.heren.his.commons;

import com.google.inject.Inject;
import com.heren.his.commons.exceptions.SystemException;
import com.heren.his.data.SearchData;
import com.heren.his.domain.entity.TestResultInfo;
import com.heren.his.domain.facade.TestResultInfoFacade;
import org.apache.commons.io.FileUtils;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by joshua on 2015/12/4.
 */
public class HerenTest {
    //定义对应页面的元素定位信息
    private enum Location {
        UserNameId("id=username"),
        UserPasswordId("id=userpassword"),
        ApplicationID("id=s2id_appselector"),
        ApplicationInputXpath("xpath=//*[@id=\"select2-drop\"]/div/input"),
        LoginButtonId("id=loginButton"),
        OpenPlugin("chrome://plugins"),
        JavaPluginId("id=java-runtime-environment-always-allowed"),
        PluginHintXpath("xpath=/html/body/div[2]/div/h5/button"),
        IframeXpath("xpath=/html/body/div/section/iframe[2]"),
        IframePerfixXpath("xpath=/html/body/div/section/iframe["),
        MenuPrefixXpath("xpath=//*[@id=\"baseFrameworkheader\"]/div[2]/div/ul/li["),
        FullScreenId("id=fullscreen-button");

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

    protected static final Logger LOGGER = LoggerFactory.getLogger(HerenTest.class);
    protected WebDriver driver;
    //登录用户名
    protected String userName;
    //登录密码
    protected String password;
    //登录对应工作站
    protected String workStation;
    //对应访问的URL上
    protected String url;
    //标记对应插件设置是否成功，来判定是否关闭安装插件提示
//    private boolean plugin = false;
    //对应页面的菜单位置
    protected int group;
    protected int number;
    //记录对应动作的反应时间
    protected static long times = System.currentTimeMillis();
    private static Configuration configuration;

    @Inject
    private TestResultInfoFacade testResultInfoFacade;

//    protected TestResultInfoFacade resultInfoFacade;

    public static SearchData searchData;

    public HerenTest() {
        try {
            configuration = new PropertiesConfiguration("config.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        this.url = configuration.getString("url");
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    }

    /**
     *
     * */
    public HerenTest(String _UserName, String _Password, String _WorkStation, int _group, int _number) {

        this();

        this.searchData = new SearchData();
        this.userName = _UserName;
        this.password = _Password;
        this.workStation = _WorkStation;
        this.group = _group;
        this.number = _number;
    }

    public static void updateTime(){
        times=System.currentTimeMillis();
    }

    public static long duringTime(){
        return System.currentTimeMillis() - times;
    }

    public String setPlugin() {
        WebDriver.Navigation navigation = driver.navigate();
        //打开plugin页面
        navigation.to(Location.OpenPlugin.getString());
        if (elementExist(Location.JavaPluginId.getString())) {
            herenFindElement(Location.JavaPluginId.getString()).click();
            LOGGER.info("set java plugin is success");
            return "设置浏览器插件成功！";
        } else {
            LOGGER.info("set java plugin failed");
            throw new SystemException("设置浏览器插件失败！");
        }
    }

    public void login() {
        WebDriver.Navigation navigation = driver.navigate();
        navigation.to(url);

        //判断是否有提示框，并关闭提示框
        if (elementExist(Location.PluginHintXpath.getString())) {
            click(Location.PluginHintXpath.getString());
        } else {
        }

        sendKeys(Location.UserNameId.getString(), userName);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(Location.UserPasswordId.getString(), password);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        herenSelect(Location.ApplicationID.getString(), Location.ApplicationInputXpath.getString(), workStation);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return herenFindElement(Location.LoginButtonId.getString());
            }
        }).click();

        LOGGER.info("Login success");
    }

    public final void herenSelect(String selecterId, String createXpath, String inputString) {
        //选择工作台
        click(selecterId);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(createXpath, inputString);
        sendKeys(createXpath, "\n");
    }

    public void initPage() {
//        setPlugin();
        login();
        openPage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //打开对应页面，标记方式为对应的主菜单和子菜单，定位明确后可打开对应页面
    public void openPage() {

        final String groupString = Location.MenuPrefixXpath.getString() + String.valueOf(group) + "]";
        String numberString = groupString + "/ul/li[" + String.valueOf(number) + "]";
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(groupString);
        click(numberString);
        driver.switchTo().frame(herenFindElement(Location.IframeXpath.getString()));
        LOGGER.info("open page success");
    }

    public void switchPage(int page){
        driver.switchTo().defaultContent();
        driver.switchTo().frame(herenFindElement(Location.IframePerfixXpath.getString() + String.valueOf(page+1) + "]"));
    }

    public final WebElement herenFindElement(String location) {
        if (transformLocation(location) == location) {
            LOGGER.error("the location is error");
            return null;
        } else if (location.charAt(0) == 'x') {
            return driver.findElement(By.xpath(location.substring(6, location.length())));
        } else {
            return driver.findElement(By.id(location.substring(3, location.length())));
        }
    }

    public final void sendKeys(String location, String value) {
        if (transformLocation(location) == null) {
            LOGGER.error("the function is failed");
            return;
        } else {
            herenFindElement(location).sendKeys(value);
        }
    }

    public final String getText(String location) {
        return herenFindElement(location).getText();
    }

    public final void doubleClinic(String location) {
        Selenium selenium = new WebDriverBackedSelenium(driver, driver.getCurrentUrl());
        selenium.doubleClick(location);
    }

    public final void click(String location) {
        if (transformLocation(location) == null) {
            LOGGER.error("the function is failed");
            return;
        } else {
            herenFindElement(location).click();
        }
    }

    public final void fullScreenKey(){
        pageShortKeys(Keys.F11);
    }

    public final void fullScreenClick(){
        if(elementExist(Location.IframePerfixXpath.getString())){
            driver.switchTo().defaultContent();
        }
        click(Location.FullScreenId.getString());
    }

    public final boolean elementExist(String location) {
        try {
            herenFindElement(location);
            LOGGER.info("this element is exist : " + location);
            return true;
        } catch (NoSuchElementException e) {
            LOGGER.info("this element is not exist : " + location);
            return false;
        }
    }

    public final String transformLocation(String location) {
        if (location.charAt(0) == 'i') {
            return location.substring(3, location.length());
        } else if (location.charAt(0) == 'x') {
            return location.substring(6, location.length());
        } else {
            LOGGER.info("location transform failed");
            return location;
        }
    }

    public final void clear(String location) {
        if (transformLocation(location) == null) {
            LOGGER.error("the function is failed");
            return;
        } else {
            herenFindElement(location).clear();
        }
    }

    public final void closeHint(String location) {
        driver.switchTo().defaultContent();
        try {
            click(location);
        } catch (NoSuchElementException e) {
        }
        driver.switchTo().frame(herenFindElement(Location.IframeXpath.getString()));
        LOGGER.info("close body hint success : " + location);
    }

    public final void shortAltKeys(String letter) {
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ALT).sendKeys(letter).keyUp(Keys.ALT).perform();
    }

    public final void pageShortKeys(Keys keys) {
        Actions actions = new Actions(driver);
        actions.sendKeys(keys).perform();
    }

    public final String getTagName(String location) {
        if (transformLocation(location) == null) {
            LOGGER.error("the function is failed");
            return null;
        } else {
            return herenFindElement(location).getTagName();
        }
    }

    public void savePrintFile(String fileName) {
//        File printFile = new File(fileName);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(fileName);
    }

    public boolean pageExist(int pageNumber) {
        pageNumber = pageNumber - 1;
        if (elementExist(Location.IframePerfixXpath.getString() + pageNumber + "]")) {
            return true;
        } else {
            return false;
        }
    }

    public final void saveScreenShot(String testCaseId) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath + "\\saveImage";

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        try {
            LOGGER.info("save snapshot path is : " + currentPath);
            FileUtils.copyFile(screenShotFile, new File(currentPath + "\\" + "test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("Can't save screenShot");
        } finally {
            LOGGER.info("screen shot finish");
        }

        currentPath = currentPath + "\\test.jpg";
        testResultInfoFacade.savePhotograph(testCaseId,currentPath);
    }

    public void setSize(int width, int height) {
        driver.manage().window().setSize(new Dimension(width + 10, height + 82));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void maxSize() {
        driver.manage().window().maximize();
    }

    //销毁对应chromeDriver资源，不然会导致电脑启动多个chromeDriver进程
    public void finishTest() {
        driver.quit();
        LOGGER.info("finish test success");
    }

    public void backToBody(){
        driver.switchTo().defaultContent();
    }

    public void gotoIframe(int iframe){
        driver.switchTo().frame(herenFindElement(Location.IframePerfixXpath.getString() + String.valueOf(iframe) + "]"));
    }

    public void close(){
        driver.close();
    }
}
