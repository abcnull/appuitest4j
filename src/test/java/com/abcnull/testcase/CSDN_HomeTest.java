package com.abcnull.testcase;

import com.abcnull.basetest.BaseTest;
import com.abcnull.pageobject.page.CSDN_HomePage;
import org.testng.annotations.Test;

/**
 * 测试 CSDN 首页
 *
 * @author abcnull@qq.com
 * @version 1.0.0
 * @date 2020/8/4 23:12
 */
public class CSDN_HomeTest extends BaseTest {
    public CSDN_HomePage csdn_homePage;
    @Test(description = "CSDN_点击关注", priority = 1)
    public void testFocus() {
        csdn_homePage = new CSDN_HomePage(driver);
        System.out.println(driver.getDeviceTime());
        assert true;
    }
}
