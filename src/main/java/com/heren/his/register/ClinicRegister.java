package com.heren.his.register;

import com.heren.his.commons.HerenTest;

/**
 * Created by joshua on 2015/11/3.
 */
public class ClinicRegister extends HerenTest {

    private static enum Location {
        ClinicSeachInputId("id=searchInput"),
        PatientEnterId("id=patientId"),
        SubmitButtonId("id=registerSubmit"),
        FreshId("id=fresh"),
        CloseId("id=quit"),
        PaySubmitButtonId("id=payment-submit-button"),
        MoneyInputXpath("xpath=//*[@id=\"billPayment\"]/div[2]/div/div[4]/div[1]/div/div/input"),
        ClinicChoosedXpath("xpath=//*[@id=\"clinicResourceTemplete\"]/header/div[2]/div[1]"),
        ClinicCardSectionPrefixXpath("xpath=//*[@id=\"clinicResourceTemplete\"]/div/div/section/div["),
        ClinicCardPrefixXapth("xpath=//*[@id=\"clinicResourceTemplete\"]/div/div/div["),
        ChargeTypeId("id=chargetype"),
        IdentityId("id=identity"),
        PatientMasterIndexXpath("xpath=//*[@id=\"mg0-5\"]/footer/div[1]/label/a"),
        CloseSuccessHintXpath("xpath=/html/body/div[6]/div/div[2]/button"),
        TimeDescXpath("xpath=//*[@id=\"clinicResourceTemplete\"]/header/div[3]/div[1]"),
        SaveTimeDescXpath("xpath=//*[@id=\"mg0-5\"]/div[4]/div[3]/button[1]"),
        PatientNameId("id=patientName"),
        TimeDescExistXpath("xpath=//*[@id=\"mg0-5\"]/div[4]"),
        MoneyTypeId("id=money-type"),
        MoneyTypeXpath("xpath=//*[@id=\"select2-drop\"]/div/input"),
        MrCheckXpath("xpath=//*[@id=\"guahaoPatientInfoTemplete\"]/section/div[2]/div[1]/label/input");

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

    public ClinicRegister(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        //ClinicRegister("mzghy", "1234", "门诊挂号", 1, 1);
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }

    public void selectClinicRegisterKeys(String clinicString) {

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        clinicString = clinicString + "\n";
        sendKeys(Location.ClinicSeachInputId.getString(), clinicString);
        if (elementExist(Location.ClinicChoosedXpath.getString())) {
            LOGGER.info("choose clinic card success");
        } else {
            LOGGER.info("choose clinic card failed");
        }
    }

    public void selectClinicRegisterMouse(int i, int j, int k) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String firstClinicCard = Location.ClinicCardSectionPrefixXpath.getString() + i + "]";
        String secondClinicCard = Location.ClinicCardSectionPrefixXpath.getString() + j + "]";
        String thirdClinicCard = Location.ClinicCardPrefixXapth.getString() + k + "]";
        doubleClinic(firstClinicCard);
        doubleClinic(secondClinicCard);
        doubleClinic(thirdClinicCard);

        if (elementExist(Location.ClinicChoosedXpath.getString())) {
            LOGGER.info("choose clinic card success");
        } else {
            LOGGER.info("choose clinic card failed");
        }
    }

    public void openTimeDescModel() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        doubleClinic(Location.TimeDescXpath.getString());
        if (elementExist(Location.TimeDescExistXpath.getString())) {
            LOGGER.info("open time desc model success");
        } else {
            LOGGER.error("open time desc model failed");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectTimeDesc(String timeDesc) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        openTimeDescModel();
        click("id=" + timeDesc);
        click(Location.SaveTimeDescXpath.getString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public void setClinicTime(String date){
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        openTimeDescModel();
////        sendKeys();
//    }

    public void cancelClinicRegister() {
        selectClinicRegisterMouse(1, 1, 1);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doubleClinic(Location.ClinicChoosedXpath.getString());
        if (elementExist(Location.ClinicChoosedXpath.getString())) {
            LOGGER.error("cancel clinic register failed");
        } else {
            LOGGER.info("cancel clinic register success");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void selectPatientInformation(String patientId) {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        patientId = patientId + "\n";
        clear(Location.PatientEnterId.getString());
        sendKeys(Location.PatientEnterId.getString(), patientId);
        if (getText(Location.PatientNameId.getString()) == null) {
            LOGGER.error("select patient information failed");
        } else {
            LOGGER.info("select patient information success");
        }
    }

    public void selectPatientInfo(String patientId) {
        selectPatientInformation(patientId);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(Location.IdentityId.getString(), "\n");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(Location.ChargeTypeId.getString(), "\n");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void payMoneyMouse(String moneyType, String money) {
        if (moneyType == "刷卡") {
            herenSelect(Location.MoneyTypeId.getString(), Location.MoneyTypeXpath.getString(), moneyType);
        } else if (moneyType == null) {
            try {
                sendKeys(Location.MoneyInputXpath.getString(), money);
            }catch (Exception e){
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                sendKeys(Location.MoneyInputXpath.getString(), money);
            }
        } else {
            herenSelect(Location.MoneyTypeId.getString(), Location.MoneyTypeXpath.getString(), moneyType);
            sendKeys(Location.MoneyInputXpath.getString(), money);
        }
        click(Location.PaySubmitButtonId.getString());
    }

    public void submitClinicRegisterMouse() {
        click(Location.SubmitButtonId.getString());
    }

    public void submitClinicRegisterKeys() {
        click(Location.PatientEnterId.getString());
        shortAltKeys("p");
    }

    public void mrCheck(){
        click(Location.MrCheckXpath.getString());
        herenSelect("id=s2id_autogen1","xpath=//*[@id=\"select2-drop\"]/div/input","ptwk");
    }

    public void closeSuccessHint() {
        closeHint(Location.CloseSuccessHintXpath.getString());
    }

    public void finishClinicRegister(String clinicString, String patientId, String money) {
//        times = System.currentTimeMillis();
        selectClinicRegisterKeys(clinicString);
        LOGGER.info("select clinic register time : " + (System.currentTimeMillis() - times));
        selectPatientInfo(patientId);
        LOGGER.info("select patient information time : " + (System.currentTimeMillis() - times));
        click(Location.PatientEnterId.getString());
        mrCheck();
        submitClinicRegisterKeys();
        LOGGER.info("submit clinic register time : " + (System.currentTimeMillis() - times));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        times = System.currentTimeMillis();
        payMoneyMouse(null, money);
        LOGGER.info("pay money time : " + (System.currentTimeMillis() - times));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        closeSuccessHint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
