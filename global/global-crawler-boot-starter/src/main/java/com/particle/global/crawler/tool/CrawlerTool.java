package com.particle.global.crawler.tool;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * <p>
 * 常用工具
 * </p>
 *
 * @author yangwei
 * @since 2025/12/23 20:15
 */
@Slf4j
public class CrawlerTool {

    /**
     * 获取页面标题
     * @param driver
     * @return
     */
    public static String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    /**
     * 等待全局,这属于一个配置项，设置后每次对浏览器的操作都会等待
     * 注意：和 sleep()方法不同，sleep()方法会阻塞当前线程，而 waitGlobal()方法不会阻塞当前线程,也不一定会实际等待设置的时间
     * @param driver
     * @param millis 毫秒，建议 2000 毫秒
     */
    public static void waitGlobal(WebDriver driver, long millis) {
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(millis));
    }
    /**
     * 强制等待
     * @param millis
     */
    public static void waitForce(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    /**
     * cssSelector
     * @param cssSelector
     * @return
     */
    public static By cssLocator(String cssSelector) {
        return By.cssSelector(cssSelector);
    }
    /**
     * 等待元素出现
     * @param driver
     * @param millis 毫秒，建议 2000 毫秒
     * @param locator
     * @return
     */
    public static WebElement findElementWithWait(WebDriver driver, long millis, By locator) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofMillis(millis));
        return webDriverWait.until(webDriver -> {
            ExpectedCondition<WebElement> webElementExpectedCondition = ExpectedConditions.presenceOfElementLocated(locator);
            try {
                return webElementExpectedCondition.apply(webDriver);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 通过cssSelector获取元素
     * @param driver
     * @param locator
     * @return
     */
    public static WebElement findElement(FirefoxDriver driver, By locator) {
        long start = System.currentTimeMillis();
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception e) {
            log.error("findElementByCssSelector error,cssSelector={}",locator.toString(),e);
            return  null;
        }finally {
            long end = System.currentTimeMillis();
            log.debug("findElementByCssSelector time={}ms,cssSelector={}",end-start,locator.toString());
        }
        return element;
    }
    /**
     * 关闭浏览器
     * @param driver
     */
    public static void closeDriver(WebDriver  driver) {
        if (driver != null) {
            driver.quit();
        }
    }

}
