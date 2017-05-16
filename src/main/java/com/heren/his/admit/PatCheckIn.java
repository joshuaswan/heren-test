package com.heren.his.admit;

import com.heren.his.commons.HerenTest;

/**
 * Created by joshua on 2016/3/30.
 */
public class PatCheckIn extends HerenTest {

    //定义对应的页面元素的定位信息，当页面元素进行变化时需要改变对应值
    private static enum Location {
        SearchTypePerfixXpath("xpath=/html/body/header/form/label["),
        QueryTextId("id=queryText"),
        ;


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

    public PatCheckIn(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }

    /**
     *
     * @param type
     */
    public void setSearchType(int type){
        click(Location.SearchTypePerfixXpath.getString() + String.valueOf(type));
    }

    public void queryPatientInfo(String queryString){
        sendKeys(Location.QueryTextId.getString(),queryString + "\n");
    }

    public void inputPatientInfo(String nation){

    }
}
