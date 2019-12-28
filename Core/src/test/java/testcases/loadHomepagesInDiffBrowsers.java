package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilities.browserProvider;
import utilities.configSettings;
import utilities.configSettings.Browser;
import utilities.logCollector;
import utilities.testData;

public class loadHomepagesInDiffBrowsers {

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
			
			System.out.println("TODO - stuff to do here... ");
			// TODO 1. in all browsers, might have to wait a few seconds after loading the URL before XPath works
			// TODO 2. check in. start a new project using clone. add test cases
			// TODO 3. run test suite, add maven batch file, add jenkins batch file

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

		// check that we got to the page...
		String expectedTitle = testData.MainSitePage.Title;
		String actualTitle = driver.getTitle();

		String msg = "Loaded page - got title: '" + actualTitle + "'";
		System.out.println(msg);
		logCollector.debug(msg);

		Assert.assertEquals(actualTitle, expectedTitle);

	}

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

		Object[][] browsers = testData.browsersToTest();
		for (int i = 0; i < browsers.length && i < 2000; i++) // no infiniloops
		{
			browserProvider.getBrowser((Browser) browsers[i][0]);
		}

	}

	@AfterTest
	public void closeBrowsers() {
		browserProvider.closeBrowsers();
	}

}
