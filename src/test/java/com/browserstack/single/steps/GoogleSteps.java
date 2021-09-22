package com.browserstack.single.steps;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.browserstack.single.pages.GooglePage;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;

public class GoogleSteps {
  private GooglePage page;

  private WebDriver webdriver;
  public GoogleSteps(WebDriver driver)
  {
    page = new GooglePage(driver);
  this.webdriver=driver;
  }

  @When("I type query as \"$keyword\"")
  public void searchGoogle(String keyword) {
    page.searchOnGoogle(keyword);
  }

  @Then("I submit")
  public void submit() throws Exception {
    page.submitSearch();
  }

  @Then("I should see title \"$keyword\"")
  public void titleShouldContain(String keyword) {
    Assert.assertEquals(keyword, page.getTitle());
    markTestStatus("Fail","failing",this.webdriver);
  }

  public static void markTestStatus(String status, String reason, WebDriver driver) {  // the same WebDriver instance should be passed that is being used to run the test in the calling funtion
try {
  URI uri = new URI(String.format("https://ankittyagi_3dagDW:Kqx6wFpszysesyrEFqsB@api.browserstack.com/automate/sessions/%s.json",((RemoteWebDriver)driver).getSessionId()));
  HttpPut putRequest = new HttpPut(uri);
  ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
  nameValuePairs.add((new BasicNameValuePair("status", "failed")));
  nameValuePairs.add((new BasicNameValuePair("reason", "")));
  putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

  HttpClientBuilder.create().build().execute(putRequest);
}catch(Exception e){
System.out.println(e.getMessage());
}
}
}
