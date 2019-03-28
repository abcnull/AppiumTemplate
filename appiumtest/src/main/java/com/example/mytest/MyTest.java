package com.example.mytest;

import com.example.base.SimpleInitiator;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * Created by 石磊 on 2019/1/24.
 */
@Slf4j
public class MyTest extends SimpleInitiator {
    @Test
    public void test() throws InterruptedException {
        System.out.println("---- 第一个测试用例 ----");

        // 延时 5s
        Thread.sleep(5000);
        // 找到登陆按钮
        WebElement loginBtn = driver.findElementByXPath("(//android.widget.Button)[1]");
        // 点击登陆按钮
        loginBtn.click();

        log.info("点击登陆按钮前往输入用户名和密码");

        // 找到 qq 号输入框
        WebElement userName = driver.findElementByXPath("(//android.widget.EditText)[1]");
        // 点击 qq 号输入框
        userName.click();
        // 延时 1s
        Thread.sleep(1000);
        // 输入 qq 号
        userName.sendKeys("463279708");
        // 延时 1s
        Thread.sleep(1000);

        log.info("输入qq号码成功");

        // 找到 qq 密码输入框
        WebElement password = driver.findElementByXPath("(//android.widget.EditText)[2]");
        // 点击 qq 密码输入框
        password.click();
        // 延时 1s
        Thread.sleep(1000);
        // 输入 qq 密码
        password.sendKeys("3s33333sssss");
        // 延时 1s
        Thread.sleep(1000);

        log.info("输入qq密码成功");

        // 点击登陆按钮
        WebElement loginBtn2 = driver.findElementByXPath("//android.widget.Button");
        // 点击登录按钮
        loginBtn2.click();
        log.info("qq登陆成功");
    }
}
