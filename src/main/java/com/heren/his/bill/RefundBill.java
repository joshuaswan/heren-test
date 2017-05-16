package com.heren.his.bill;

import com.heren.his.commons.HerenTest;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * Created by lisen on 2016/3/30.
 */
public class RefundBill extends HerenTest{
    private static enum Location{
        RecipeNoInputId("id=rcpt-no-input"),
        RefundSettleButtonXpath("xpath=/html/body/footer/button[1]"),
        ConfirmRefundHintXpath("xpath=/html/body/div[1]/div[3]/button[1]"),
        RefundRecipeHintXpath("xpath=/html/body/div[6]/div/div/div[2]/button"),
        RecipeHintMessageXpath("xpath=/html/body/div[6]/div/div"),
        IframeXpath("xpath=/html/body/div[1]/section/iframe[2]"),
        RePrintRecipeButtonXpath("xpath=/html/body/footer/button[2]");
        private String string;
        private Location(String s){this.string=s;}
        public String getString(){return string;}
        public void setString(String str){this.string=str;}
    }

    private boolean refundFlag=true;

    public RefundBill(String _userName,String _password,String _workstation,int _menuGroup,int _menu){
        super(_userName,_password,_workstation,_menuGroup,_menu);
    }

    public boolean selectRecipeInfo(String recipeNo){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(Location.RecipeNoInputId.getString(),recipeNo+"\n");
        refundFlag=judgeRefundHintMessage(Location.RecipeHintMessageXpath.getString());
        if(refundFlag){
            return false;
        }else {
            return true;
        }
    }

    public void fullReRefundSettle() throws InterruptedException {
        click(Location.RefundSettleButtonXpath.getString());
        Thread.sleep(1000);
        click(Location.ConfirmRefundHintXpath.getString());
        refundFlag=judgeRefundHintMessage(Location.RecipeHintMessageXpath.getString());
        if(refundFlag){
            closeHint(Location.RefundRecipeHintXpath.getString());
            return;
        }
    }

    public void reprintRecipe(){
        refundFlag=judgeRefundHintMessage(Location.RecipeHintMessageXpath.getString());
        if(refundFlag){
            closeHint(Location.RefundRecipeHintXpath.getString());
            return;
        }
        click(Location.RePrintRecipeButtonXpath.getString());
    }

    public boolean judgeRefundHintMessage(String str){
        driver.switchTo().defaultContent();
        if(elementExist(str)){
            try {
                click(Location.RefundRecipeHintXpath.getString());
            }catch (NoSuchElementException e){
                e.printStackTrace();
            }
            driver.switchTo().frame(herenFindElement(Location.IframeXpath.getString()));
            return true;
        }else {
            driver.switchTo().frame(herenFindElement(Location.IframeXpath.getString()));
            return false;
        }
    }

    public void ReprintRecipeTest() throws InterruptedException {
        if (selectRecipeInfo("160329000006")) {
            fullReRefundSettle();
        } else {
            System.out.println("Refund failed");
        }

    }

}
