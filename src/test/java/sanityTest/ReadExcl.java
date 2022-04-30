package sanityTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcl {
	public static int rowCount;
	public static Sheet guru99Sheet;
	public static Map<String, String> vars;

	public static void readExcel(String filePath,String fileName,
			String sheetName, Logger logger) throws IOException{
        logger.info("\t\tReadExcl (readExcel) -> searching for file: " + filePath+fileName);
	    //Create an object of File class to open xlsx file
	    File file = new File(filePath+fileName);
	    
	    //Create an object of FileInputStream class to read excel file
	
	    FileInputStream inputStream = new FileInputStream(file);
	
	    Workbook guru99Workbook = null;
	
	    //Find the file extension by splitting file name in substring  and getting only extension name
	
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	
	    //Check condition if the file is xls file
	    if(fileExtensionName.equals(".xlsx")){
	        //If it is xls file then create object of HSSFWorkbook class
	    	logger.info("\t\tReadExcl (readExcel) -> the file is .xlsx");
	        guru99Workbook = new XSSFWorkbook(inputStream);
	    }

	    //Read sheet inside the workbook by its name
	    guru99Sheet = guru99Workbook.getSheet(sheetName);
	    logger.info("\t\tReadExcl (readExcel) -> export sheet: " + sheetName);
	    //Find number of rows in excel file
	    rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();
	    logger.info("\t\tReadExcl (readExcel) -> total rows ("+rowCount + ")" );
	    // Iterators to traverse over
	    Iterator<Row> iterator = guru99Sheet.iterator();
	    // We are taking vars as reference.
	    vars = new HashMap<>();
	    while (iterator.hasNext()) {
	        // Get a row in sheet
	        Row nextRow = iterator.next();
	        // This is for a Row's cells
	        Iterator<Cell> cellIterator
	            = nextRow.cellIterator();
	        
	        
	        // Adding up to the Map
	        Cell nextCell = cellIterator.next();
	        vars.put(nextCell.getStringCellValue(), 
	        		cellIterator.next().getStringCellValue());
	    }
    }

	public static int getRowcount() {
		return rowCount;
	}

	public static Sheet getsheet() {
		return guru99Sheet;
	}

	public static String getValue(String key,Logger logger) {
		String val = vars.get(key);
	    logger.info("\t\tReadExcl (readExcel) -> return velue: " + key + " = " + val);
		return val;
	}
}
