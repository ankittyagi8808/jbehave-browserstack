package com.browserstack.single.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LocalPage {
  private String DEFAULT_LOCAL_URL = "http://localhost:9000/about";
  private WebDriver driver;
  private WebElement element;

  public LocalPage(WebDriver driver) {
    this.driver = driver;
  }

  public String getPageSource(){
    return driver.getPageSource();
  }

  public void openHealthCheck() {
    driver.get(DEFAULT_LOCAL_URL);
  }
}
