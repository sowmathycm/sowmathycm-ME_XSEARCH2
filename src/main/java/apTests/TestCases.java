
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
// Selenium Imports
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.wikipedia.org/");
        String currentURL = driver.getCurrentUrl();
        String expectedTitle = "wikipedia";
        if (currentURL.contains(expectedTitle)) {
            System.out.println("The URL contains the expected title" + " " + expectedTitle);
        } else {
            System.out.println("The URL does not contain the expected title" + " " + expectedTitle);
        }
        System.out.println("End Test case: testCase01");
    }

    public void testCase02() {
        try{

       
        System.out.println("Start Test case: testCase02");
        driver.get("https://www.wikipedia.org/");
        WebElement header = driver.findElement(By
                .xpath("//div[@class='central-textlogo']//h1/span[normalize-space()='Wikipedia']"));
        String headerText = header.getText();
        if (headerText.equals("Wikipedia")) {
            System.out.println("The header text contains the text");
        } else {
            System.out.println("Header Text verification failed");
        }


        WebElement termsofuse = driver
                .findElement(By.xpath("//a[@href='https://meta.wikimedia.org/wiki/Terms_of_use']"));
        WebElement privacypolicy = driver.findElement(
                By.xpath("//a[@href='https://meta.wikimedia.org/wiki/Privacy_policy']"));
        
            String termsofUse = termsofuse.getText();
            String privacyPolicy = privacypolicy.getText();
            if (termsofUse.equals("Terms of Use") && privacyPolicy.equals("Privacy Policy")) {
                System.out.println("The Footer contains the Terms Of Use and Privacy Policy");
            } else {
                System.out.println("Footer Text verification failed");
            }

        
        System.out.println("End Test case: testCase02");
    }catch(Exception e){
        System.out.println("Test Case Failed:" +e.getMessage());
    }
}

    public void testCase03() {
        
        try{
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.wikipedia.org/");
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='searchInput']"));
        searchInput.sendKeys("apple");


        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement dropdown = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='suggestions-dropdown']")));
        WebElement searchResults = dropdown.findElement(By.xpath(
                "//p[@class='suggestion-description' and text()='American multinational technology corporation']"));
        searchResults.click();
        WebElement founder = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[@class='plainlist']//a[@href='/wiki/Steve_Jobs' and contains(text(), 'Steve Jobs')]")));
       //System.out.println("Found element text" + founder.getText());
       
            String found = founder.getText();

            if(found.equals("Steve Jobs")){
                System.out.println("Steve Jobs is contained in the founders list");
            }
            else{
               System.out.println("Steve Jobs is not contained in the founders list");
            }
            
        
    

        System.out.println("End Test case: testCase03");
    } catch(Exception e){
        System.out.println("Test Case Failed:" +e.getMessage());
    }
    }

    public void testCase04() {
        System.out.println("Start Test case: testCase04");
        driver.get("https://www.wikipedia.org/");
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='searchInput']"));
        searchInput.sendKeys("microsoft");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement dropdown = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='suggestions-dropdown']")));
        WebElement searchResults = dropdown.findElement(By.xpath(
                "//p[@class='suggestion-description' and text()='American multinational technology corporation']"));
        searchResults.click();
        WebElement founder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//div[@class='plainlist']//a[@href='/wiki/Bill_Gates' and contains(text(), 'Bill Gates')]")));
                String found = founder.getText();

                if(found.equals("Bill Gates")){
                    System.out.println("Bill Gates is contained in the founders list");
                }
                else{
                   System.out.println("Bill Gates is not contained in the founders list");
                }
        founder.click();

        String currentURL = driver.getCurrentUrl();
        if (currentURL.contains("Bill_Gates")) {
            System.out.println("The opened URL contains Bill_Gates");

        } else {
            System.out.println("The opened URL does not contain Bill_Gates");
        }
        System.out.println("End Test case: testCase04");

    }

    public void testCase05() throws InterruptedException {

        System.out.println("Start Test case: testCase05");
        driver.get("https://en.wikipedia.org/");

       // Thread.sleep(20000);

        // Or any larger sleep time


        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement mainmenu = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@id='vector-main-menu-dropdown']")));
        mainmenu.click();
        WebElement searchResults = mainmenu.findElement(By.xpath(
                ".//a[@href='/wiki/Wikipedia:About' and span[text()='About Wikipedia']]"));
        searchResults.click();
        String currentURL = driver.getCurrentUrl();
        if (currentURL.contains("About")) {
            System.out.println("The opened URL contains About");

        } else {
            System.out.println("The opened URL does not contain About");
        }
        System.out.println("End Test case: testCase05");
        
      
    }
}

