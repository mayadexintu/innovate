package com.wk.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

public class LoginDemo {
    /**
     * CSDN官方地址
     **/
    private final static String CSDN_URL = "https://www.csdn.net/";

    /**
     * 谷歌驱动
     **/
    private static ChromeDriver browser;

    /**
     * 账号
     **/
    private final static String USERNAME = "1351182501@qq.com";

    /**
     * 密码
     **/
    private final static String PASSWORD = "w9611988k";

    public static void main(String[] args) throws InterruptedException {
        openBrowser();
        String url = "https://zc.plap.mil.cn";
//        String url = "https://gitee.com/";
//        findLoginPage(url);
//        loginMaYun();

        loginJunNet(url);
        Thread.sleep(500000);
        closeBrowser();
    }

    /**
     * 打开谷歌浏览器
     **/
    public static void openBrowser() {
        // 设置全局的驱动地址
        System.setProperty("webdriver.chrome.driver", "D:\\java\\driver\\chromedriver.exe");
        // 创建谷歌驱动对象
        browser = new ChromeDriver();
        // 浏览器实现自己模糊等待
        browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        browser.manage().window().maximize();
    }

    public static void closeBrowser() {
        browser.quit();
    }

    /**
     * 功能描述: <br>
     * 〈寻找登录页面〉
     */
    public static String findLoginPage(String url) {
        // 打开网址
        browser.get(url);
        // 找到登录注册进行点击跳转到真的登录页面
        WebElement webElement = browser.findElementByLinkText("登录");
        if (webElement != null) {
            String realLoginUrl = webElement.getAttribute("href");
            // 模拟进行点击
            webElement.click();
            return realLoginUrl;
        }
        return "";
    }

    /**
     * 功能描述: <br>
     * 〈选择登录方式〉
     */
    public static void loginMaYun() throws InterruptedException {
        // 暂停3秒让页面先加载出来
        Thread.sleep(2000);
        // 输入账户和密码
        browser.findElement(By.id("user_login")).sendKeys(USERNAME);
        Thread.sleep(1000);
        browser.findElement(By.id("user_password")).sendKeys(PASSWORD);
        Thread.sleep(1000);
        // 第一种一般情况下有的网站登录在输入完成之后可以按回车键进行直接登录
//        browser.findElement(By.name("commit")).sendKeys(Keys.ENTER);

        // 第二种就是找到登录按钮进行模拟点击
        // 两个方法选择一个就可以了
        WebElement loginWebElement = browser.findElement(By.name("commit"));
        loginWebElement.click();
    }


    /**
     * 登录军网
     */
    public static void loginJunNet(String url) throws InterruptedException {
        // 暂停2秒让页面先加载出来
        Thread.sleep(1000);
        browser.get(url);
        // 找到登录注册进行点击跳转到真的登录页面
        WebElement webElement = browser.findElementByCssSelector("[class='user-action-btn login']");
//        WebElement webElement = browser.findElementByCssSelector(".user-action-btn.login");
        webElement.click();
        Thread.sleep(1000);
        // 点击供应商
//        browser.switchTo().frame("layui-layer-iframe8");
        browser.switchTo().frame(1);
        Thread.sleep(1000);
        browser.findElementById("waiselected").click();
        Thread.sleep(1000);
        // 输入账户和密码
        browser.findElement(By.id("loginid")).sendKeys("zjmiec");
        Thread.sleep(500);
        browser.findElement(By.id("loginpwd")).sendKeys("zjmiec1234");
        Thread.sleep(500);
        WebElement loginWebElement = browser.findElementByLinkText("登    录");
        loginWebElement.click();
        Thread.sleep(500);
        //转回主页面
        browser.switchTo().defaultContent();
    }

    /**
     * 军网报价
     */
    public void junNetQuote() {
        browser.switchTo().frame(0);
        browser.findElementByCssSelector(".wait-handle-icon.quoted").click();
    }
}
