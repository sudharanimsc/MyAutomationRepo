package com.exa.practice;
import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class InitializeScript {
	
		XSSFWorkbook myWorkBook;
		XSSFSheet myWorkSheet;
		XSSFSheet funFlowSheet;
		
		/*
		 * public void initializeExcel() throws InvalidFormatException, IOException { //
		 * TODO Auto-generated method stub InitializeScript objExcelPractice = new
		 * InitializeScript(); XSSFSheet workSheet = objExcelPractice.initalizeExcel(
		 * "C:\\Users\\ragha\\Desktop\\practice\\TestData.xlsx"); int
		 * rowNum=objExcelPractice.getRowNumber(workSheet, "TC_5"); String cellValue =
		 * objExcelPractice.getCellValue(workSheet,rowNum,"LastName");
		 * System.out.println(cellValue); }
		 */
	
	public XSSFSheet initalizeExcel(String excelPath) throws InvalidFormatException, IOException {
		File f = new File(excelPath);
		myWorkBook = new XSSFWorkbook(f);
		myWorkSheet = myWorkBook.getSheet("TestData");
		funFlowSheet = myWorkBook.getSheet("FunFlow");
		return myWorkSheet;
	}
	public XSSFSheet initalizeFunFlow() {
		funFlowSheet = myWorkBook.getSheet("FunFlow");
		return funFlowSheet;
	}
	public int getRowNumber(String tcNo) {
		int rownum=-1;
		for(int i=0;i<myWorkSheet.getLastRowNum();i++) {
		String cellValue = myWorkSheet.getRow(i).getCell(1).getStringCellValue();
		if(cellValue.equalsIgnoreCase(tcNo)) {
			rownum=i;
			break;
		}
		}
		return rownum;
	}
	
	public int getColumnHeader(String colHeader) {
		// **************Header Value***********
		int cellNo = -1;
		Row row1 = myWorkSheet.getRow(0);
		for(int i=0;i<row1.getLastCellNum();i++) {
			String cellHeaderValue = row1.getCell(i).getStringCellValue();
			if (cellHeaderValue.equalsIgnoreCase(colHeader)) {
				cellNo = row1.getCell(i).getColumnIndex();
			}
		}
		/*
		 * Iterator<Cell> cellIterator1 = row1.cellIterator(); while
		 * (cellIterator1.hasNext()) { Cell cell = cellIterator1.next(); String
		 * cellHeaderValue = cell.getStringCellValue(); if
		 * (cellHeaderValue.equalsIgnoreCase(colHeader)) { cellNo =
		 * cell.getColumnIndex(); } }
		 */
		return cellNo;
	}
	public String getCellValue(String colHeader) {
		String cellValue = "";
		int rownum = getRowNumber("tc_no");
		int colIndex = getColumnHeader(colHeader);
		Row row1 = myWorkSheet.getRow(rownum);
		cellValue = row1.getCell(colIndex).getStringCellValue();
		//**************Actual Cell Value***********
		
		/*
		 * Iterator<Cell> cellIterator = row1.cellIterator(); while
		 * (cellIterator.hasNext()) { Cell cell = cellIterator.next(); int cellType =
		 * cell.getCellType(); if (cellType == cell.CELL_TYPE_NUMERIC) { cellValue =
		 * String.valueOf(cell.getNumericCellValue()); } else if (cellType ==
		 * cell.CELL_TYPE_STRING) { cellValue = cell.getStringCellValue(); } }
		 * 
		 */
		
		return cellValue;
	}

	public void getFunFlowValues() {
		for (int i = 1; i < funFlowSheet.getLastRowNum(); i++) {
			for (int j = 0; j < funFlowSheet.getRow(i).getLastCellNum(); j++) {
				if (j == 1) {
					String TC_NO = funFlowSheet.getRow(i).getCell(j).getStringCellValue();
				} else if (j > 1) {
					String keyWord = funFlowSheet.getRow(i).getCell(j).getStringCellValue();
				}
			}
		}		
	}
}
