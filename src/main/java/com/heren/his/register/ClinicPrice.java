package com.heren.his.register;

import com.heren.his.commons.HerenTest;

/**
 * Created by joshua on 2015/11/5.
 * 这个程序基本上没有设么功能，只是对一个页面的查询，所以没啥用
 */
public class ClinicPrice extends HerenTest {
    private static enum Location {
        SearchButtonId("id=btnSearch"),
        CloseButtonXpath("xpath=/html/body/footer/button"),
        ChargeTypeId("id=s2id_autogen1"),
        InputChargeTypeXpath("xpath=//*[@id=\"select2-drop\"]/div/input");

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

    public ClinicPrice(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }

}
