import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {
    //WebDriver driver;
    private static final String URL = "https://192.168.100.26";
    private static final String PHANTOM_JS_PATH = "src/main/resources/phantomjs-2.1.1-windows/bin/phantomjs.exe";
    static PhantomJSDriver driver;

    public PhantomJSDriver createPhantomDriver(DesiredCapabilities dcaps) {
        dcaps.setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                PHANTOM_JS_PATH);
        dcaps.setCapability("takesScreenshot", true);
        String[] phantomJsArgs = {"--ignore-ssl-errors=true"};
        dcaps.setCapability(
                PhantomJSDriverService.PHANTOMJS_GHOSTDRIVER_CLI_ARGS,
                phantomJsArgs);
        return new PhantomJSDriver(dcaps);
    }

    @BeforeMethod
    public void browserSetup() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);
        driver = createPhantomDriver(capabilities);
        //driverForPhantomJS = new PhantomJSDriver(capabilities);
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        //takeScreenshot("loginPage");
    }

    @AfterMethod
    public void browserClose() {
        driver.quit();
    }
}
