package com.pageobjects;

import static com.setup.SeleniumDriver.*;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Reporter;

import com.setup.SeleniumDriver;

public abstract class PagePko<T> extends SeleniumDriver{
	
	private static final String BASE_URL = "http://172.30.4.167/Saba/Web/Main";
	private static final int LOAD_TIMEOUT = 30;
	private static final int REFRESH_RATE = 2;
	
	public T openPage(Class<T> clazz) {
		T page = PageFactory.initElements(getDriver(), clazz);
		getDriver().get(BASE_URL + getPageUrl());
		
		ExpectedCondition pageLoadCondition = ((PagePko) page).getPageLoadCondition();
		Reporter.log("Jesteśmy tu1",true);
		waitForPageToLoad(pageLoadCondition);
		Reporter.log("Jesteśmy tu",true);
		driver.switchTo().frame("SabaMain");
		//getFrame();
		return page;
	}
	public void getFrame2() {
		getFrame();
		//driver.switchTo().frame("SabaMain");
	}
	
	private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
		Wait wait = new FluentWait(getDriver())
				.withTimeout(LOAD_TIMEOUT, TimeUnit.SECONDS)
				.pollingEvery(REFRESH_RATE, TimeUnit.SECONDS);

		wait.until(pageLoadCondition);
	}

	protected abstract ExpectedCondition getPageLoadCondition();
	
	public abstract String getPageUrl();


}
