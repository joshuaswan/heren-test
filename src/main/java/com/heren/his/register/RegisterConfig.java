package com.heren.his.register;

import com.heren.his.commons.HerenTest;

/**
 * Created by joshua on 2015/11/4.
 */
public class RegisterConfig extends HerenTest {
    private static enum Location {
        SaveButtonXpath("xpath=/html/body/div/div/div/div[1]/div/footer/button[3]"),
        SaveSuccessCloseXpath("xpath=/html/body/div[6]/div/h5/button"),
        CloseButtonXpath("xpath=/html/body/div/div/div/div[1]/div/footer/button[4]"),
        RestoreRegisterConfigId("id=restore-setting");

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

    //user=cmy,password=1234,workstation=门诊挂号与预约,menuGroup=7,menu=1
    public RegisterConfig(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }

    public void saveButton() {
        shortAltKeys("s");
    }

    public void saveMouse() {
        click(Location.SaveButtonXpath.getString());
    }

    public void closeRegisterConfigMouse() {
        click(Location.CloseButtonXpath.getString());
    }

    public void closeRegisterConfigKeys() {
        shortAltKeys("c");
    }

    public void restoreButtonMouse() {
        click(Location.RestoreRegisterConfigId.getString());
    }

    public void restoreButtonKeys() {
        shortAltKeys("r");
    }

}
