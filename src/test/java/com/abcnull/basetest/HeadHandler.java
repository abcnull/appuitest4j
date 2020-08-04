package com.abcnull.basetest;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

/**
 * 责任链-头结点
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/2 18:22
 */
public class HeadHandler extends DriverHandler {
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
        return next.startMobile(platformName, udid, appPackage, appActivity, automationName);
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
        return next.startMobile(platformName, udid, appPackage, appActivity, automationName, remoteIP, remotePort);
    }
}
