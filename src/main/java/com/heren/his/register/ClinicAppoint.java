package com.heren.his.register;

import com.heren.his.commons.HerenTest;

import java.util.Date;

/**
 * Created by joshua on 2015/11/13.
 */
public class ClinicAppoint extends HerenTest {
    private static enum Location {
        SeachInputId("id=searchInput"),
        PatientEnterId("id=patientId"),
        SubmitButtonId("id=appointSubmit"),
        FreshButtonId("id=fresh"),
        CloseButtonId("id=quit"),
        ClinicCardSectionPrefixXpath("xpath=//*[@id=\"clinicResourceTemplete\"]/div[2]/div/section/div["),
        ClinicCardPrefixXapth("xpath=//*[@id=\"clinicResourceTemplete\"]/div[2]/div/div["),
        ClinicDatePrefixXpath("xpath=//*[@id=\"clinicResourceTemplete\"]/div[1]/ul/li["),
        CloseFinishHintXpath("xpath=/html/body/div[6]/div/div[2]/button"),
        CloseAppointIsOverXpath("xpath=/html/body/div[6]/div/div/h5/button"),
        ChargeTypeId("id=chargetype"),
        AppointDatePrefixXpath("xpath=//*[@id=\"clinicResourceTemplete\"]/div[1]/ul/li["),
        IdentityId("id=identity");
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

    public ClinicAppoint(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }

//    public void selectDate(Date date){
//        if (date>)
//        click();
//    }

}
