package utilities;

import utilities.configSettings.Browser;

public class testData {

	public class MainSitePage {
		public static final String Title = "The world’s leading software development platform · GitHub";
		public static final String Url = "https://github.com/";

	}

//	private static Object[][] _browsersToTest = new Object[][] { new Object[] {
//	Browser.Chrome }, new Object[] { Browser.FireFox } };

	private static Object[][] _browsersToTest = new Object[][] { new Object[] { Browser.Chrome } };

	public static Object[][] browsersToTest() {
		return _browsersToTest;
	}

	/*
	 * if want to add a data provider to the test class, can implement using this...
	 * TestNG will auto call multiple tests using the provided data - can add other
	 * params like so:
	 *
	 * new Object[][] {new Object[ {Browser.Chrome, "p2", 3}, {Browser.FireFox...}
	 * 
	 * then change the test method like so:
	 * 
	 * testr(Browser b, String p2, int p3){}
	 * 
	 * ex: XPath not working on IE - even after thread.sleep, so don't ask for it
	 * 
	 * private static Object[][] _browsersToTest = new Object[][] { new Object[] {
	 * Browser.Chrome }, new Object[] { Browser.FireFox } };
	 */

}
