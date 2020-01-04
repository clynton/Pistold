package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import utilities.browserProvider;
import utilities.buttonClicker;
import utilities.configSettings;
import utilities.configSettings.Browser;
import utilities.logCollector;
import utilities.pageElementLocators;
import utilities.testData;

public class loadHomepageInDiffBrowsers {

	@Test(dataProvider = "testWhichBrowsers")
	public void gotoHomePages(Browser browser) {

		logCollector.debug("Getting Browser: " + browser);

		WebDriver driver = browserProvider.getBrowser(browser);

		if (driver == null) {

			System.out.println("Note: '" + browser + "' Driver not set. Can, but won't assert right now");
			return;
		}

		//
		System.out.println("Going to '" + testData.MainSitePage.Url + "' - in driver: " + driver.toString());

		// for some reason, get(page) is saying times out in ie - even though it works
		try {
			if (browser == Browser.IE) {

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

		// in some browsers, might need Thread.sleep after loading the URL before By.xpath and By.cssSelector work
		int delayAfterLoadingUrl_s = configSettings.SeleniumBrowserDelayTimes.DelayAfterLoadingUrl_s;
		if (delayAfterLoadingUrl_s > 0) {
			System.out.println("just loaded page... waiting " + delayAfterLoadingUrl_s
					+ " seconds...");

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
		
		// check that we got to the page...
		String expectedTitle = testData.MainSitePage.Title;
		String actualTitle = driver.getTitle();

		String msg = "Loaded page - got title: '" + actualTitle + "'";
		System.out.println(msg);
		logCollector.debug(msg);

		AssertJUnit.assertEquals(actualTitle, expectedTitle);

		// Click a link button - found using xpath or css or whatever
		// returns exception message if not there, but we can check for something else
		String clickResult = buttonClicker.Click(driver, pageElementLocators.MainSitePage.HomePageLinkBtn,
				configSettings.SeleniumBrowserDelayTimes.DelayAfterClickButton_ms);
		AssertJUnit.assertEquals(clickResult, "");		

		// Other stuff...
		//new Select(driver.findElement(...)).selectByValue("dropdown-value");
		//driver.findElement(...).sendKeys("mytext" + Keys.TAB + "text in a textarea field after hitting tab");
	}

	@Test
	@DataProvider
	public Object[][] testWhichBrowsers() {
		return testData.browsersToTest();
	}


	@BeforeTest
	public void getBrowsers() {
		// BeforeMethod called before each Test method
		// BeforeTest called once only (before all Test methods)

		// loop over testData.browsersToTest()
		// - so not launching browsers we don't need
		String msg = "getBrowsers... ";
		System.out.println(msg);
		logCollector.debug(msg);

		Object[][] browsers = testData.browsersToTest();
		for (int i = 0; i < browsers.length && i < 2000; i++) // no infiniloops
		{
			browserProvider.getBrowser((Browser) browsers[i][0]);
		}

	}

	@AfterTest
	public void closeBrowsers() {
		
		String msg = "closeBrowsers... ";
		System.out.println(msg);
		logCollector.debug(msg);
		
		browserProvider.closeBrowsers();
	}

}
