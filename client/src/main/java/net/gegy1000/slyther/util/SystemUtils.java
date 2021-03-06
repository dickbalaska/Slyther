package net.gegy1000.slyther.util;

import java.io.File;

public final class SystemUtils {
	private SystemUtils() {}

	private static final String NAME = "slyther";
	private static final String HOME_NAME = ".slyther";

	private static File gameFolder;

	public static OperatingSystem getOS() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			return OperatingSystem.WINDOWS;
		} else if (os.contains("sunos") || os.contains("solaris")) {
			return OperatingSystem.SOLARIS;
		} else if (os.contains("unix")) {
			return OperatingSystem.LINUX;
		} else if (os.contains("linux")) {
			return OperatingSystem.LINUX;
		} else if (os.contains("mac")) {
			return OperatingSystem.MACOSX;
		} else {
			return OperatingSystem.UNKNOWN;
		}
	}

	public static File getGameFolder() {
		if (gameFolder == null) {
			String testData = System.getenv("SLYTHERDATA");
			if (testData != null) {
				gameFolder = new File(testData);
				return(gameFolder);
			}
			String appdata = System.getenv("APPDATA");
			if (appdata == null) {
				File f = new File(System.getProperty("user.home"), ".config");
				if (f.exists()) {
					gameFolder = new File(f, NAME);
				} else {
					gameFolder = new File(System.getProperty("user.home"), HOME_NAME);
				}
			} else {
				gameFolder = new File(appdata, HOME_NAME);
			}
		}
		return gameFolder;
	}

	public static File getReplayFolder() {
		File file = new File(getGameFolder(), "replays");
		if (!file.exists())
			file.mkdirs();
		return(file);
	}

	/** Set the game folder used.
	 * Nominally gameFolder is derived, but can be overriden by the command line parser.
	 * @param dir The directory for slyther to place his data.
	 */
	public static void setGameFolder(String dir) {
		File f = new File(dir);
		if (f.exists() && f.isDirectory()) {
			gameFolder = f;
		} else {
			System.err.format("Bad game folder: {}", dir);
		}
	}
}
