package bddstepdefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;
import utilities.browserProvider;
import utilities.buttonClicker;
import utilities.configSettings;
import utilities.configSettings.Browser;
import utilities.logCollector;
import utilities.pageElementLocators;
import utilities.testData;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;

@RunWith(Cucumber.class)
public class bddHomePageValidation {

	WebDriver driver = null;

	@Given("^F01 S01 web browser is open$")
	public void f01_s01_web_browser_is_open() throws Throwable {
		logCollector.info("opening browser");

		driver = browserProvider.getBrowser(Browser.Chrome);
	}

	@When("^F01 S01 user enters home page url$")
	public void f01_s01_user_enters_home_page_url() throws Throwable {

		// in this scenario, we should already have the driver
		Assert.assertNotEquals(null, driver);

		logCollector.info("going to home page in browser: " + driver.getClass().getName());

		// for some reason, get(page) is saying times out in ie - even though it works
		try {
			if (driver.getClass().getName() == configSettings.SeleniumClassNames.IE) {

				int pageLoadTimeoutForIE_s = configSettings.SeleniumBrowserDelayTimes.PageLoadTimeoutForIE_s;
				if (pageLoadTimeoutForIE_s > 0) {
					System.out.println(
							"setting page load timer for IE browser to " + pageLoadTimeoutForIE_s + " seconds...");
					driver.manage().timeouts().pageLoadTimeout(pageLoadTimeoutForIE_s, TimeUnit.SECONDS);
				}

				int delayBeforeLoadingUrlInIE_s = configSettings.SeleniumBrowserDelayTimes.DelayBeforeLoadingUrlInIE_s;
				if (delayBeforeLoadingUrlInIE_s > 0) {
					System.out.println("about to load page in IE browser... waiting " + delayBeforeLoadingUrlInIE_s
							+ " seconds...");

					try {
						// System.out.println("sleeping in");
						Thread.sleep(delayBeforeLoadingUrlInIE_s * 1000);
					} catch (InterruptedException e) {
						// e.printStackTrace();
						System.out.println(e.getMessage());
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			}

			driver.get(testData.MainSitePage.Url);

		} catch (Exception ex) {
			//
			System.out.println(ex);

//			org.openqa.selenium.TimeoutException: Timed out waiting for page to load.
//			Build info: version: '3.141.59', revision: 'e82be7d358', time: '2018-11-14T08:25:48'
//			System info: host: '...', ip: '...', os.name: 'Windows 10', os.arch: 'amd64', os.version: '10.0', java.version: '13.0.1'
//			Driver info: org.openqa.selenium.ie.InternetExplorerDriver
//			Capabilities {acceptInsecureCerts: false, browserName: internet explorer, browserVersion: 11, javascriptEnabled: true, pageLoadStrategy: normal, platform: WINDOWS, platformName: WINDOWS, proxy: Proxy(), se:ieOptions: {browserAttachTimeout: 0, elementScrollBehavior: 0, enablePersistentHover: true, ie.browserCommandLineSwitches: , ie.edgechromium: false, ie.edgepath: , ie.ensureCleanSession: false, ie.fileUploadDialogTimeout: 3000, ie.forceCreateProcessApi: false, ignoreProtectedModeSettings: false, ignoreZoomSetting: false, initialBrowserUrl: http://localhost:40789/, nativeEvents: true, requireWindowFocus: false}, setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify}

			// continue anyway
		}

		// in some browsers, might need Thread.sleep after loading the URL before
		// By.xpath and By.cssSelector work
		int delayAfterLoadingUrl_s = configSettings.SeleniumBrowserDelayTimes.DelayAfterLoadingUrl_s;
		if (delayAfterLoadingUrl_s > 0) {
			System.out.println("just loaded page... waiting " + delayAfterLoadingUrl_s + " seconds...");

			try {
				// System.out.println("sleeping in");
				Thread.sleep(delayAfterLoadingUrl_s * 1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

	}

	@Then("^F01 S01 browser loads the home page url$")
	public void f01_s01_browser_loads_the_home_page_url() throws Throwable {
		// nothing to do. we will validate the page is loaded next
	}

	@And("^F01 S01 home page title is validated$")
	public void f01_s01_home_page_title_is_validated() throws Throwable {
		// check that we got to the page...
		String expectedTitle = testData.MainSitePage.Title;
		String actualTitle = driver.getTitle();

		logCollector.debug("Loaded page - got title: '" + actualTitle + "'");

		AssertJUnit.assertEquals(actualTitle, expectedTitle);
	}

	// TODO: add browser closer - need to deal with the issue of other tests relying
	// on it being open

	@Given("^F01 S02 browser is up and home page is loaded$")
	public void f01_s02_browser_is_up_and_home_page_is_loaded() throws Throwable {

		// in this scenario the driver might not be defined already, so load it now
		logCollector.info("opening browser");

		driver = browserProvider.getBrowser(Browser.Chrome);

		logCollector.info("going to home page in browser: " + driver.getClass().getName());

		// for some reason, get(page) is saying times out in ie - even though it works
		try {
			if (driver.getClass().getName() == "IE") {

				int pageLoadTimeoutForIE_s = configSettings.SeleniumBrowserDelayTimes.PageLoadTimeoutForIE_s;
				if (pageLoadTimeoutForIE_s > 0) {
					System.out.println(
							"setting page load timer for IE browser to " + pageLoadTimeoutForIE_s + " seconds...");
					driver.manage().timeouts().pageLoadTimeout(pageLoadTimeoutForIE_s, TimeUnit.SECONDS);
				}

				int delayBeforeLoadingUrlInIE_s = configSettings.SeleniumBrowserDelayTimes.DelayBeforeLoadingUrlInIE_s;
				if (delayBeforeLoadingUrlInIE_s > 0) {
					System.out.println("about to load page in IE browser... waiting " + delayBeforeLoadingUrlInIE_s
							+ " seconds...");

					try {
						// System.out.println("sleeping in");
						Thread.sleep(delayBeforeLoadingUrlInIE_s * 1000);
					} catch (InterruptedException e) {
						// e.printStackTrace();
						System.out.println(e.getMessage());
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
					}
				}
			}

			driver.get(testData.MainSitePage.Url);

		} catch (Exception ex) {
			//
			System.out.println(ex);

//			org.openqa.selenium.TimeoutException: Timed out waiting for page to load.
//			Build info: version: '3.141.59', revision: 'e82be7d358', time: '2018-11-14T08:25:48'
//			System info: host: '...', ip: '...', os.name: 'Windows 10', os.arch: 'amd64', os.version: '10.0', java.version: '13.0.1'
//			Driver info: org.openqa.selenium.ie.InternetExplorerDriver
//			Capabilities {acceptInsecureCerts: false, browserName: internet explorer, browserVersion: 11, javascriptEnabled: true, pageLoadStrategy: normal, platform: WINDOWS, platformName: WINDOWS, proxy: Proxy(), se:ieOptions: {browserAttachTimeout: 0, elementScrollBehavior: 0, enablePersistentHover: true, ie.browserCommandLineSwitches: , ie.edgechromium: false, ie.edgepath: , ie.ensureCleanSession: false, ie.fileUploadDialogTimeout: 3000, ie.forceCreateProcessApi: false, ignoreProtectedModeSettings: false, ignoreZoomSetting: false, initialBrowserUrl: http://localhost:40789/, nativeEvents: true, requireWindowFocus: false}, setWindowRect: true, strictFileInteractability: false, timeouts: {implicit: 0, pageLoad: 300000, script: 30000}, unhandledPromptBehavior: dismiss and notify}

			// continue anyway
		}

		// in some browsers, might need Thread.sleep after loading the URL before
		// By.xpath and By.cssSelector work
		int delayAfterLoadingUrl_s = configSettings.SeleniumBrowserDelayTimes.DelayAfterLoadingUrl_s;
		if (delayAfterLoadingUrl_s > 0) {
			System.out.println("just loaded page... waiting " + delayAfterLoadingUrl_s + " seconds...");

			try {
				// System.out.println("sleeping in");
				Thread.sleep(delayAfterLoadingUrl_s * 1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}

	}

	@When("^F01 S02 user clicks home page link$")
	public void f01_s02_user_clicks_home_page_link() throws Throwable {
		// Click a link button - found using xpath or css or whatever
		// returns exception message if not there, but we can check for something else
		String clickResult = buttonClicker.Click(driver, pageElementLocators.MainSitePage.HomePageLinkBtn,
				configSettings.SeleniumBrowserDelayTimes.DelayAfterClickButton_ms);
		AssertJUnit.assertEquals(clickResult, "");
	}

	@Then("^F01 S02 browser reloads the url$")
	public void f01_s02_browser_reloads_the_url() throws Throwable {
		// nothing to do. we will validate the page is loaded next
	}

	@And("^F01 S02 page title is revalidated$")
	public void f01_s02_page_title_is_revalidated() throws Throwable {
		String expectedTitle = testData.MainSitePage.Title;
		String actualTitle = driver.getTitle();

		logCollector.debug("Loaded page - got title: '" + actualTitle + "'");

		AssertJUnit.assertEquals(actualTitle, expectedTitle);
	}

	@Then("^F01 S02 the browser is closed$")
	public void f01_s02_the_browser_is_closed() throws Throwable {
		browserProvider.closeBrowsers();
	}
}