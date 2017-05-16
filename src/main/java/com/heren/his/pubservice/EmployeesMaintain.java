package com.heren.his.pubservice;

import com.google.inject.Inject;
import com.heren.his.commons.HerenTest;
import com.heren.his.commons.exceptions.SystemException;

/**
 * Created by joshua on 2016/6/23.
 */
public class EmployeesMaintain extends HerenTest {

    private enum Location {
        SearchInputXpath("xpath=/html/body/div/div[1]/section[2]/div[1]/div[1]/div/input"),
        SearchButtonXpath("xpath=/html/body/div/div[1]/section[2]/div[1]/div[1]/div/button");

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


    @Inject
    public EmployeesMaintain(){
        super("mj","1234","jcpt",2,1);
        initPage();
    }

    public String shortScreem(String testCaseId){
        saveScreenShot(testCaseId);
        if ( 1==0 ){
            throw new SystemException("消息按钮定位错误！");
        }
        finishTest();
        return "保存截图成功！";
    }

    public String searchStaffDict(){
        sendKeys(Location.SearchInputXpath.getString(),"asdfasdfasd");
        click(Location.SearchButtonXpath.getString());
        if (elementExist("xpath=/html/body/div/div[1]/section[2]/div[2]/div[3]/div[2]/div/div"))
        {
            finishTest();
            return "查询人员字典成功！";
        }else {
            finishTest();
            throw new SystemException("查询人员字典失败！");
        }
    }
}
