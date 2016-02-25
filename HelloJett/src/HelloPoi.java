import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import util.JettUtils;


public class HelloPoi {
	private static Logger logger = Logger.getLogger(HelloPoi.class);
	
	public static void main(String[] args) {
		readA();
//		try1();
	}
	
	public static String getAa() {
		String aa = "IF(INDEX(B:B,MATCH(\"102\",A:A,)) = INDEX(B:B,MATCH(\"101\",A:A,)) + INDEX(B:B,MATCH(\"104\",A:A,)), \"OK\", \"Error\")";
		
		if(true) aa = "=INDEX(B:B, MATCH(102,A:A))";
		logger.info("getAa [" + aa + "]");
		return aa;
	}
	
	public static void readA() {
		try {
			String xlsName = "A.xls";
			String sheetName = "A2";
			
			xlsName = "Book1.xls";
			sheetName = "Sheet1";
			
			FileInputStream inputStream = new FileInputStream(new File(getXls(xlsName)));
			Workbook workbook = new HSSFWorkbook(inputStream);
//			Workbook workbook = new Workbook
			
			Sheet sheet = workbook.getSheet(sheetName);
			Row row = null;
//			Sheet sheet = workbook.getSheetAt(0);
//			if(sheet == null) sheet = workbook.createSheet(sheetName);
//			logger.info("QQQ [" + sheet.getRow(2).getCell(0).getStringCellValue() + "]");
			
			// keied formula
			String keyStr = "";			// "A2!A1", "MATCH(3,A1!A:A)", "INDEX(B:B,MATCH(\"29999\",A:A,)"
			keyStr = "'F:\\[B3.xls]a'!C238";
//			keyStr = "A1";
//			keyStr = "=a!C238=Sheet3!";
//			keyStr = getAa();
//			keyStr = "A2!A1";
//			if(true) keyStr = "IF(INDEX(C:C,MATCH(\"19999\",A:A,)) = INDEX(C:C,MATCH(\"29999\",A:A,)) + INDEX(C:C,MATCH(\"39999\",A:A,)), \"OK\", \"Error\")";
			
			// compid
//			compid();
			
			// create result cell
			int baseMaxRow = sheet.getLastRowNum() + 10;
			int idxCell = 0;
			row = sheet.createRow(baseMaxRow);
			Cell tmpCell = null;
			tmpCell = row.createCell(idxCell++);
			tmpCell.setCellFormula(keyStr);
//			xlsA.getCreationHelper().createFormulaEvaluator().evaluateAll();
//			logger.info("tmpCell CellFormula [" + tmpCell.getCellFormula() + "]");
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//			Cell a = evaluator.evaluateInCell(tmpCell);
			String cellValue = getCellValue(evaluator.evaluateInCell(tmpCell));
			logger.info("evaluateFormulaCell [" + cellValue + "]");
			
//			row = sheet.getRow(0);
//			row.getCell(0);
//			getCellValue(evaluator.evaluateInCell(row.getCell(4)));
			
//			String tmpStr = "A2!A7";
//			sheet = xlsA.getSheet("A2");
			
//			row8.createCell(1).setCellFormula("A1!A1");
//			row8.createCell(2).setCellFormula("SUM(A1!A1:A1!A2)");
//			row8.createCell(3).setCellFormula("[resultFuncB.xls]A1!A2");
//			row8.createCell(3).setCellFormula("A1!A5");
//			row8.createCell(3).setCellFormula("MATCH(3,A1!A:A)");
//			row8.createCell(4).setCellFormula("INDEX(A1!B:B,MATCH(3,A1!A:A))");
//			row8.createCell(5).setCellFormula("INDEX(A1!B:B,MATCH(23,[resultFuncB.xls]A1!B:B))");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void compid() throws Exception {
		String bookNameA = "B3.xls";
		String bookNameB = "B3.xls";

		HSSFFormulaEvaluator wbA = new HSSFFormulaEvaluator(new HSSFWorkbook(new FileInputStream(new File(getXls(bookNameA)))));
		HSSFFormulaEvaluator feB = new HSSFFormulaEvaluator(new HSSFWorkbook(new FileInputStream(new File(getXls(bookNameB)))));

		// Set up the workbook environment for evaluation
		String[] workbookNames = { bookNameA, bookNameB};
		HSSFFormulaEvaluator[] evaluators = { wbA, feB};
		HSSFFormulaEvaluator.setupEnvironment(workbookNames, evaluators);
	}
	
	public static String getCellValue(Cell cell) {
//		cell.get
//		StringBuffer ar = new StringBuffer("");
//		logger.info(cell.getStringCellValue());
//        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//           return cell.getStringCellValue();
//        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
//        	return(cell.getNumericCellValue());
//        }else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
//        	return(evaluator.evaluateFormulaCell(cell));
//        }
//        else {
//        	return("");
//        }
        
        String cellValue = "";
        switch (cell.getCellType()) {
        	case Cell.CELL_TYPE_STRING:
        	    cellValue = cell.getStringCellValue();
        	    break;

        	case Cell.CELL_TYPE_FORMULA:
        	    cellValue = cell.getCellFormula();
        	    break;

        	case Cell.CELL_TYPE_NUMERIC:
        	    if (DateUtil.isCellDateFormatted(cell)) {
        	        cellValue = cell.getDateCellValue().toString();
        	    } else {
        	        cellValue = Double.toString(cell.getNumericCellValue());
        	    }
        	    break;

        	case Cell.CELL_TYPE_BLANK:
        	    cellValue = "";
        	    break;

        	case Cell.CELL_TYPE_BOOLEAN:
        	    cellValue = Boolean.toString(cell.getBooleanCellValue());
        	    break;

        	}
        logger.info("CellType[" + cell.getCellType() + "], cellValue[" + cellValue + "]");
        return cellValue;
	}
	
//	public static void readA1() {
//		FileInputStream fis = new FileInputStream("c:/temp/test.xls");
//		Workbook wb = new HSSFWorkbook(fis); //or new XSSFWorkbook("c:/temp/test.xls")
//		Sheet sheet = wb.getSheetAt(0);
//		FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
//
//		// suppose your formula is in B3
//		CellReference cellReference = new CellReference("B3"); 
//		Row row = sheet.getRow(cellReference.getRow());
//		Cell cell = row.getCell(cellReference.getCol()); 
//
//		CellValue cellValue = evaluator.evaluate(cell);
//
//		switch (cellValue.getCellType()) {
//		    case Cell.CELL_TYPE_BOOLEAN:
//		        System.out.println(cellValue.getBooleanValue());
//		        break;
//		    case Cell.CELL_TYPE_NUMERIC:
//		        System.out.println(cellValue.getNumberValue());
//		        break;
//		    case Cell.CELL_TYPE_STRING:
//		        System.out.println(cellValue.getStringValue());
//		        break;
//		    case Cell.CELL_TYPE_BLANK:
//		        break;
//		    case Cell.CELL_TYPE_ERROR:
//		        break;
//
//		    // CELL_TYPE_FORMULA will never happen
//		    case Cell.CELL_TYPE_FORMULA: 
//		        break;
//		}				
//	}

	public static void try1() {
		try {
			String xlsFileA = "A.xls";
			String xlsFileB = "B.xls";
			FileInputStream inputStream = new FileInputStream(new File(JettUtils.getXls(xlsFileA)));
			
			Workbook xlsA = new HSSFWorkbook(inputStream);
			
			/* for FormulaEvaluator refrence other xls beg*/
			// Create a FormulaEvaluator to use

//			FormulaEvaluator mainWorkbookEvaluator = xlsA.getCreationHelper().createFormulaEvaluator();
//			FormulaEvaluator mainWorkbookEvaluator = WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator();
//			mainWorkbookEvaluator.setDebugEvaluationOutputForNextEval(true);
//			mainWorkbookEvaluator.setIgnoreMissingWorkbooks(true);

			// Track the xlsA references
//			Map<String,FormulaEvaluator> xlsAs = new HashMap<String, FormulaEvaluator>();
			// Add this xlsA
//			xlsAs.put(xlsFileA, mainWorkbookEvaluator);
//			xlsAs.put("resultFuncB.xls", mainWorkbookEvaluator);
			
			// Add two others
//			HSSFFormulaEvaluator two = (HSSFFormulaEvaluator)WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator();
//			xlsAs.put("resultFuncB.xls", two);
//			xlsAs.put("resultFuncB.xls", WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator());
			
//			FormulaEvaluator mainWorkbookEvaluator2 = WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator();
//			xlsAs.put("resultFuncB.xls", mainWorkbookEvaluator2);

//			xlsAs.put("resultFuncBA.xls", WorkbookFactory.create(new File(JettUtils.getXls("resultFuncB.xls"))).getCreationHelper().createFormulaEvaluator());

			// Attach them
//			mainWorkbookEvaluator.setupReferencedWorkbooks(xlsAs);
//			HSSFFormulaEvaluator.set
			
			// Set up the xlsA environment for evaluation
			 HSSFFormulaEvaluator mainFE = new HSSFFormulaEvaluator((HSSFWorkbook)xlsA); 
			 HSSFFormulaEvaluator mainSe = (HSSFFormulaEvaluator)WorkbookFactory.create(new File("D:/EclipseIndigo/WorkSpace/HelloJett/xls/resultFuncB.xls")).getCreationHelper().createFormulaEvaluator();
			 String[] xlsANames = { xlsFileA, "resultFuncB.xls"};
			 HSSFFormulaEvaluator[] evaluators = { mainFE, mainSe};
			 for(HSSFFormulaEvaluator v:evaluators) logger.info(v._getWorkbookEvaluator());
			 HSSFFormulaEvaluator.setupEnvironment(xlsANames, evaluators);
			 
//			String[] xlsANames = {xlsFileA, "resultFuncB.xls"};
//			HSSFFormulaEvaluator[] ff = {(HSSFFormulaEvaluator)mainWorkbookEvaluator,two};
//			((HSSFFormulaEvaluator)mainWorkbookEvaluator).setupEnvironment(xlsANames, ff);
		    
//			ForkedEvaluator.c
//			WorkbookEvaluator evaluatorsa = (WorkbookEvaluator)HSSFEvaluationWorkbook.create(new HSSFWorkbook(inputStream));
//			WorkbookEvaluator[] evaluators = {, mainWorkbookEvaluator2};
//			CollaboratingWorkbooksEnvironment.setup(xlsANames, evaluators);

			// Evaluate
//			mainWorkbookEvaluator.evaluateAll();
			/* for FormulaEvaluator refrence other xls end*/

			Sheet sheet = xlsA.getSheet("Validate");
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
			FileOutputStream outputStream = new FileOutputStream(JettUtils.getXls(xlsFileA));
			xlsA.write(outputStream);
			xlsA.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		logger.info("tryPoi1 done!");
	}
	
	public static String getXls(String xlsName) {
		return "F:/" + xlsName;
	}
}
