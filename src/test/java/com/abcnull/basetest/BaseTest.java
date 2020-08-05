package com.abcnull.basetest;

import com.abcnull.pageobject.page.LoginPage;
import com.abcnull.util.PropertiesReader;
import com.abcnull.util.WordartDisplayer;
import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * BaseTest
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/1 23:26
 */
public class BaseTest {
    /**
     * 驱动基类
     */
    private BaseDriver baseDriver;

    /**
     * 驱动
     * 对外暴露
     */
    public MobileDriver<WebElement> driver;

    /**
     * 执行一个测试套之前执行
     * 进行测试配置文件的读取工作
     * 由于 BeforeSuite 不会多线程去执行，因此对于配置文件读取未使用线程安全的操作
     *
     * @param propertiesPath 整个项目的测试配置文件相对于项目的路径
     * @throws IOException IOException
     */
    @BeforeSuite(alwaysRun = true)
    @Parameters({"propertiesPath"})
    public void beforeSuite(@Optional("src/test/resources/config/config.properties") String propertiesPath) throws IOException {
        // 显示文字 appuitest4j
        WordartDisplayer.display();
        // 配置文件读取
        PropertiesReader.readProperties(propertiesPath);
        // todo : 这里可以自己定制其他工具初始化操作（看需要）
    }

    /**
     * @param platformName   手机系统：Android/IOS
     * @param udid           设备唯一标识符
     * @param appPackage     app 包名
     * @param appActivity    app Activity
     * @param automationName automation name
     * @param remoteIP       远端 ip
     * @param remotePort     端口
     * @throws MalformedURLException URL
     */
    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName", "udid", "appPackage", "appActivity", "automationName", "remoteIP", "remotePort"})
    public void beforeTest(@Optional("Android") String platformName, @Optional("3a789767") String udid,
                           @Optional("net.csdn.csdnplus") String appPackage, @Optional(".activity.MainActivity") String appActivity,
                           @Optional("UiAutomator2") String automationName, @Optional("127.0.0.1") String remoteIP,
                           @Optional("4723") String remotePort) throws MalformedURLException {
        /* 驱动配置 */
        baseDriver = new BaseDriver();
        driver = baseDriver.startApp(platformName, udid, appPackage, appActivity, automationName, remoteIP, remotePort);
        // todo : 由于线程隔离设为 test，这里可以通过 new 一个对象来达到线程隔离的效果，可以做其他的扩展定制（看需要）
        /* todo : 登录操作可以放在这里（看需要）
         * LoginPage loginPage = new LoginPage(driver);
         * loginPage.loginByUI();
         */
    }

    /**
     * 执行一个测试用例中的类方法之前执行
     */
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        // todo : 登录操作可以放在这里（看需要）
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPage();
        loginPage.loginByUI();
    }

    /**
     * 执行一个测试用例中的类方法之后执行
     */
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        // todo : 登录的注销或其他操作可以放在这里（看需要）
    }

    /**
     * 执行一个测试用例之后执行
     */
    @AfterTest(alwaysRun = true)
    public void afterTest() {
        // 驱动退出关闭浏览器
        baseDriver.closeBrowser();
        // todo : 其他工具的释放操作（看需要）
    }

    /**
     * 执行一个测试套之后执行
     */
    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        // todo : 可自己定制（看需要）
    }
}
