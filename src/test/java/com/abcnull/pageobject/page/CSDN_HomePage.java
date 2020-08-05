package com.abcnull.pageobject.page;

import com.abcnull.basepage.BasePage;
import com.abcnull.pageobject.locator.CSDN_HomeLocator;
import io.appium.java_client.MobileDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * CSDN 首页操作
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/4 15:36
 */
@Slf4j
public class CSDN_HomePage extends BasePage {
    /**
     * 构造器
     *
     * @param driver 驱动
     */
    public CSDN_HomePage(MobileDriver<WebElement> driver) {
        super(driver);
    }

    /**
     * 点击关注按钮操作
     */
    public void clickFocus() {
        log.info("点击首页的关注按钮");
        clickButton(CSDN_HomeLocator.focusBtn);
        Assert.assertTrue(true);
    }
}
