package ua.edu.sumdu.ta.zhurba.pr7.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebSiteTest {
    @Test
    public void testGoogleSearch() {

        String textToSelect = "netcracker job";
        System.setProperty("webdriver.chrome.driver", "d:\\Downloads\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://www.google.com/");

        driver.findElement(By.name("q")).sendKeys("netcracker op");
        List<WebElement> autoSuggestList = driver.findElements(By.className("sbqs_c"));
        // print the auto suggest
        for(WebElement option : autoSuggestList){
            System.out.println(option.getText());
            if(option.getText().equals(textToSelect)) {

                option.click();
                break;
            }
        }
    }
}