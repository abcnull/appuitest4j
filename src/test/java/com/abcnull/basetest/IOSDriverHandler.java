package com.abcnull.basetest;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;

/**
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/2 18:22
 */
public class IOSDriverHandler extends DriverHandler {
    @Override
    public MobileDriver<WebElement> startMobile(String platformName, String udid, String appPackage, String appActivity, String automationName) {
        return null;
    }

    @Override
    public MobileDriver<WebElement> startMobile(String platformName, String udid, String appPackage, String appActivity, String automationName, String remoteIP, String remotePort) {
        return null;
    }
}
