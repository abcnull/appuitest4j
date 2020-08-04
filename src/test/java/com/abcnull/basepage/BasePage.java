package com.abcnull.basepage;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebElement;

/**
 * BasePage
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/1 23:26
 */
public class BasePage extends BaseApp {
    /**
     * 构造器
     *
     * @param driver 驱动
     */
    public BasePage(MobileDriver<WebElement> driver) {
        super(driver);
    }

    /*============================== 页面可共用的操作 ==============================*/

    /**
     * 很多 app 项目采用了一些框架，页面各个模块比较相似，因此这里设立了一个 BasePage 类型来存放公用的操作方法
     * BasePage 与 BaseApp 不同的是 BasePage 存放各模块页面可以公用的操作，BaseApp 只存放最基本的页面操作
     * <p>
     * function1
     */
    public void function1() {
        // todo : 某项公用操作 1（自己封装）
    }

    /**
     * function2
     *
     * @return true
     */
    public boolean function2() {
        // todo : 某项公用操作 2（自己封装）
        return true;
    }

    /**
     * function3
     *
     * @return true
     */
    public boolean function3() {
        // todo : 某项公用操作 3（自己封装）
        return true;
    }

    /*============================== 页面基本断言 ==============================*/

    // todo : 页面中的公用操作可自行封装
}
