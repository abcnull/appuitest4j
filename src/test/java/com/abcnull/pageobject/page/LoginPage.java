package com.abcnull.pageobject.page;

import com.abcnull.basepage.BasePage;
import io.appium.java_client.MobileDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

/**
 * 登录页面操作
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/4 14:31
 */
@Slf4j
public class LoginPage extends BasePage {
    /**
     * 构造器
     *
     * @param driver 模拟器驱动
     */
    public LoginPage(MobileDriver<WebElement> driver) {
        super(driver);
    }

    /**
     * 进入登录页面
     */
    public void enterPage() {
        log.info("点击进入登录页面");
        // todo : 点击操作
    }

    /**
     * UI 点击的方式登录
     */
    public void loginByUI() {
        // todo : UI 方式登录
    }

    /**
     * API 访问登录接口实现登陆
     * 常用 HttpClient
     */
    public void loginByAPI() {
        // todo : API 方式登录
    }

    /**
     * 使用已知的 cookie/sessionid/access_token 实现登录
     */
    public void loginBySession() {
        // todo : 使用已知的 cookie/sessionid/access_token 登录
    }
}
