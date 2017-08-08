import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class LoginRMSysTest extends TestBase {
    @Test
    public void fillingLoginForm() throws IOException {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File("screenshots/img.png"));
        Assert.assertEquals(driver.getTitle(), "RMSys - Sign In");
    }
}

