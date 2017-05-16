package com.heren.his.register;

import com.heren.his.data.CsvUtil;
import com.heren.his.data.SearchData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class ClinicRegisterTest {

    private ClinicRegister clinicRegister;
    private SearchData searchData;
    private String[] patientId = new String[1000];

    @Before
    public void initClinicRegister() {
        clinicRegister = new ClinicRegister("cmy", "1234", "mzgh", 1, 1);
        clinicRegister.initPage();
    }

    public int prepareRegisterData() {
        searchData = new SearchData();
        int loopNumber = 0;
        try {
            loopNumber = searchData.singleData(patientId, patientId.length, "select p.patient_id from pat_master_index p " +
                    "where p.patient_id not in" +
                    " (select t.patient_id from outp_visit t where t.visit_date = to_date(" + "'" + searchData.getToday() + "'" + ",'yyyy-mm-dd')) " +
                    "and p.patient_id not in (select pmi.pid_related from pmi_merged_index pmi) and p.charge_type = '自费'" +
                    "and p.patient_id not in (select inp.patient_id from inp_visit inp where inp.adt_status in ('0','1','2','3')) " +
                    "and p.birth_place_code='532625' " +
                    " order BY p.patient_id", "patient_id");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loopNumber;
    }

    @Test
    public void testSelectClinicRegisterKeys() throws Exception {
        clinicRegister.selectClinicRegisterKeys("zqs");
    }

    @Test
    public void testSelectPatientInformation() throws Exception {
        clinicRegister.selectPatientInformation("25698");
    }

    @After
    public void finishTest() {
        clinicRegister.finishTest();
    }

    @Test
    public void testOpenTimeDescModel() throws Exception {
        clinicRegister.openTimeDescModel();
    }

    @Test
    public void testSelectClinicRegisterMouse() throws Exception {
        clinicRegister.selectClinicRegisterMouse(1, 1, 1);
    }

    @Test
    public void testSelectTimeDesc() throws Exception {
        clinicRegister.selectTimeDesc("上午");
    }

    @Test
    public void testCancelClinicRegister() throws Exception {
        clinicRegister.cancelClinicRegister();
    }

    @Test
    public void testFinishClinicRegister() throws Exception {
        Thread.sleep(10000);
        String[] patients = new String[10];
        patients[0] = "P100320";
        patients[1] = "P100321";
        patients[2] = "P100322";
        patients[3] = "P100323";
        patients[4] = "P100324";
        patients[5] = "P100325";
        patients[6] = "P100326";
        patients[7] = "P100327";
        patients[8] = "P100328";
        patients[9] = "P100329";
        for (int i = 3; i < 10; i++) {
            clinicRegister.finishClinicRegister("10355", patients[i], "100");
            Thread.sleep(1000);
        }
    }

    @Test
    public void testRegister() throws Exception {
        Thread.sleep(1000);
        CsvUtil csvUtil = new CsvUtil("src\\main\\resources\\dataFile\\patient_id.csv");
        String[] patientId = csvUtil.getCol(0).split(",");
        long times[] = new long[500];
        int i;
        for (i = 0; i < 500; i++) {
            Thread.sleep(1000);
            clinicRegister.updateTime();
            try {
                clinicRegister.finishClinicRegister("bfcsa", patientId[i], "100");
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            times[i] = clinicRegister.duringTime();
            System.out.println(i);
        }
        long sum = 0;
        for (int j = 0; j < times.length; j++) {
            sum += times[j];
            System.out.println(times[j]);
        }
        System.out.println("挂号个数" + i);
        System.out.println("挂号平均时间为：" + (sum / (i + 1)) + "ms");
    }

    //    @Test
    public void clinicRegisterCapacity(int loopNumber) {
        int dateNumber = prepareRegisterData();
        if (loopNumber > dateNumber) {
            loopNumber = dateNumber;
        }
        System.out.println(loopNumber);
        for (int i = 0; i < loopNumber; ++i) {
            clinicRegister.finishClinicRegister("bfcsa", patientId[i], "100");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("成功挂号数量 : " + i);
        }
    }

    @Test
    public void mrCheckTest() throws Exception {
        Thread.sleep(1000);
        CsvUtil csvUtil = new CsvUtil("src\\main\\resources\\dataFile\\patient_id.csv");
        String[] patientId = csvUtil.getCol(0).split(",");
        long times[] = new long[500];
        int i;
        for (i = 0; i < 500; i++) {
            Thread.sleep(1000);
            clinicRegister.updateTime();
            try {
                clinicRegister.finishClinicRegister("bfcsa", patientId[i], "100");
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
            times[i] = clinicRegister.duringTime();
            System.out.println(i);
        }
    }


    //    @Test
    public void numberClinicRegister() {
        clinicRegisterCapacity(500);
    }
}