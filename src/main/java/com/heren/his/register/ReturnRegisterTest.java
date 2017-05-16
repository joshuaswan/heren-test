package com.heren.his.register;

import com.heren.his.data.SearchData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class ReturnRegisterTest {
    private ReturnRegister returnRegister;
    private ClinicRegisterTest clinicRegisterTest;
    private String[] patientId = new String[1000];
    private SearchData searchData;

    @Before
    public void initTest() {
        returnRegister = new ReturnRegister("cmy", "1234", "mzgh", 1, 2);
        returnRegister.initPage();
    }

    public int prepareReturnRegisterData() {
        searchData = new SearchData();
        int loopNumber = 0;
        try {
            loopNumber = searchData.singleData(patientId, patientId.length,
                    "select * from outp_visit t where t.visit_date = to_date(" + "'" + searchData.getToday() + "'" + ",'yyyy-mm-dd') " +
                            "and t.visit_status = '挂号' and t.clinic_code = '20151012000000000002'", "patient_id");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loopNumber;
    }

    public void setClinicRegister() {
        clinicRegisterTest.initClinicRegister();
        clinicRegisterTest.clinicRegisterCapacity(10);
        clinicRegisterTest.finishTest();
    }

    @Test
    public void testReturnClinicRegister() throws Exception {
        int loopNumber = prepareReturnRegisterData();
        for (int i = 0; i < loopNumber; ++i) {
            returnRegister.returnClinicRegister(patientId[i]);
            System.out.println("return register number : " + i);
        }
    }

    @Test
    public void testClosePageMouse() throws Exception {
        returnRegister.closePageMouse();
    }

    @Test
    public void testClosePageKeys() throws Exception {
        returnRegister.closePageKeys();
    }

    @Test
    public void testRefreshPageMouse() throws Exception {
        returnRegister.refreshPageMouse();
    }

    @Test
    public void testRefreshPageKeys() throws Exception {
        returnRegister.refreshPageKeys();
    }

    @Test
    public void testSearchClinicRegisterPatient() throws Exception {

    }

    @Test
    public void testSearchClinicRegisterVisitNo() throws Exception {

    }

    @After
    public void finishTest() {
        returnRegister.finishTest();
    }
}