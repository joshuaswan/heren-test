package com.heren.his.doctorstation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigDoctorStationTest {

    private ConfigDoctorStation configDoctorStation;

    @Before
    public void beforeTest() {
        configDoctorStation = new ConfigDoctorStation("zhqs", "1234", "hxnkmz", 4, 7);
        configDoctorStation.login();
        configDoctorStation.openPage();
    }

    @After
    public void afterTest() {
        configDoctorStation.finishTest();
    }

    @Test
    public void testSetDrug() throws Exception {
        configDoctorStation.setDrug("zxyf");
    }

    @Test
    public void testSaveChange() throws Exception {
        configDoctorStation.setDrug("zxyf");
        configDoctorStation.saveChange();
    }
}