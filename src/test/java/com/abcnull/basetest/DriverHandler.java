package com.abcnull.basetest;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

/**
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/2 18:21
 */
public abstract class DriverHandler {
    /**
     * 后继 DriverHandler 结点
     */
    public DriverHandler next;

    /**
     * 判断启动本地或远端
     *
     * @param platformName   手机系统：Android/IOS
     * @param udid           设备唯一标识符
     * @param appPackage     app 包名
     * @param appActivity    app Activity
     * @param automationName automation name
     * @param remoteIP       远端 ip
     * @param remotePort     端口
     * @return MobileDriver
     */
    public MobileDriver<WebElement> start(String platformName, String udid, String appPackage, String appActivity, String automationName, String remoteIP, String remotePort) throws MalformedURLException {
        // 通过 remoteIP 是不是空来判定在本地还是远端运行
        if (remoteIP == null || remoteIP.isEmpty()) {
            return startMobile(platformName, udid, appPackage, appActivity, automationName);
        } else {
            return startMobile(platformName, udid, appPackage, appActivity, automationName, remoteIP, remotePort);
        }
    }

    /**
     * 运行本地
     *
     * @param platformName   手机系统：Android/IOS
     * @param udid           设备唯一标识符
     * @param appPackage     app 包名
     * @param appActivity    app Activity
     * @param automationName automation name
     * @return MobileDriver
     */
    public abstract MobileDriver<WebElement> startMobile(String platformName, String udid, String appPackage, String appActivity, String automationName) throws MalformedURLException;

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
     */
    public abstract MobileDriver<WebElement> startMobile(String platformName, String udid, String appPackage, String appActivity, String automationName, String remoteIP, String remotePort) throws MalformedURLException;

    /**
     * 后继结点赋值
     *
     * @param next 后继结点
     */
    public DriverHandler setNext(DriverHandler next) {
        this.next = next;
        return this.next;
    }

    ;
}
