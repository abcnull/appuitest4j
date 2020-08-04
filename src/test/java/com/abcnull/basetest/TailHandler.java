package com.abcnull.basetest;

import com.abcnull.exception.PlatformException;
import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;

/**
 * 责任链-尾结点
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/2 18:22
 */
public class TailHandler extends DriverHandler {
    /**
     * 尾结点处理
     *
     * @param platformName   手机系统：Android/IOS
     * @param udid           设备唯一标识符
     * @param appPackage     app 包名
     * @param appActivity    app Activity
     * @param automationName automation name
     * @return null
     */
    @Override
    public MobileDriver<WebElement> startMobile(String platformName, String udid, String appPackage, String appActivity, String automationName) {
        throw new PlatformException("platformName 未匹配到！");
    }

    /**
     * 尾结点处理
     *
     * @param platformName   手机系统：Android/IOS
     * @param udid           设备唯一标识符
     * @param appPackage     app 包名
     * @param appActivity    app Activity
     * @param automationName automation name
     * @param remoteIP       远端 ip
     * @param remotePort     端口
     * @return null
     */
    @Override
    public MobileDriver<WebElement> startMobile(String platformName, String udid, String appPackage, String appActivity, String automationName, String remoteIP, String remotePort) {
        throw new PlatformException("platformName 未匹配到！");
    }
}
