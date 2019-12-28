package utilities;

import org.apache.log4j.Logger;

public class logCollector {

	// if want to give full capabilities...
	// public static Logger Log = Logger.getLogger(logCollector.class.getName());

	static Logger Log = Logger.getLogger(logCollector.class.getName());

	public static void info(String message) {
		Log.info(message);
	}

	public static void warn(String message) {
		Log.warn(message);
	}

	public static void error(String message) {
		Log.error(message);
	}

	public static void fatal(String message) {
		Log.fatal(message);
	}

	public static void debug(String message) {
		Log.debug(message);
	}

}
