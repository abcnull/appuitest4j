package com.abcnull.basepage;

import com.abcnull.util.PropertiesReader;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

/**
 * BaseApp
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/1 23:28
 */
public class BaseApp {
    /**
     * 驱动
     */
    protected MobileDriver<WebElement> driver;

    /**
     * 动作
     */
    protected TouchAction action;

    /**
     * 显示等待
     */
    protected WebDriverWait wait;

    /**
     * 构造器
     *
     * @param driver 驱动
     */
    public BaseApp(MobileDriver<WebElement> driver) {
        this.driver = driver;
        this.action = new TouchAction(driver);
        // 显示等待时长
        long timeout = Long.parseLong(PropertiesReader.getKey("driver.timeouts.webDriverWait"));
        this.wait = new WebDriverWait(driver, timeout);
    }

    /*============================== 基本元素操作 ==============================*/

    /**
     * 通过元素定位拿到 Element 元素对象
     *
     * @param locator By 类型元素定位
     * @return 定位到的元素
     */
    public WebElement locateElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * 点击元素
     *
     * @param locator By 类型元素定位
     * @return 点击的元素
     */
    public WebElement clickButton(By locator) {
        WebElement buttonElement = locateElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        buttonElement.click();
        return buttonElement;
    }

    /**
     * 输入框输入数据
     *
     * @param locator By 类型元素定位
     * @param content 输入的内容，支持多内容，可以键盘输入
     * @return 输入框元素
     */
    public WebElement sendInput(By locator, CharSequence... content) {
        WebElement inputElement = locateElement(locator);
        inputElement.clear();
        inputElement.sendKeys(content);
        return inputElement;
    }

    /*============================== 切换 WebView ==============================*/

    /**
     * 切换到 webview 页面
     *
     * @return MobileDriver
     */
    public MobileDriver<WebElement> switchWebView() {
        driver.getContextHandles().forEach((context) -> {
            if (context.contains("WEBVIEW")) {
                driver.context(context);
            }
        });
        return driver;
    }

    /**
     * 切换到下一个窗口
     *
     * @return MobileDriver
     */
    public MobileDriver<WebElement> switchNextWindow() {
        // 当前窗口句柄
        String currentHandle = driver.getWindowHandle();
        // 所有窗口句柄
        Set<String> allHandlesSet = driver.getWindowHandles();
        for (String window : allHandlesSet) {
            if (!currentHandle.equals(window)) {
                driver.switchTo().window(window);
            }
        }
        return driver;
    }

    /**
     * 退出 webview 页面
     *
     * @return MobileDriver
     */
    public MobileDriver<WebElement> switchOutWebView() {
        driver.getContextHandles().forEach((context) -> {
            if (context.contains("NATIVE_APP")) {
                driver.context(context);
            }
        });
        return driver;
    }

    /*============================== 页面滑动操作 ==============================*/

    /**
     * 通过坐标滑动页面
     */
    public void slideScreen(int fromX, int fromY, int toX, int toY) {
        PointOption fromOption = PointOption.point(fromX, fromY);
        PointOption toOption = PointOption.point(toX, toY);
        action.press(fromOption).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10)))
                .moveTo(toOption)
                .release().perform();
    }

    // todo : 页面中其他的最基本操作，可自行封装
}
