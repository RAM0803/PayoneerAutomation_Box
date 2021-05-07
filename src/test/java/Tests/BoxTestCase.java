package Tests;

import Core.BaseTest;
import Core.Core;
import Core.KeyValue;
import Pages.Box_PageModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

import java.util.Random;

public class BoxTestCase extends BaseTest {

    @Test
    public void Login_UploadFile() throws IOException, AWTException, InterruptedException {
        String userName = KeyValue.getGeneralProperty("Box_EmailId");
        String pswd = Core.decodePswd(KeyValue.getGeneralProperty("Box_Password"));
        String fileName = "TESTpdf.pdf";
        driver.navigate().to("https://www.box.com/");
        Box_PageModel box = new Box_PageModel();
        box.clickLogin();

        // After logging in, we will verify whether we have landed on correct page or not. If not testcase will get failed
        Assert.assertTrue(box.enterEmailAndPswd(userName, pswd));
        Random rand = new Random();
        String folderName = "Folder_"+rand.nextInt();

        box.createNewFolder(folderName, Box_PageModel.Access.viewer);

        //Verifying folder created or not
        Assert.assertTrue(box.verifyFolderCreated(folderName));

        box.uploadFileIntoFolder(folderName, fileName);

        //Verifying whether file uploaded
        Assert.assertTrue(box.checkWhetherFileUploaded(fileName));

        box.logOut();

        //Verifying whether successfully loggedout
        Assert.assertEquals(driver.getCurrentUrl(), "https://account.box.com/login");

    }

}
