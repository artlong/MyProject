import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jett.transform.ExcelTransformer;

import org.apache.log4j.Logger;

import util.JettUtils;


public class HelloSheets {
	private static Logger logger = Logger.getLogger(HelloSheets.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String template = JettUtils.getXls("template-Sheets.xls");
		String result = JettUtils.getXls("z-Sheets.xls");
		try {
			List<String> templateSheetNames = new ArrayList<String>();
			List<String> newSheetNames = new ArrayList<String>();
			List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();

			Map<String, Object> data = null;
			templateSheetNames.add("intro");
			newSheetNames.add("Introduction");
			data = new HashMap<String, Object>();
			data.put("comapnyName", "artlong");
			data.put("year", "2015");
			beansList.add(data);
			
			templateSheetNames.add("sheetToClone");
			newSheetNames.add("Q1 2015");
			data = new HashMap<String, Object>();
			data.put("country", "TW");
			data.put("income", "50M");
			beansList.add(data);
			
			templateSheetNames.add("sheetToClone");
			newSheetNames.add("Q2 2015");
			data = new HashMap<String, Object>();
			data.put("country", "CN");
			data.put("income", "500M");
			beansList.add(data);
			
			templateSheetNames.add("sheetToClone");
			newSheetNames.add("Q3 2015");
			data = new HashMap<String, Object>();
			data.put("country", "US");
			data.put("income", "5000M");
			beansList.add(data);
			
			templateSheetNames.add("sheetToClone");
			newSheetNames.add("Q4 2015");
			data = new HashMap<String, Object>();
			data.put("country", "JP");
			data.put("income", "555M");
			beansList.add(data);
			
			ExcelTransformer transformer = new ExcelTransformer();
			transformer.transform(template, result, templateSheetNames, newSheetNames, beansList);
			logger.info("HelloSheets done!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

}
