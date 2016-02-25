import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jett.transform.ExcelTransformer;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import util.JettUtils;
import vo.Employee;

public class HelloJett {
	private static Logger logger = Logger.getLogger(HelloJett.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String template = JettUtils.getXls("template.xls");
		String result = JettUtils.getXls("z-result.xls");
		try {
			new ExcelTransformer().transform(template, result, JettUtils.genSimpleBean());
			logger.info("HelloWorld done!");
		} catch (IOException e) {
			logger.error("IOException reading " + template + " : " + e.getMessage());
		} catch (InvalidFormatException e) {
			logger.error("InvalidFormatException reading  " + result + " : " + e.getMessage());
		}
	}
}
