package com.wk.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaiduDemo {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\java\\driver\\chromedriver.exe");// chromedriver服务地址

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.baidu.com/");

        WebElement search_text = driver.findElement(By.id("kw"));
        WebElement search_button = driver.findElement(By.id("su"));

        search_text.sendKeys("Java");
        search_text.clear();
        search_text.sendKeys("Selenium");
        search_button.click();

        driver.quit();
    }
}
