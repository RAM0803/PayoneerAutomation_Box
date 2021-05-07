package Pages;

import Core.Core;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class Box_PageModel {

    public enum Access {
        viewer,
        editor;
    }

    private By loginBtn_MainPage = By.linkText("Log in");
    private By emailAddressTxtBox = By.id("login-email");
    private By nextBtn = By.id("login-submit");
    private By passwordTxtBox = By.id("password-login");
    private By loginBtn = By.id("login-submit-password");
    private By leftPane = By.className("ScrollbarsCustom-Content");
    private By createAFolderBtn = By.xpath("//span[text()='Create a folder']");
    private By uploadAFileBtn = By.xpath("//span[text()='Upload a file']");
    private By folderOptionBtn = By.xpath("//span[text()='Folder']");
    private By upload_File = By.xpath("//span[text()='File']");

    private By newBtn = By.xpath("//button[contains(@class,'btn create-dropdown-menu-toggle-button')]");
    private By folderNameTxtBox = By.name("folder-name");
    private By invitePermission = By.name("invite-permission");
    private By inviteAdditionalPPlTxtBox = By.xpath("//textarea[@placeholder='Enter email addresses to invite users']");
    private By createBtn = By.xpath("//span[text()='Create']");
    private By uploadBtn = By.xpath("//span[text()='Upload']");
    private By profileBtn = By.xpath("//span[@role = 'presentation']//span[contains(text(),'RK')]");
    private By logOutBtn = By.xpath("//span[text()='Log Out']");

    public void clickLogin(){
        Core.standardClick(loginBtn_MainPage);
    }

    public boolean enterEmailAndPswd(String emailId, String pswd){
        Core.clearThenSetText(emailAddressTxtBox, emailId);
        Core.standardClick(nextBtn);
        Core.clearThenSetText(passwordTxtBox, pswd);
        Core.standardClick(loginBtn);
        return Core.getVisibilityOfElement(leftPane);
    }

    public void createNewFolder(String folderName, Access access){
        Core.standardClick(newBtn);
        Core.standardClick(folderOptionBtn);
        Core.clearThenSetText(folderNameTxtBox,folderName);
        if(access == Access.editor)
            Core.selectOptionByText(invitePermission,"Editor");
        else
            Core.selectOptionByText(invitePermission,"Viewer");
        Core.standardClick(createBtn);
    }

    public boolean verifyFolderCreated(String folderName){
        return Core.getVisibilityOfElement(By.xpath("//div[@class = 'item-name']//a[contains(text(), '"+folderName+"')]"));
    }

    public void uploadFileIntoFolder(String createdFolderName, String fileName) throws AWTException, InterruptedException {
        Core.standardClick( By.xpath("//div[@class = 'item-name']//a[contains(text(), '"+createdFolderName+"')]"));
        Core.standardClick(uploadBtn);
        Core.standardClick(upload_File);
        String path = System.getProperty("user.dir") + "\\src\\resources\\" + fileName;
        StringSelection ss = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        //native key strokes for CTRL, V and ENTER keys
        Robot robot = new Robot();
        Thread.sleep(5000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public boolean checkWhetherFileUploaded(String fileName){
        return Core.getVisibilityOfElement(By.xpath("//a[@data-resin-target = 'openfile' and contains(text(), '"+fileName+"')]"));
    }

    public void logOut() throws InterruptedException {
        Thread.sleep(3000);
        Core.standardClick(profileBtn);
        Core.standardClick(logOutBtn);
    }

}
