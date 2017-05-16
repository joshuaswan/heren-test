package com.heren.his.bill;


import com.heren.his.commons.HerenTest;

/**
 * Created by joshua on 2015/11/8.
 */
public class BillConfig extends HerenTest {
    private static enum Location {
        OnlyOneBillPerfixXpath("xpath=/html/body/section/div/div/div[1]/div[1]/div[2]/param-scope-type/div/div/label["),
        SaveButtonXpath("xpath=/html/body/footer/button[1]"),
        NotReopenHintXpath("xpath=/html/body/div[6]/div/div[2]/button[2]"),
        CloseButtonXpath("xpath=/html/body/footer/button[2]");

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

    public BillConfig(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }
}
