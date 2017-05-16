package com.heren.his.patient;

import com.heren.his.commons.HerenTest;
import com.thoughtworks.selenium.Selenium;

import java.sql.*;

/**
 * Created by joshua on 2015/11/5.
 */
public class PatientMasterIndex extends HerenTest {
    private static enum Location {
        PatientIdId("id=patientId"),
        IdNumberInputId("id=idNoInput"),
        SecurityTypeId("id=securitytype"),
        NameInputId("id=name"),
        NamePhoneticId("id=namephonetic"),
        SexInputId("id=sex"),
        DateOfBirthId("id=dateofbirth"),
        BirthPlaceId("id=s2id_birthPlace"),
        BirthPlaceInputXpath("xpath=//*[@id=\"select2-drop\"]/div/input"),
        BirthPlaceConfirmXpath("xpath=//*[@id=\"select2-drop\"]/ul/li[2]"),
        //*[@id="select2-drop"]/div/input
        KinPhoneNumberId("id=phonenumber"),
        NextOfKinId("id=nextofkin"),
        UnNameButtonXpath("xpath=/html/body/footer/div/button[1]"),
        SaveButtonXpath("xpath=/html/body/footer/div/button[2]"),
        RefreshButtonXpath("xpath=/html/body/footer/div/button[3]"),
        CloseButtonXpath("xpath=/html/body/footer/div/button[4]"),
        ContinueSaveId("id=continueSave"),
        CloseHintXpath("xpath=/html/body/div[6]/div/h5/button");

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

    private static String[] IdNumber;
    private static String[] PatientId;
    private static String[] Name;
    private static String[] Sex;
    private static String[] BirthDate;
    private static String[] BirthPlace;
    private static String[] KinPhoneNumber;
    private static String[] NextOfKinName;
    private static int LoopNumber = 10;
    private static int FinishNumber = 700;
    private static Selenium SeleniumWebDriver;

    public PatientMasterIndex(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }

    //无参构造函数，对患者信息进行准备工作，从86上获取数据填写到对饮建立主索引页面中
    public PatientMasterIndex() {
        PatientId = new String[LoopNumber];
        Name = new String[LoopNumber];
        Sex = new String[LoopNumber];
        BirthPlace = new String[LoopNumber];
        BirthDate = new String[LoopNumber];
        KinPhoneNumber = new String[LoopNumber];
        NextOfKinName = new String[LoopNumber];
        try {
            perparePatientData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //填入患者信息，其中每个语句的执行步骤很重要，因为建立主索引页面对键盘控制很是严格，这会导致你的定位和程序的自动定位冲突，
    // 这样就导致定位不安全，所以要遵循程序在对应页面的定位顺序，这样才会保证你的定位和程序自动定位不冲突
    public void run() throws InterruptedException {
        for (int i = 0; i < LoopNumber; ++i) {

            sendKeys(Location.PatientIdId.getString(), "\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            sendKeys(Location.SecurityTypeId.getString(), "\n");
            sendKeys(Location.IdNumberInputId.getString(), "\n");
            Thread.sleep(500);

            click(Location.NameInputId.getString());
            sendKeys(Location.NameInputId.getString(), Name[i] + "\n");

            sendKeys(Location.NamePhoneticId.getString(), "\n");

            if (i % 2 == 0) {
                click("xpath=//*[@id=\"inside-id\"]/div/div[1]/table/tbody/tr[2]");
            } else {
                click("xpath=//*[@id=\"inside-id\"]/div/div[1]/table/tbody/tr[3]");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clear(Location.DateOfBirthId.getString());
            sendKeys(Location.DateOfBirthId.getString(), "\n");

            Thread.sleep(1000);

            click(Location.BirthPlaceId.getString());

            sendKeys(Location.BirthPlaceInputXpath.getString(), "YMSWSZZM");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            click(Location.BirthPlaceConfirmXpath.getString());

            sendKeys(Location.KinPhoneNumberId.getString(), KinPhoneNumber[i]);

            sendKeys(Location.NextOfKinId.getString(), NextOfKinName[i]);

            click(Location.SaveButtonXpath.getString());

            if (elementExist(Location.ContinueSaveId.getString())) {
                click(Location.ContinueSaveId.getString());
            } else {

            }

            closeHint(Location.CloseHintXpath.getString());

            Thread.sleep(1000);

            click(Location.RefreshButtonXpath.getString());

            LOGGER.info("使用的患者数量：" + i);

            Thread.sleep(1000);
        }
    }

    //建立主索引的准备数据，从86数据库中获取主索引数据中的必要信息
    public static void perparePatientData() throws ClassNotFoundException, SQLException {
        String user = "heren";
        String password = "heren";
        String url = "jdbc:oracle:thin:@10.0.30.64:1521:orcl";

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(url, user,
                password);
        Statement stmt = con.createStatement();

        ResultSet resultSet = stmt.executeQuery("select * from pat_master_index t where t.name is not null and" +
                " t.sex is not null and t.date_of_birth is not null and t.birth_place_code is not null and" +
                " t.next_of_kin is not null and t.next_of_kin_phone is not null ORDER BY t.patient_id");
        int i;
        for (int j = 0; j < FinishNumber && resultSet.next(); ++j) {
            resultSet.getString("name");
        }
        System.out.println("已经录入的患者数量" + FinishNumber);
        for (i = 0; i < LoopNumber && resultSet.next(); ++i) {
            PatientId[i] = resultSet.getString("patient_id");
            System.out.println(PatientId[i]);
            Name[i] = resultSet.getString("name");
            System.out.println(Name[i]);
            Sex[i] = resultSet.getString("sex");
            System.out.println(Sex[i]);
            //对出生日期字段需要做特殊处理，因为从数据库中输出的字段类型为 yyyy-mm-dd而向页面中输入的数据类型为yyyymmdd所以要对字符串进行更改
            String birthDateTemp;
            birthDateTemp = resultSet.getString("date_of_birth");
            BirthDate[i] = transformDateString(birthDateTemp);
            System.out.println(BirthDate[i]);
            //地址信息基本没用，所以在填写患者信息时我给患者填入了一个固定值，呵呵
            BirthPlace[i] = resultSet.getString("birth_place_code");
            System.out.println(BirthPlace[i]);
            NextOfKinName[i] = resultSet.getString("next_of_kin");
            System.out.println(NextOfKinName[i]);
            KinPhoneNumber[i] = resultSet.getString("next_of_kin_phone");
            System.out.println(KinPhoneNumber[i]);
            System.out.println();
        }
        resultSet.close();
        con.close();
    }

    //转换对应日期字符串，可以将数据库输出的日期格式转换为页面输入格式
    //yyyy-mm-dd -> yyyymmdd
    public static String transformDateString(String string) {
        String year = string.substring(0, 4);
        String month = string.substring(5, 7);
        String day = string.substring(8, 10);
        System.out.println(year);
        System.out.println(month);
        String date = year + month + day;
        System.out.println(date);
        return date;
    }
}
