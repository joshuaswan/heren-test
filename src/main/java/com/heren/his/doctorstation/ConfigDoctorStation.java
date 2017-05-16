package com.heren.his.doctorstation;

import com.heren.his.commons.HerenTest;

/**
 * Created by lisen on 2015/12/24.
 */
public class ConfigDoctorStation extends HerenTest {

    private enum Location {
        DrugId("id=s2id_autogen9"),
        DrugXpath("xpath=//*[@id=\"select2-drop\"]/div/input"),
        SaveButtonXpath("xpath=/html/body/footer/button[1]"),
        CloseHintXpath("xpath=/html/body/div[6]/div/div[2]/button[2]");

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

    public ConfigDoctorStation(String _UserName, String _Password, String _WorkStation, int _group, int _number){
        super(_UserName,_Password,_WorkStation,_group,_number);
    }

    public void setDrug(String drug){
        herenSelect(Location.DrugId.getString(),Location.DrugXpath.getString(),drug);
    }

    public void saveChange(){
        click(Location.SaveButtonXpath.getString());
        closeHint(Location.CloseHintXpath.getString());
        LOGGER.info("save change success");
    }
}
