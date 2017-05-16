package com.heren.his.doctorstation;

import com.heren.his.commons.HerenTest;
import org.openqa.selenium.Keys;

/**
 * Created by joshua on 2015/11/4.
 */
public class OutpDoctorStation extends HerenTest {

    private static enum Location {
        MrXpath("xpath=/html/body/ng-view/div/article/ul/li[3]"),
        //定义对应各个页面的Xpath
        OverViewXpath("xpath=/html/body/ng-view/div/article/ul/li[1]"),
        VisitHistoryXpath("xpath=/html/body/ng-view/div/article/ul/li[2]"),
        CaseHisXpath("xpath=/html/body/ng-view/div/article/ul/li[3]"),
        OrdersXpath("xpath=/html/body/ng-view/div/article/ul/li[4]"),
        LabReportXpath("xpath=/html/body/ng-view/div/article/ul/li[5]"),
        PastHistoryXpath("xpath=/html/body/ng-view/div/article/ul/li[6]"),
        ExternalDataXpath("xpath=/html/body/ng-view/div/article/ul/li[7]"),
        ApplyXpath("xpath=/html/body/ng-view/div/article/ul/li[8]"),
        RegisterXpath("xpath=/html/body/ng-view/div/article/ul/li[9]"),
        //定义各个医嘱页面的Xpath
        OrderDrugWestXpath("xpath=//*[@id=\"outpOrdersMain\"]/section[3]/ul/li[1]"),
        OrderDrugTCMXpath("xpath=//*[@id=\"outpOrdersMain\"]/section[3]/ul/li[2]"),
        OrderExamXpath("xpath=//*[@id=\"outpOrdersMain\"]/section[3]/ul/li[3]"),
        OrderLabXpath("xpath=//*[@id=\"outpOrdersMain\"]/section[3]/ul/li[4]"),
        OrderTreadXpath("xpath=//*[@id=\"outpOrdersMain\"]/section[3]/ul/li[5]"),
        OrderNurseXpath("xpath=//*[@id=\"outpOrdersMain\"]/section[3]/ul/li[6]"),
        OrderInjectionXpath("xpath=//*[@id=\"outpOrdersMain\"]/section[3]/ul/li[7]"),

        ChiefComplaintXpath("xpath=//*[@id=\"outpMrMain\"]/div/div[1]/div/div/textarea"),
        PresentIllnessXpath("xpath=//*[@id=\"outpMrMain\"]/div/div[3]/div/div/textarea"),
        MrSubmitXpath("xpath=//*[@id=\"outpMrMain\"]/footer/button[1]"),
        CloseFinishHintXpath("xpath=/html/body/div[6]/div/div[2]/button"),

        //已诊、未诊选项
        PatientNotOverStatusXpath("xpath=//*[@id=\"pat-list-aside\"]/header/div[1]/div/form/label[1]/input"),
        PatientOverStatusXpath("xpath=//*[@id=\"pat-list-aside\"]/header/div[1]/div/form/label[2]/input"),
        SearchPatientId("id=searchPatientId"),

        //患者列表中的各个按钮
        ExtentPatientListXpath("xpath=//*[@id=\"pat-list-aside\"]/footer/div/button[1]"),
        CallPatientXpath("xpath=//*[@id=\"pat-list-aside\"]/footer/div/button[2]"),
        RefreshPatientListXpath("xpath=//*[@id=\"pat-list-aside\"]/footer/div/button[3]"),
        OpenPatientXpath("xpath=//*[@id=\"pat-list-aside\"]/footer/div/button[4]"),

        //打开看诊页面后选中对应号别的Xpath
        ClinicListXpath("xpath=/html/body/div[1]/div[2]/div[2]/table/tbody/tr["),
        PatientListButtonId("id=pat-list-btn"),
        //*[@id="patientList"]/aside/div[2]
        //*[@id="patientList"]/aside/div[3]
        PatientListButtonXpath("xpath=//*[@id=\"patientList\"]/aside/div[3]"),
        PatientInHospitalXpath("xpath=/html/body/div[6]/div/div/div[2]/button"),
        ChooseClinicSubmitButtonXpath("xpath=/html/body/div[1]/div[3]/button"),
        DrugInfoInputId("id=drugInfoInputCode"),
        DrugInfoAppearPerfixXpath("xpath=//*[@id=\"leftSearch\"]/table/tbody/tr["),
        DrugStorePerfixXpath("xpath=//*[@id=\"otherDrugInfo\"]/tbody/tr["),
        DrugTotalAmountId("id=totalAmount"),

