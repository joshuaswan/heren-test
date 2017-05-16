package com.heren.his.bill;

import com.heren.his.commons.HerenTest;

/**
 * Created by joshua on 2015/11/4.
 */
public class Bill extends HerenTest {

    //定义对应的页面元素的定位信息，当页面元素进行变化时需要改变对应值
    private static enum Location {
        PatientIdInputId("id=patient-id-input"),
        RecipeNoInputId("id=recipe-no-input"),
        ChargeTypeSelectID("id=s2id_charge-type-select"),
        SumTheMoneyXpath("xpath=/html/body/div[1]/footer/div/button[1]"),
        ClearScreenXpath("xpath=/html/body/div[1]/footer/div/button[2]"),
        CloseButtonXpath("xpath=/html/body/div[1]/footer/div/button[3]"),
        OtherOptionXpath("xpath=/html/body/div[1]/footer/div/div/button"),
        NewObjectButtonXpath("xpath=/html/body/div[1]/footer/div/div/ul/li[1]"),
        DeleteBillButtonXpath("xpath=/html/body/div[1]/footer/div/div/ul/li[2]"),
        ModelButtonXpath("xpath=/html/body/div[1]/footer/div/div/ul/li[3]"),
        NewBillButtonXpath("xpath=/html/body/div[1]/footer/div/div/ul/li[5]"),
        RemoveButtonXpath("xpath=/html/body/div[1]/footer/div/div/ul/li[6]"),
        RecoverButtonXpath("xpath=/html/body/div[1]/footer/div/div/ul/li[7]"),
        PaySubmitButtonId("id=payment-submit-button"),
        MoneyInputXpath("xpath=//*[@id=\"billPayment\"]/div[2]/div/div[4]/div[1]/div/div/input"),
        PaymentMoneyTypeXpath("xpath=//*[@id=\"s2id_money-type\"]/a/span[1]"),
        ParentPayMoneyXpath("xpath=//*[@id=\"billPayment\"]/div[2]/div/div[2]/div[2]/div/div/span[1]/strong"),
        CloseFinishHintXpath("xpath=/html/body/div[6]/div/h5/button");


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

    public Bill(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }

    //按患者ID查看医嘱收费单
    public void searchOfPatientId(String patientId){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(Location.PatientIdInputId.getString(), patientId + "\n");
    }

    //按医嘱单号查询收费单据
    public void searchOfRecipeNo(String recipeNo){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(Location.RecipeNoInputId.getString(), recipeNo + "\n");
    }

    public void settleMoney() throws InterruptedException {
        click(Location.SumTheMoneyXpath.getString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String paymentType=herenFindElement(Location.PaymentMoneyTypeXpath.getString()).getText();
        if(paymentType.trim().equals("现金")){
            String money = herenFindElement(Location.ParentPayMoneyXpath.getString()).getText();
            double temMoney = Double.parseDouble(money) +10;
            click(Location.MoneyInputXpath.getString());
            sendKeys(Location.MoneyInputXpath.getString(), String.valueOf(temMoney));
        }
        click(Location.PaySubmitButtonId.getString());
    }

    public void settleMoneyTest() throws InterruptedException {
        searchOfPatientId("10004");
        Thread.sleep(2000);
        try {
            settleMoney();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
