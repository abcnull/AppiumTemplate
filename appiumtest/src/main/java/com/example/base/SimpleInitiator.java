package com.example.base;

/**
 * Created by 石磊 on 2019/1/25.
 */

import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 目前需要解决的问题：
 * 1.properties文件做些修改
 * conf.app_package参数，conf.app_activity参数，conf.app参数
 * 2.启动driver的代码
 * 3.sdk，adb
 * 4.模拟器还是真机
 * 5.他人项目代码中多出来的东西
 * 6.其他遗漏点
 * 7.截图问题
 * 8.退出驱动关闭浏览器问题
 */

/**
 * 启动手机浏览器的前置操作，此类可以被更优类替代
 *
 * @author lei
 * @date 2019.01.25
 */
@Slf4j
public class SimpleInitiator {
    /**
     * Appium 驱动变量
     */
    protected AppiumDriver driver;
    /**
     * automationName
     */
    private String automationName;
    /**
     * udid
     */
    private String udid;
    /**
     * deviceName
     */
    private String deviceName;
    /**
     * platformName
     */
    private String platformName;
    /**
     * platformVersion
     */
    private String platformVersion;
    /**
     * appPackage
     */
    private String appPackage;
    /**
     * appActivity
     */
    private String appActivity;
    /**
     * newCommandTimeout
     */
    private String newCommandTimeout;
    /**
     * app
     */
    private String app;

    /* ======================================== 配置好 app 驱动 ======================================== */
    /**
     * 初始化 appium 驱动的方法：
     * 1.读取配置文件中信息并保存变量
     * 2.设置 appium 驱动
     * 3.驱动启动 app
     *
     * @param propertiesPath
     */
    @BeforeTest(groups = {"SimpleInitiator"})
    @Parameters({"propertiesPath"})
    public void launch(@Optional("src/main/resources/config.properties")String propertiesPath){
        // START
        log.info("=== APPIUM TEST START ===");

        // 读取 properties 配置文件并保存变量
        try {
            this.setPropertiesData(propertiesPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 设置 appium 驱动并开启 app
        try {
            this.setAppDriver();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将 properties 中的数据保存为类的成员变量
     *
     * @param propertiesPath
     */
    private void setPropertiesData(String propertiesPath) throws IOException {
        // 开始读取配置文件
        log.info("开始读取配置文件...");

        // config.properties 文件的 File 对象
        File propertiesFile = new File(propertiesPath);
        // 输入流读取 properties 文件
        InputStreamReader propertiesReader = new InputStreamReader(new FileInputStream(propertiesFile.getAbsolutePath()), "UTF-8");
        // 定义 Properties 对象 properties
        Properties properties = new Properties();
        // 通过 Properties 类的 load 方法来读取 properties 文件中的变量
        properties.load(propertiesReader);

        /* 保存 properties 中的数据给成员变量 */
        this.automationName = properties.getProperty("conf.automation_name");
        this.udid = properties.getProperty("conf.udid");
        this.deviceName = properties.getProperty("conf.device_name");
        this.platformName = properties.getProperty("conf.platform_name");
        this.platformVersion = properties.getProperty("conf.platform_version");
        // app 的安装包名
        this.appPackage = properties.getProperty("conf.app_package");
        // app 的 Activity 活动界面名称
        this.appActivity = properties.getProperty("conf.app_activity");
        this.newCommandTimeout = properties.getProperty("conf.new_command_timeout");
        this.app = properties.getProperty("conf.app");
    }

    /**
     * 设置设备驱动
     *
     * udid：通过 cmd 中输入 adb devices 来查看连接设备的 IMEI 号
     * deviceName：给设备取名字，随机
     * platformName：下面用的是 Android
     * platformVersion：找到自己手机是安卓那个版本的
     * appPackage：腾讯 qq 的是 com.tencent.mobileqq，可以通过 adb shell dumpsys activity activities 命令查看
     * appActivity：腾讯 qq 的是 .activity.SplashActivity，可以通过 adb shell dumpsys activity activities 命令查看
     *
     */
    private void setAppDriver() throws MalformedURLException {
        /* 此类本质是(key, value)，负责参数设置 */
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        // 驱动配置设备相关参数开始
        log.info("驱动配置设备相关参数开始...");

        /* 驱动配置设备相关参数 */
        // appium 自动化名字 Appium
        desiredCapabilities.setCapability("automationName", this.automationName);
        // 设备的 IMEI 编号 3a789767
        desiredCapabilities.setCapability("udid", this.udid);
        // deviceName 模拟器设置的属性中 IMEI 填入或者手机名称
        desiredCapabilities.setCapability("deviceName", this.deviceName);
        // platformName 平台设置 IOS，Android，FirefoxOS
        desiredCapabilities.setCapability("platformName", this.platformName);
        // platformVersion 系统版本号设置，cmd：adb shell getprop ro.build.version.release
        desiredCapabilities.setCapability("platformVersion", this.platformVersion);

        // 驱动配置 apk 相关参数开始
        log.info("驱动配置 apk 相关参数开始...");

        /* 驱动配置 apk 相关参数 */
        // appPackage 要启动的app包 进入 sdk\build-tools\版本号路径下，cmd：apt dump badging C:\apps\xxxxx.apk 来查看
        desiredCapabilities.setCapability("appPackage", this.appPackage);
        // appActivity 要启动的Activity 同上类似
        desiredCapabilities.setCapability("appActivity", this.appActivity);
        // sessionOverride 每次启动时覆盖 session，否则第二次后运行会报错不能新建 session
        desiredCapabilities.setCapability("sessionOverride", true);
        // unicodeKeyboard 设置键盘支持中文输入
        desiredCapabilities.setCapability("unicodeKeyboard", true);
        // resetKeyboard 设置默认键盘为 appium 的键盘，设置输入法不重置到原有状态
        desiredCapabilities.setCapability("resetKeyboard", false);
        // noSign 避免重新签名
        desiredCapabilities.setCapability("noSign", true);
        // newCommandTimeout 延时 30
        desiredCapabilities.setCapability("newCommandTimeout", this.newCommandTimeout);

        /* 本地安装 apk 包的文件路径设置，若不需要安装，就不用设置 */
//        // user.dir
//        File classpathRoot = new File(System.getProperty("user.dir"));
//        // apps app路径
//        File appDir = new File(classpathRoot, "apps");
//        // apk
//        File app = new File(appDir, "zhihu.apk");
//        // app
//        desiredCapabilities.setCapability("app", app.getAbsolutePath());

        // 驱动开始初始化
        log.info("开始初始化驱动...");

        /* 初始化驱动 */
        this.driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
        /* 设置隐式等待 10s */
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /* ========================================== 退出 app 驱动 ======================================== */
    /**
     * app 驱动退出
     */
    @AfterTest
    private void quitDriver(){
        // 驱动退出
        log.info("驱动退出...");

        // 驱动退出
        this.driver.quit();

        // OVER
        log.info("=== APPIUM TEST OVER ===");
    }
}
