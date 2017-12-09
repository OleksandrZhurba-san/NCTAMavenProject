package ua.edu.sumdu.ta.zhurba.pr7.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class WebSiteTest {
    @Test
    public void testGoogleSearch() throws InterruptedException {

        String textToSelect = "netcracker job openings";
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("http://www.google.com/");
        WebDriverWait wait = new WebDriverWait(driver, 100);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        driver.findElement(By.name("q")).sendKeys("netcracker jo");
        List<WebElement> autoSuggestList = driver.findElements(By.className("sbqs_c"));
        // click the auto suggest
        for (WebElement option : autoSuggestList) {
            if (option.getText().contains(textToSelect)) {
                option.click();
                break;
            }
        }

        List<WebElement> resultEntries = driver.findElements(By.cssSelector("h3.r > a"));
        for (WebElement element : resultEntries){
            if (element.getText().equals("Netcracker - Open Positions")){
                element.click();
                break;
            }
        }

        WebElement deps = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='jobdept-group']")));
        deps.click();

        List<WebElement> jobDeps = driver.findElements(By.xpath("//ul//li[contains(.,'Implementation Engineering')]"));
        for(WebElement element : jobDeps){
            if (element.getText().equals("Implementation Engineering")){
                element.click();
                break;
            }
        }

        List<WebElement> listResult = driver.findElements(By.xpath("//div[@class='job result active']"));
        Map<String,List<String>> result = new HashMap<String, List<String>>();
        for (WebElement element : listResult){
            String country = element.findElement(By.cssSelector(".info > p")).getText();
            String vacancy = element.findElement(By.tagName("h3")).getText();
            if(result.containsKey(country)) {
                result.get(country).add(vacancy);
            } else {
                List<String> vacancies = new ArrayList<String>();
                vacancies.add(vacancy);
                result.put(country,vacancies);
            }
        }

        System.out.println(result);

        Map<String,List<String>> mapToAssert = new HashMap<String, List<String>>();

        List<String> list1 = Arrays.asList("Business Analyst - OSS");
        mapToAssert.put("Rome, Italy",list1);

        List<String> list2 = Arrays.asList("Business Analyst - Product Catalog");
        mapToAssert.put("Brussels, Belgium",list2);

        List<String> list3 = Arrays.asList("Business Analyst, OSS", "Business Analyst, OSS (Service Activation)","QA Analyst (AMX- DR)","Solution Architect, OSS","Technical Manager, OSS");
        mapToAssert.put("Santo Domingo, Dominican Republic",list3);

        List<String> list4 = Arrays.asList("Solution Architect, BSS");
        mapToAssert.put("Vancouver, Canada",list4);

        List<String> list5 = Arrays.asList("Director SaaS Product Management");
        mapToAssert.put("Waltham, USA",list5);

        List<String> list6 = Arrays.asList("Principal Solutions Architect, BSS");
        mapToAssert.put("Atlanta, USA",list6);

        List<String> list7 = Arrays.asList("Technical Project Manager");
        mapToAssert.put("Frankfurt, Germany",list7);

        assertThat(result, is(mapToAssert));

    }
}