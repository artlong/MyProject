package util;

import java.io.File;

public class XdocReportUtil {
	
	public static File getTemplateFile(String fileName) {
		return new File(getTemplateDir(), fileName);
	}
	
	public static File getOutputFile(String fileName) {
		return new File(getOutputDir(), fileName);
	}
	
	public static String getTemplateDir() {
		return "./template/";
	}

	public static String getOutputDir() {
		return "./output/";
	}
}
