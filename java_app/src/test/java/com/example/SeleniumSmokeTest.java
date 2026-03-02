
package com.example;
import org.testng.annotations.Test;import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;import org.openqa.selenium.chrome.ChromeOptions;import org.openqa.selenium.chrome.ChromeDriver;
public class SeleniumSmokeTest { @Test public void opens_example_dot_com_in_headless_chrome(){ ChromeOptions options=new ChromeOptions(); options.addArguments("--headless=new","--disable-gpu","--no-sandbox"); WebDriver driver=new ChromeDriver(options); try{ driver.get("https://example.com/"); String title=driver.getTitle(); assertTrue(title!=null && !title.isEmpty(),"Title should not be empty"); } finally { driver.quit(); } } }
