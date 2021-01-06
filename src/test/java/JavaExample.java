/*
 * Copyright (c) 2020 TestProject LTD. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.internal.exceptions.AgentConnectException;
import io.testproject.sdk.internal.exceptions.InvalidTokenException;
import io.testproject.sdk.internal.exceptions.ObsoleteVersionException;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;


/* Run a simple Web Test using JUNIT 4*/
public class JavaExample {

    @Test
    public void runTest() throws IOException, AgentConnectException, ObsoleteVersionException, InvalidTokenException {

        ChromeDriver driver = new DriverBuilder<ChromeDriver>(new ChromeOptions()).build(ChromeDriver.class);

        driver.navigate().to("https://example.testproject.io/web/");
        driver.findElement(By.cssSelector("#name")).sendKeys("John Smith");
        driver.findElement(By.cssSelector("#password")).sendKeys(System.getenv("PASSWORD"));
        new Actions(driver).click(driver.findElement(By.cssSelector("#login"))).build().perform();

        boolean passed = driver.findElement(By.cssSelector("#logout")).isDisplayed();
        if (passed) {
            System.out.println("Test Passed");
        } else {
            System.err.println("Test Failed");
        }

        driver.quit();
    }
}
