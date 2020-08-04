package com.abcnull.basetest;

import com.abcnull.util.PropertiesReader;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 责任链-IOS
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/2 18:22
 */
public class IOSDriverHandler extends DriverHandler {
    /**
     * 运行本地
     *
     * @param platformName   手机系统：Android/IOS
     * @param udid           设备唯一标识符
     * @param appPackage     app 包名
     * @param appActivity    app Activity
     * @param automationName automation name
     * @return MobileDriver
     * @throws MalformedURLException URL
     */
    @Override
    public MobileDriver<WebElement> startMobile(String platformName, String udid, String appPackage, String appActivity, String automationName) throws MalformedURLException {
        if (!platformName.equals("IOS")) {
            return next.startMobile(platformName, udid, appPackage, appActivity, automationName);
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("automationName", automationName);
        // noRest
        capabilities.setCapability("noReset", true);
        // webview 测试的 chromedriver
        String chromePath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.chromeDriver");
        capabilities.setCapability("chromedriverExecutable", chromePath);
        // 通过本地 appium 服务开启 driver
        URL url = new URL("http://localhost:4723/wd/hub");
        return new AndroidDriver<>(url, capabilities);
    }

    /**
     * 运行远端
     *
     * @param platformName   手机系统：Android/IOS
     * @param udid           设备唯一标识符
     * @param appPackage     app 包名
     * @param appActivity    app Activity
     * @param automationName automation name
     * @param remoteIP       远端 ip
     * @param remotePort     端口
     * @return MobileDriver
     * @throws MalformedURLException URL
     */
    @Override
    public MobileDriver<WebElement> startMobile(String platformName, String udid, String appPackage, String appActivity, String automationName, String remoteIP, String remotePort) throws MalformedURLException {
        if (!platformName.equals("IOS")) {
            return next.startMobile(platformName, udid, appPackage, appActivity, automationName, remoteIP, remotePort);
        }
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("udid", udid);
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("automationName", automationName);
        // noRest
        capabilities.setCapability("noReset", true);
        // webview 测试的 chromedriver
        String chromePath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.chromeDriver");
        capabilities.setCapability("chromedriverExecutable", chromePath);
        // 通过远端 appium 服务开启 driver
        URL url = new URL("http://" + remoteIP + ":" + remotePort + "/wd/hub/");
        return new IOSDriver<>(url, capabilities);
    }
}
