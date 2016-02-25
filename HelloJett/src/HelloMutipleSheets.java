import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jett.transform.ExcelTransformer;

import org.apache.log4j.Logger;

import util.JettUtils;


public class HelloMutipleSheets {
	private static Logger logger = Logger.getLogger(HelloMutipleSheets.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String template = JettUtils.getXls("template-Sheets-B.xls");
		String result = JettUtils.getXls("z-Sheets-B.xls");
		try {
			List<String> templateSheetNames = new ArrayList<String>();
			List<String> newSheetNames = new ArrayList<String>();
			List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();

			List<Map<String, Object>> datas = null;
			Map<String, Object> data = null;
			templateSheetNames.add("intro");
			newSheetNames.add("Introduction");
			data = new HashMap<String, Object>();
			data.put("comapnyName", "artlong");
			data.put("year", "2015");
			beansList.add(data);
			
			templateSheetNames.add("sheetToClone");
			newSheetNames.add("Q1 2015");
			datas = new ArrayList<Map<String, Object>>();
			data = new HashMap<String, Object>();
			data.put("name", "TW");
			data.put("income", "50M");
			datas.add(data);
			data = new HashMap<String, Object>();

			data = new HashMap<String, Object>();
			data.put("name", "US");
			data.put("income", "500M");
			datas.add(data);
			data = new HashMap<String, Object>();
			data.put("countrys", datas);
			beansList.add(data);

			templateSheetNames.add("sheetToClone");
			newSheetNames.add("Q2 2015");
			datas = new ArrayList<Map<String, Object>>();
			data = new HashMap<String, Object>();
			data.put("name", "TW");
			data.put("income", "99M");
			datas.add(data);
			data = new HashMap<String, Object>();

			data = new HashMap<String, Object>();
			data.put("name", "US");
			data.put("income", "600M");
			datas.add(data);
			data = new HashMap<String, Object>();
			data.put("countrys", datas);
			beansList.add(data);
			
			ExcelTransformer transformer = new ExcelTransformer();
			transformer.transform(template, result, templateSheetNames, newSheetNames, beansList);
			logger.info("HelloSheets2 done!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

}
