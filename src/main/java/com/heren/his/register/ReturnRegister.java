package com.heren.his.register;

import com.heren.his.commons.HerenTest;

/**
 * Created by joshua on 2015/12/8.
 */
public class ReturnRegister extends HerenTest {
    private static enum Location {
        VisitNumberId("id=visit-no"),
        PatientIdId("id=patientId"),
        VisitDateId("id=visit-date"),
        ReturnRegisterButtonId("id=return-register"),
        RefreshButtonXpath("xpath=/html/body/footer/button[2]"),
        CloseButtonXpath("xpath=/html/body/footer/button[3]"),
        RegisterRecordXpath("//*[@id=\"mg0-5-height\"]/div[2]"),
        RegisterRecordPerfixXpath("//*[@id=\"mg0-5-height\"]/div["),
        ConfirmReturnXpath("xpath=/html/body/div[3]/div[3]/button[1]"),
        CloseSuccessHintXpath("xpath=/html/body/div[6]/div/div[2]/button");

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

    public ReturnRegister(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }

    public void returnClinicRegister(String patientId) {
        searchClinicRegisterPatient(patientId);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        returnClinicRegisterKeys();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        confirmReturnMouse();
        closeSuccessHint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closePageMouse() {
        click(Location.CloseButtonXpath.getString());
        if (pageExist(1)) {
            LOGGER.error("close return register page failed");
        } else {
            LOGGER.info("close return register page success");
        }
    }

    public void confirmReturnMouse() {
        click(Location.ConfirmReturnXpath.getString());
    }

    public void closePageKeys() {
        shortAltKeys("c");
        if (pageExist(1)) {
            LOGGER.error("close return register page failed");
        } else {
            LOGGER.info("close return register page success");
        }
    }

    public void refreshPageMouse() {
        click(Location.RefreshButtonXpath.getString());
    }

    public void refreshPageKeys() {
        shortAltKeys("r");
    }

    public void change() {

    }

    public void closeSuccessHint() {
        closeHint(Location.CloseSuccessHintXpath.getString());
    }

    public void searchClinicRegisterPatient(String patientId) {
        sendKeys(Location.PatientIdId.getString(), patientId + "\n");
        if (elementExist(Location.RegisterRecordXpath.getString())) {
            LOGGER.info("search clinic register success,Patient id = " + patientId);
        } else {
            LOGGER.error("search clinic register failed,Patient id = " + patientId);
        }
    }

    public void returnClinicRegisterMouse() {
        click(Location.ReturnRegisterButtonId.getString());
    }

    public void returnClinicRegisterKeys() {
        shortAltKeys("b");
    }

    public void searchClinicRegisterVisitNo(String visitNo) {
        sendKeys(Location.VisitNumberId.getString(), visitNo + "\n");
        if (elementExist(Location.RegisterRecordXpath.getString())) {
            LOGGER.info("search clinic register success,visit number = " + visitNo);
        } else {
            LOGGER.error("search clinic register failed,visit number = " + visitNo);
        }
    }

}
