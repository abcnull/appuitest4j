package com.abcnull.basetest;

import com.abcnull.util.PropertiesReader;
import com.abcnull.util.WordartDisplayer;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;

/**
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

    @BeforeTest(alwaysRun = true)
    @Parameters({"platformName", "udid", "appPackage", "appActivity", "automationName", "remoteIP", "remotePort"})
    public void beforeTest(@Optional("Android") String platformName, @Optional("udid") String udid,
                           @Optional("appPackage") String appPackage, @Optional("appActivity") String appActivity,
                           @Optional("automationName") String automationName, @Optional("remoteIP") String remoteIP,
                           @Optional("remotePort") String remotePort) throws MalformedURLException {
        /* 驱动配置 */
        baseDriver = new BaseDriver();
        driver = baseDriver.startApp(platformName, udid, appPackage, appActivity, automationName, remoteIP, remotePort);
        // todo : 由于线程隔离设为 test，这里可以通过 new 一个对象来达到线程隔离的效果，可以做其他的扩展定制（看需要）
    }
}
