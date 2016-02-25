import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jett.transform.ExcelTransformer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.apache.poi.ss.formula.EvaluationWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.JettUtils;


public class HelloExcelFunc {
	private static Logger logger = Logger.getLogger(HelloExcelFunc.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		tryJettTemplate();
		tryPoi1();
//		tryPoi2();
	}
	
	public static void tryPoi() {
		try {
			String excelFilePath = "resultFuncA.xls";
			FileInputStream inputStream = new FileInputStream(new File(JettUtils.getXls(excelFilePath)));
			
			Workbook workbook = new HSSFWorkbook(inputStream);
			Sheet sheet = workbook.getSheet("Validate");
			int lastRowNum = sheet.getLastRowNum();
			logger.info("How many Rows ? " + lastRowNum);
			Row row = null;
			Cell cell = null;
			for(int i=0; i<=lastRowNum; i++) {
				logger.info("\tRow[" + i + "]");
				row = sheet.getRow(i);
				if(row != null) {
					int lastCellNum = row.getLastCellNum();
					for(int j=0; j<=lastCellNum; j++) {
						cell = row.getCell(j);
						if(cell != null) {
							logger.info("\t\tCell[" + j + "], Type[" + cell.getCellType() + "]");
//							cell.setCellType(2);
							String va = StringUtils.trimToEmpty(cell.getStringCellValue());
							if(va.startsWith("=")) {
								logger.info("QooQooQoo value[" + va + "]");
							}
						}
					}
				}
			}
			inputStream.close();
			
			FileOutputStream outputStream = new FileOutputStream(JettUtils.getXls(excelFilePath));
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("tryPoi done!");
	}
	
	public static void tryPoi1() {
		try {
			String excelFilePath = "resultFuncA.xls";
			FileInputStream inputStream = new FileInputStream(new File(JettUtils.getXls(excelFilePath)));
			
			Workbook workbook = new HSSFWorkbook(inputStream);
			
			/* for FormulaEvaluator refrence other xls beg*/
			// Create a FormulaEvaluator to use

//			FormulaEvaluator mainWorkbookEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
//			FormulaEvaluator mainWorkbookEvaluator = WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator();
//			mainWorkbookEvaluator.setDebugEvaluationOutputForNextEval(true);
//			mainWorkbookEvaluator.setIgnoreMissingWorkbooks(true);

			// Track the workbook references
//			Map<String,FormulaEvaluator> workbooks = new HashMap<String, FormulaEvaluator>();
			// Add this workbook
//			workbooks.put(excelFilePath, mainWorkbookEvaluator);
//			workbooks.put("resultFuncB.xls", mainWorkbookEvaluator);
			
			// Add two others
//			HSSFFormulaEvaluator two = (HSSFFormulaEvaluator)WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator();
//			workbooks.put("resultFuncB.xls", two);
//			workbooks.put("resultFuncB.xls", WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator());
			
//			FormulaEvaluator mainWorkbookEvaluator2 = WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator();
//			workbooks.put("resultFuncB.xls", mainWorkbookEvaluator2);

//			workbooks.put("resultFuncBA.xls", WorkbookFactory.create(new File(JettUtils.getXls("resultFuncB.xls"))).getCreationHelper().createFormulaEvaluator());

			// Attach them
//			mainWorkbookEvaluator.setupReferencedWorkbooks(workbooks);
//			HSSFFormulaEvaluator.set
			
			// Set up the workbook environment for evaluation
			 HSSFFormulaEvaluator mainFE = new HSSFFormulaEvaluator((HSSFWorkbook)workbook); 
			 HSSFFormulaEvaluator mainSe = (HSSFFormulaEvaluator)WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator();
			 String[] workbookNames = { excelFilePath, "resultFuncB.xls"};
			 HSSFFormulaEvaluator[] evaluators = { mainFE, mainSe};
			 for(HSSFFormulaEvaluator v:evaluators) logger.info(v._getWorkbookEvaluator());
			 HSSFFormulaEvaluator.setupEnvironment(workbookNames, evaluators);
			 
//			String[] workbookNames = {excelFilePath, "resultFuncB.xls"};
//			HSSFFormulaEvaluator[] ff = {(HSSFFormulaEvaluator)mainWorkbookEvaluator,two};
//			((HSSFFormulaEvaluator)mainWorkbookEvaluator).setupEnvironment(workbookNames, ff);
		    
//			ForkedEvaluator.c
//			WorkbookEvaluator evaluatorsa = (WorkbookEvaluator)HSSFEvaluationWorkbook.create(new HSSFWorkbook(inputStream));
//			WorkbookEvaluator[] evaluators = {, mainWorkbookEvaluator2};
//			CollaboratingWorkbooksEnvironment.setup(workbookNames, evaluators);

			// Evaluate
//			mainWorkbookEvaluator.evaluateAll();
			/* for FormulaEvaluator refrence other xls end*/

			Sheet sheet = workbook.getSheet("Validate");
//			logger.info("QQQ [" + sheet.getRow(2).getCell(0).getStringCellValue() + "]");
			Row row8 = sheet.createRow(10);
			
			row8.createCell(1).setCellFormula("A!A1");
			row8.createCell(2).setCellFormula("SUM(A!A1:A!A2)");
			row8.createCell(3).setCellFormula("[resultFuncB.xls]A1!A2");
//			row8.createCell(3).setCellFormula("A1!A5");
//			row8.createCell(3).setCellFormula("MATCH(3,A!A:A)");
			row8.createCell(4).setCellFormula("INDEX(A!B:B,MATCH(3,A!A:A))");
//			row8.createCell(5).setCellFormula("INDEX(A!B:B,MATCH(23,[resultFuncB.xls]A1!B:B))");
			
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(JettUtils.getXls(excelFilePath));
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("tryPoi1 done!");
	}

	public static void tryPoiQQ() {
		try {
			FileInputStream inputStream = new FileInputStream(new File("F:\\resultFuncA.xls"));
//			new HSSFFormulaEvaluator(new HSSFWorkbook(new POIFSFileSystem(inputStream))).notifyAll();
			HSSFWorkbook workbookA = new HSSFWorkbook(new POIFSFileSystem(inputStream));
//			triggerFormula(workbook);
			
			Sheet sheetAA = workbookA.getSheet("A");
			
			FileOutputStream outputStream = new FileOutputStream(new File("F:\\resultFuncB.xls"));
			HSSFWorkbook workbookB = new HSSFWorkbook(new POIFSFileSystem(inputStream));
			Sheet sheetBA = workbookB.createSheet(sheetAA.getSheetName());
//			sheetAA.
//			sheetAA.
			
			workbookB.write(outputStream);
			workbookB.close();
			outputStream.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("tryPoi2 done!");
	}

	public static void tryPoi2() {
		try {
			FileInputStream inputStream = new FileInputStream(new File("F:\\resultFuncA.xls"));
//			new HSSFFormulaEvaluator(new HSSFWorkbook(new POIFSFileSystem(inputStream))).notifyAll();
			HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
//			triggerFormula(workbook);
			
			Sheet sheet = workbook.getSheet("Validate");
			Row row8 = sheet.createRow(8);
			row8.createCell(2).setCellFormula("SUM(A!A1:A!A4)");
			
			workbook.setForceFormulaRecalculation(true);
			workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
			
//			XSSFFormulaEvaluator.evaluateAllFormulaCells(new XSSFWorkbook(new File("F:\\resultFuncA.xls")));
			inputStream.close();
			
			FileOutputStream outputStream = new FileOutputStream(new File("F:\\resultFuncA.xls"));
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("tryPoi2 done!");
	}
	
	public static void triggerFormula(HSSFWorkbook workbook) {
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		evaluator.clearAllCachedResultValues();
		HSSFSheet sheet = workbook.getSheetAt(0);
		int lastRowNo = sheet.getLastRowNum();
		for (int rownum=0; rownum <= lastRowNo; rownum++) {
			Row row;
			logger.info("\tRow[" + rownum + "]");
			if (sheet.getRow(rownum) != null) {
				row = sheet.getRow(rownum);
				int lastCellNo = row.getLastCellNum();
				for (int cellnum=0; cellnum < lastCellNo; cellnum++) {
					logger.info("\t\tCell[" + cellnum + "]");
					Cell cell;
					if (row.getCell(cellnum) != null) {
						cell = row.getCell(cellnum);
						evaluator.evaluateFormulaCell(cell);
						evaluator.notifySetFormula(cell);
						evaluator.notifyUpdateCell(cell);
						/*
						if(Cell.CELL_TYPE_STRING == cell.getCellType()) {
							cell.setCellFormula("=" + cell.getStringCellValue());
//							cell.setCellType(Cell.CELL_TYPE_FORMULA);
						}
						*/
						logger.info("evaluateInCell CellType[" + evaluator.evaluateInCell(cell).getCellType() + "]");
						/*
						if (Cell.CELL_TYPE_FORMULA == cell.getCellType()) {
							logger.info("\t\t\t FormulaValue[" + cell.getCellFormula() + "]");
							evaluator.evaluateFormulaCell(cell);
						}
						if(Cell.CELL_TYPE_STRING == cell.getCellType() && StringUtils.trim(cell.getStringCellValue()).startsWith("=")) {
							logger.info("\t\t\t Value[" + cell.getStringCellValue() + "]");
//							cell.setCellType(Cell.CELL_TYPE_FORMULA);
							evaluator.evaluateInCell(cell);
//							evaluator.evaluateFormulaCell(cell);
						}
						*/
					}
				}
			}
		}
	}
	
	public static void tryJettTemplate() {
		String template = JettUtils.getXls("templateFuncA.xls");
		String result = JettUtils.getXls("resultFuncA.xls");
		try {
			// 資料
			List<String> dataList = new ArrayList<String>();
			dataList.add("=A!A1");
			dataList.add("=SUM(A!A1:A!A2)");
			dataList.add("=INDEX(A!B:B,2)");
//			dataList.add("INDEX([resultFuncB.xls]A!B:B,3)");
//			dataList.add("INDEX(A!B:B,MATCH(3,[resultFuncA.xls]A!A:A))");
//			dataList.add("=INDEX(A!B:B,MATCH(3,[resultFuncA.xls]A!A:A,))");
			
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("data", dataList);
			data.put("dataResult", "=A1-B1+C1");

			dataList = new ArrayList<String>();
			dataList.add("A!A1");
			dataList.add("SUM(A!A1:A!A2)");
			dataList.add("INDEX(A!B:B,2)");
			dataList.add("INDEX(A!B:B,MATCH(3,A!A:A))");
			data.put("data2", dataList);
			data.put("dataResult2", "A4-B4+C4");
			
			List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
			beansList.add(data);
			
			List<String> templateSheetNames = new ArrayList<String>();
			templateSheetNames.add("validate");
			
			ExcelTransformer transformer = new ExcelTransformer();
			transformer.transform(template, result, templateSheetNames, templateSheetNames, beansList);
			
			logger.info("HelloExcelFunc done!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
}
