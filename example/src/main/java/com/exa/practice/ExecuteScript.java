package com.exa.practice;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ExecuteScript {
	

	String excelPath = "C:\\Users\\ragha\\Desktop\\practice\\TestData.xlsx";
	XSSFSheet funFlowSheet;
	XSSFSheet myWorkSheet;
	String TC_No;
	String testscriptsPath = "src\\main\\java\\com\\exa\\testscripts";
	public static WebDriver myWebDriver=null;
	/*
	 * public static void main(String[] args) throws InvalidFormatException,
	 * IOException { ExecuteScript executeScript = new ExecuteScript();
	 * executeScript.executeKeyword(); }
	 */

	public void run() throws InvalidFormatException, IOException {
		InitializeScript initializeScript = new InitializeScript();
		FrameworkUtility frameworkUtility = new FrameworkUtility();
		BaseTestScript baseTestScript = new BaseTestScript();
		LogGenerator logGenerator = new LogGenerator();
		logGenerator.configureLog4j();	
		LogGenerator.log.error("log generated");
		myWorkSheet = initializeScript.initalizeExcel(excelPath);
		funFlowSheet = initializeScript.funFlowSheet;
		ArrayList<String> keywords = getFunFlowValues();
		List<File> listClassFilesPath = new ArrayList<File>();
		listClassFilesPath = getAllScripts();
		List<String> classNames = new ArrayList<String>();
		classNames = listClassNames(listClassFilesPath);
		String browser = frameworkUtility.getConfigProperties("browser.name");
		System.out.println(browser);
		boolean isClassNotFound = false;
		for (String keyword : keywords) {
			for (String className : classNames) {
				Class<?> reqdClass;
				try {
					reqdClass = Class.forName(className);
					@SuppressWarnings("deprecation")
					Object reqdObject = reqdClass.newInstance();
					Method method = null;
					try {
						method = reqdClass.getDeclaredMethod(keyword);
					} catch (Exception e) {
						isClassNotFound = true;
					}
					if (!isClassNotFound) {
						myWebDriver = getWebDriver(browser);
						baseTestScript.setDriver(myWebDriver);
						method.invoke(reqdObject);
						if (myWebDriver != null) {
							myWebDriver.quit();
						}
						break;
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}
		
	public WebDriver getWebDriver(String browser) {		
		switch(browser){
		case("IE"):
			System.setProperty("webdriver.ie.driver", "");
			myWebDriver= new InternetExplorerDriver();
		break;
		case ("chrome"):
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			myWebDriver= new ChromeDriver();
	}
		return myWebDriver;
	}
		
	
	
	public List<String> listClassNames(List<File> listClassFilesPath) {
		List<String> classNames = new ArrayList<String>();
		for (File file : listClassFilesPath) {
			String path = file.getPath();
			String[] classFiles = path.split(Pattern.quote("src\\main\\java"));
			int len = classFiles.length;
			String className = ((classFiles[len - 1].replace(".java", "")).replace(File.separator, ".")).substring(1);
			classNames.add(className);	
		
		}
		return  classNames;
	}

	public ArrayList<String> getFunFlowValues() {
		ArrayList<String> keywords=new ArrayList<String>();
		for (int i = 1; i <= funFlowSheet.getLastRowNum(); i++) {
			for (int j = 0; j < funFlowSheet.getRow(i).getLastCellNum(); j++) {
				if (j == 1) {
					 TC_No = funFlowSheet.getRow(i).getCell(j).getStringCellValue();
				} else if (j > 1) {
					String keyWord = funFlowSheet.getRow(i).getCell(j).getStringCellValue();
					keywords.add(keyWord);
				}
			}
		}
		return keywords;
	}
	
	public List<File> getAllScripts(){
		List<File> listClassFilesPath = new ArrayList<File>();		
		listClassFilesPath = (List<File>) FileUtils.listFiles(new File(testscriptsPath), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		return listClassFilesPath;
	}
}
