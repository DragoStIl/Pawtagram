package project;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.URL;

public class ChromeTest {

    private static final String CHROME_DRIVER_FILE_NAME = "chromedriver.exe";
    private ChromeDriver chromeDriver;

    @BeforeEach
    void setUp (){
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(getChromeDriverFileName())).build();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");

        chromeDriver = new ChromeDriver(service, options);
    }

    @Test
    void testLogin(){
        chromeDriver.get("http://localhost:8080/login");

        WebElement usernameInput = chromeDriver.findElement(By.name("username"));
        WebElement passwordInput = chromeDriver.findElement(By.name("password"));

        usernameInput.sendKeys("drago");
        passwordInput.sendKeys("1234");

        passwordInput.submit();
        WebElement content = chromeDriver.findElement(By.id("all"));
        WebElement p = content.findElement(By.tagName("p"));
        String text = p.getText();




        Assertions.assertTrue(text.contains("I love them all !"));

    }

    @Test
    void testFailLogin(){
        chromeDriver.get("http://localhost:8080/login");

        WebElement usernameInput = chromeDriver.findElement(By.name("username"));
        WebElement passwordInput = chromeDriver.findElement(By.name("password"));

        usernameInput.sendKeys("asd");
        passwordInput.sendKeys("12345");

        passwordInput.submit();
        WebElement button = chromeDriver.findElement(By.className("submit"));
        String text = button.getText();




        Assertions.assertTrue(text.contains("Log in"));

    }


    @AfterEach
    void tearDown(){
        chromeDriver.quit();
    }

    private String getChromeDriverFileName() {
        ClassLoader classLoader = ChromeTest.class.getClassLoader();
        URL driverUrl = classLoader.getResource(CHROME_DRIVER_FILE_NAME);
        if(driverUrl == null){
            Assertions.fail("Unable to locate the Chrome driver !");
        }
        return driverUrl.getFile();
    }
}
