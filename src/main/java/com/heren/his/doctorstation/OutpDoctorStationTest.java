package com.heren.his.doctorstation;

import com.heren.his.data.CsvUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joshua on 2016/3/25.
 */
public class OutpDoctorStationTest {

    public OutpDoctorStation outpDoctorStation;

    public String patientId;

    @Before
    public void startTest() throws Exception {
        outpDoctorStation = new OutpDoctorStation("gehong", "1234", "gkmzys", 1, 1);
        outpDoctorStation.initPage();
    }

    @After
    public void finishTest() {
        outpDoctorStation.finishTest();
    }

    @Test
    public void testOpenClinic() throws Exception {
        outpDoctorStation.openClinic(1);
    }

    public void testChoosePatient(String patientIdString) throws Exception {
        outpDoctorStation.openClinic(1);
        outpDoctorStation.choosePatient(patientIdString);
    }

    public void testReChoosePatient(String rePatientId) throws Exception {
        testChoosePatient(patientId);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        outpDoctorStation.reChoosePatient(rePatientId);
    }

    @Test
    public void testOpenCaseHis() throws Exception {
        testChoosePatient(patientId);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        outpDoctorStation.openCaseHis();
    }

    @Test
    public void testSavePatientCase() throws Exception {
        testOpenCaseHis();
        outpDoctorStation.savePatientCase("感冒", "感冒", "食物中毒");
    }

    @Test
    public void testPrescribeWestDrug() throws Exception {
        testSavePatientCase();
        outpDoctorStation.prescribeWestDrug("aszlp", 1, 20);
        outpDoctorStation.saveOrderClick();
    }

    @Test
    public void all() throws Exception {
        CsvUtil csvUtil = new CsvUtil("src\\main\\resources\\dataFile\\clinic_patient.csv");
        String[] patientIdS = csvUtil.getCol(1).split(",");
        testChoosePatient(patientIdS[0]);
        for (int i = 1; i < 133; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outpDoctorStation.openCaseHis();
            boolean isOK = true;
            do {
                try {
                    outpDoctorStation.savePatientCase("感冒", "感冒", "食物中毒");
                    isOK = true;
                } catch (Exception e) {
                    outpDoctorStation.closeHint("xpath=/html/body/div[7]/div/div/div[2]/button");
                    isOK = false;
                    e.printStackTrace();
                }
            } while (!isOK);

            outpDoctorStation.prescribeWestDrug("aszlp", 1, 20);
            outpDoctorStation.saveOrderClick();
            outpDoctorStation.prescribeWestDrug("aszlp", 1, 20);
            outpDoctorStation.saveOrderClick();
            outpDoctorStation.reChoosePatient(patientIdS[i]);
        }
    }

    @Test
    public void inputTest() throws Exception {

        CsvUtil csvUtil = new CsvUtil("dataFile\\drug_input_code.csv");
        String[] drugString = csvUtil.getCol(1).split(",");

        testChoosePatient("P101137");
        Thread.sleep(500);
        outpDoctorStation.openCaseHis();
        outpDoctorStation.savePatientCase("感冒", "感冒", "食物中毒");
        outpDoctorStation.openDoctorOrder();
        outpDoctorStation.openWestDrugOrder();
        int i = 0;
        int count = 0;
        long times[] = new long[100];
        for (i = 0; i < 100; i++) {
            outpDoctorStation.updateTime();
            outpDoctorStation.inputWestDrug(drugString[i]);
            if (outpDoctorStation.elementExist("xpath=//*[@id=\"leftSearch\"]/table/tbody/tr[1]/td")) {
                count++;
            }
            times[i] = outpDoctorStation.duringTime();
        }
        int sum = 0;
        for (int j = 0; j < times.length; j++) {
            sum += times[j];
            System.out.println(times[j]);
        }
        System.out.println("药品平均检索时间为：" + (sum / times.length) + "ms");
        System.out.println("输入法成功率为：" + (count / i * 100) + "%");
    }
}