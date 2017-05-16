package com.heren.his.nurse;

import com.google.inject.Inject;
import com.heren.his.commons.HerenTest;
import com.heren.his.commons.exceptions.SystemException;

/**
 * Created by joshua on 2015/11/19.
 */
public class BedMaintain extends HerenTest {

    //定义对应页面的元素定位信息
    private static enum Location {
        NewBedButtonXpath("xpath=/html/body/footer/div/button[1]"),

        InputBedNumberXpath("xpath=/html/body/div[1]/div[2]/div[2]/div/input"),
        BedDeptInputId("id=s2id_deptAdmissionTo"),
        ConfirmBedInputXpath("xpath=//*[@id=\"select2-drop\"]/div/input"),
        BedTypeInputId("id=s2id_autogen"),
        BedOfficeInputId("id=s2id_autogen2"),
        BedClassInputId("id=s2id_autogen2"),
        NewBedInfoAreaXpath("xpath=/html/body/section/div[2]");

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
    public BedMaintain(){
        super("hj","1234","kfyxkhsz",3,1);
        initPage();
        switchPage(2);
    }

    public String clickAddBedButton(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String newBed = Location.NewBedButtonXpath.getString();
        click(newBed);
        if (elementExist(Location.NewBedInfoAreaXpath.getString())){
            finishTest();
            return "点击新增床位维护成功！";
        }else {
            finishTest();
            throw new SystemException("点击新增床位维护失败！");
        }
    }
}