        //病历页面对应定位信息
        PatientComplainXpath("xpath=//*[@id=\"outpMrMain\"]/div/div[1]/div/div/textarea"),
        NowDiseaseHistoryXpath("xpath=//*[@id=\"outpMrMain\"]/div/div[3]/div/div/textarea"),
        DiagnosisElementId("id=diagnosisName0"),
        SaveOutMrXpath("xpath=//*[@id=\"outpMrMain\"]/footer/button[1]"),
        CloseSaveMrHintXpath("xpath=/html/body/div[6]/div/div[2]/button"),

        //医嘱页面对应定位信息
        WestDrugInfoInputId("id=drugInfoInputCode"),
        WestDrugDosageId("id=dosage"),
        WestDrugTotalAmountId("id=totalAmount"),
        WestDrugAddOrderId("id=addOrder"),
        WestDrugSaveHintXpath("xpath=/html/body/div[6]/div/div[2]/button"),
        WestDrugCancelPrintXpath("xpath=/html/body/div[6]/div/div[2]/button[2]"),
        SaveOrderXpath("xpath=//*[@id=\"outpOrdersMain\"]/footer/div/button[2]"),
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


    public OutpDoctorStation(String _userName, String _password, String _workstation, int _menuGroup, int _menu) {
        super(_userName, _password, _workstation, _menuGroup, _menu);
    }

    public void openClinic(int clinicNumber){
        String clinicString = Location.ClinicListXpath.getString() + String.valueOf(clinicNumber) + "]";
        doubleClinic(clinicString);
    }

    public void choosePatient(String patientId){
        clear(Location.SearchPatientId.getString());
        sendKeys(Location.SearchPatientId.getString(),patientId+"\n");
        try {
            click(Location.OpenPatientXpath.getString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void reChoosePatient(String patientId){
        click(Location.PatientListButtonXpath.getString());
        choosePatient(patientId);
    }

    public void openCaseHis(){
        click(Location.CaseHisXpath.getString());
    }

    //主诉
    public void patientComplain(String patientComplainString){
        clear(Location.PatientComplainXpath.getString());
        sendKeys(Location.PatientComplainXpath.getString(),patientComplainString);
    }

    //现病史
    public void patientDiseaseHistory(String patientDiseaseHistoryString){
        clear(Location.NowDiseaseHistoryXpath.getString());
        sendKeys(Location.NowDiseaseHistoryXpath.getString(),patientDiseaseHistoryString);
    }

    //诊断
    public void patientDiagnosis(String diagnosisString){
        clear(Location.DiagnosisElementId.getString());
        sendKeys(Location.DiagnosisElementId.getString(),diagnosisString);
    }

    //保存对应病历信息
    public void savePatientCase(String patientComplainString,String patientDiseaseString,String diagnosisString){
        patientComplain(patientComplainString);
        patientDiseaseHistory(patientDiseaseString);
        patientDiagnosis(diagnosisString);
        click(Location.SaveOutMrXpath.getString());
        closeHint(Location.CloseSaveMrHintXpath.getString());
    }

    public void openDoctorOrder(){
        click(Location.OrdersXpath.getString());
    }

    public void openWestDrugOrder(){
        click(Location.OrderDrugWestXpath.getString());
    }

    public void prescribeWestDrug(String drugString,int doses,int total){
        openDoctorOrder();
        openWestDrugOrder();

        clear(Location.WestDrugInfoInputId.getString());
        sendKeys(Location.WestDrugInfoInputId.getString(),drugString);
        sendKeys(Location.WestDrugInfoInputId.getString(),"\n");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pageShortKeys(Keys.ENTER);
        clear(Location.WestDrugDosageId.getString());
        sendKeys(Location.WestDrugDosageId.getString(),String.valueOf(doses));
        clear(Location.WestDrugTotalAmountId.getString());
        sendKeys(Location.WestDrugTotalAmountId.getString(),String.valueOf(total));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(Location.WestDrugAddOrderId.getString());

    }

    public void inputWestDrug(String drugString){
        clear(Location.WestDrugInfoInputId.getString());
        sendKeys(Location.WestDrugInfoInputId.getString(),drugString);
    }

    public void chooseDrug(){

    }

    public void saveOrderClick(){
        click(Location.SaveOrderXpath.getString());

        closeHint(Location.WestDrugSaveHintXpath.getString());
        closeHint(Location.WestDrugCancelPrintXpath.getString());
    }
}
