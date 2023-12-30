package com.adnabu.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public static WebDriver driver;
	public static RequestSpecification reqSpec;
	public static Response response;

	// 1.Launch Browser
	public static void launchBrowser(String browser) {
		switch(browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
		}
	}

	// 2. Get entered url
	public static void goToUrl(String url) {
		if (url != null) {
			driver.get(url);
		}
	}
	// 3. To maximize the window
	public static void maximizeWindow() {
		driver.manage().window().maximize();
	}
	// 4.1 To insert values in text box
	public static void sendKeys(WebElement element, String data) {
		visibilityOf(element);
		if (isDisplayed(element) && isEnabled(element) && data != null) {
			element.sendKeys(data);
		}
	}
	//4.2 Insert text and press Enter in text box
	public static void sendKeysEnter(WebElement element, String data) {
		visibilityOf(element);
		if (isDisplayed(element) && isEnabled(element) && data != null) {
			element.sendKeys(data,Keys.ENTER);
		}
	}
	// 5.To click any buttons/check box.
	public static void click(WebElement element) {
		visibilityOf(element);
		if (isDisplayed(element) && isEnabled(element)) {
			element.click();
		}
	}
	// 6.To get Webpage title
	public static String getApplnTitle() {
		String title = driver.getTitle();
		return title;
	}
	// 7.To find Locator by Id
	public static WebElement findById(String attributeValue) {
		WebElement element = null;
		if (attributeValue != null) {
			element = driver.findElement(By.id(attributeValue));
		}
		return element;
	}
	// 8.To find Locator by name
	public static WebElement findByName(String attributeValue) {
		WebElement element = null;
		if (attributeValue != null) {
			element = driver.findElement(By.name(attributeValue));
		}
		return element;
	}
	// 9.To find Locator by className
	public static WebElement findByClass(String attributeValue) {
		WebElement element = null;
		if (attributeValue != null) {
			element = driver.findElement(By.className(attributeValue));
		}
		return element;
	}
	// 10.To find Locator by Xpath
	public static WebElement findByXpath(String attributeValue) {
		WebElement element = null;
		if (attributeValue != null) {
			element = driver.findElement(By.xpath(attributeValue));
		}
		return element;
	}
	// 11.To get the text present
	public static String getText(WebElement element) {
		String text = null;
		visibilityOf(element);
		if (isDisplayed(element)) {
			text = element.getText();
		}
		return text;
	}
	// 12.To get the entered url of a page
	public static String getApplnCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
	// 13.To accept a Alert
	public static Alert acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
		return alert;
	}
	// 14.To dismiss a Alert
	public static Alert declineAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		return alert;
	}
	// 15.To insert a value in the particular textbox
	public static Alert alertSendkeys(String value) {
		Alert alert = null;
		if (value != null) {
			alert = driver.switchTo().alert();
			alert.sendKeys(value);
		}
		return alert;
	}
	// 16.Get the entered value from textbox(For 99% of fixed attribute
	// value)--->getAttribute()
	public static String getAttribute(WebElement element) {
		String attribute = null;
		visibilityOf(element);
		if (isDisplayed(element)) {
			attribute = element.getAttribute("value");
		}
		return attribute;
	}
	// 17.Get the entered value from textbox(For 1% of dynamic attribute
	// value)--->getAttribute()
	public static String getAttribute(WebElement element, String attributeName) {
		String attribute = null;
		visibilityOf(element);
		if (isDisplayed(element) && attributeName != null) {
			attribute = element.getAttribute(attributeName);
		}
		return attribute;
	}
	// 18.DropDown--->selectByIndex()
	public static void selectOptionByIndex(WebElement element, int index) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			Select select = new Select(element);
			select.selectByIndex(index);
		}
	}
	// 19.DropDown--->selectByValue()
	public static void selectOptionByValue(WebElement element, String value) {
		visibilityOf(element);
		if (value != null && isDisplayed(element)) {
			Select select = new Select(element);
			select.selectByValue(value);
		}
	}
	// 20.DropDown--->selectByVisibleText()
	public static void selectOptionByText(WebElement element, String text) {
		visibilityOf(element);
		if (text != null && isDisplayed(element)) {
			Select select = new Select(element);
			select.selectByVisibleText(text);
		}
	}
	// 21.DropDown--->getOptions()(get all the options in DD)
	public static List<WebElement> getOptionsInDd(WebElement element) {
		List<WebElement> options = null;
		visibilityOf(element);
		if (isDisplayed(element)) {
			Select select = new Select(element);
			options = select.getOptions();
		}
		return options;
	}
	// 22.DropDown--->getAllSelectedOptions()(get all the options in DD)
	public static List<WebElement> getAllSelectedOptionsInDd(WebElement element) {
		List<WebElement> options = null;
		visibilityOf(element);
		if (isDisplayed(element)) {
			Select select = new Select(element);
			options = select.getOptions();
		}
		return options;
	}
	// 23.DropDown--->getFirstSelectedOption()
	public static WebElement getFirstSelectedOptionsInDd(WebElement element) {
		WebElement element1 = null;
		visibilityOf(element);
		if (isDisplayed(element)) {
			Select select = new Select(element);
			element1 = select.getFirstSelectedOption();
		}
		return element1;
	}
	// 24.verify in DropDown in multi select option?
	public static boolean ddnMultiValueSelection(WebElement element) {
		visibilityOf(element);
		Select select = new Select(element);
		boolean multiple = select.isMultiple();
		System.out.println(multiple);
		return multiple;
	}
	// 25.DropDown--->deselectByIndex()
	public static void deSelectOptionByIndex(WebElement element, int index) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			Select select = new Select(element);
			select.deselectByIndex(index);
		}
	}
	// 26.DropDown--->deselectByValue()
	public static void deSelectOptionByValue(WebElement element, String value) {
		visibilityOf(element);
		if (isDisplayed(element) && value != null) {
			Select select = new Select(element);
			select.deselectByValue(value);
		}
	}
	// 27.DropDown--->deselectByVisibleText()
	public static void deSelectOptionByVisibleText(WebElement element, String text) {
		visibilityOf(element);
		if (isDisplayed(element) && text != null) {
			Select select = new Select(element);
			select.deselectByVisibleText(text);
		}
	}
	// 28.DropDown--->deselectAll()
	public static void deSelectOptionsAll(WebElement element) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			Select select = new Select(element);
			select.deselectAll();
		}
	}
	// 29.To insert a value using JS
	public static void insertValueJs(WebElement element, String data) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].setAttribute('value','" + data + "')", element);
	}
	// 30.To click using JS
	public static void clickJs(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", element);
	}
	// 31.To scroll the page
	public static void scrollPageJs(WebElement element, String data) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView()", element);
	}
	// 32.switch to frame By index-->
	public static WebDriver switchFrameByIndex(int index) {
		WebDriver frame = driver.switchTo().frame(index);
		return frame;
	}
	// 33.switch to frame By id-->
	public static WebDriver switchFrameById(String id) {
		WebDriver frame = null;
		if (id != null) {
			frame = driver.switchTo().frame(id);
		}
		return frame;
	}
	// 34.switch to frame By name-->
	public static WebDriver switchFrameByName(String name) {
		WebDriver frame = null;
		if (name != null) {
			frame = driver.switchTo().frame(name);
		}
		return frame;
	}
	// 35.switch to frame By iframe Tag(WebElement)-->
	public static WebDriver switchFrameByWebElement(WebElement element) {
		WebDriver frame = null;
		visibilityOf(element);
		if (isDisplayed(element)) {
			frame = driver.switchTo().frame(element);
		}
		return frame;
	}
	// 36.Return to parent frame
	public static WebDriver switchToParentFrame() {
		WebDriver frame = driver.switchTo().parentFrame();
		return frame;
	}
	// 37.Return to window from frame
	public static WebDriver switchFrameToWindow() {
		WebDriver frame = driver.switchTo().defaultContent();
		return frame;
	}
	// 38.To print number of rows in the table
	public static void numberOfRowsInTable(WebElement element) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			List<WebElement> tableRow = element.findElements(By.tagName("tr"));
			System.out.println(tableRow.size());
		}
	}
	// 39.To print number of columns in the table
	public static void numberOfColumnInTable(WebElement element) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			List<WebElement> tableColumn = element.findElements(By.tagName("th"));
			System.out.println(tableColumn.size());
		}
	}
	// 40.To print number of data in the table
	public static void numberOfDataInTable(WebElement element) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			List<WebElement> tableData = element.findElements(By.tagName("td"));
			System.out.println(tableData.size());
		}
	}
	// 41.switch to window by windowId
	public static WebDriver switchByWindowId(String id) {
		WebDriver window = null;
		if (id != null) {
			window = driver.switchTo().window(id);
		}
		return window;
	}
	// 42.switch to window by windowUrl
	public static WebDriver switchByWindowUrl(String url) {
		WebDriver window = null;
		if (url != null) {
			window = driver.switchTo().window(url);
		}
		return window;
	}
	// 43.switch to window by windowTitle
	public static WebDriver switchByWindowTitle(String title) {
		WebDriver window = null;
		if (title != null) {
			window = driver.switchTo().window(title);
		}
		return window;
	}
	// 44.To Get child window
	public static String getChildWindow() {
		String windowHandle = driver.getWindowHandle();
		return windowHandle;
	}
	// 45.To Get all windows
	public Set<String> getAllWindows() {
		Set<String> windowHandles = driver.getWindowHandles();
		return windowHandles;
	}
	// 46.Implicit wait
	public static void implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	// 47.Implicit wait default
	public static void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	// 48.Explicit wait--->visibilityOfElementLocated
	public static WebElement visibilityOf(int seconds, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		return until;
	}
	// 49.Explicit wait--->visibilityOfElementLocated
	public static WebElement visibilityOf(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement until = wait.until(ExpectedConditions.visibilityOf(element));
		return until;
	}
	// 50.Explicit wait--->elementToBeClickable
	public static WebElement explicitWaitByClick(int seconds, String xpathExpression) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		WebElement until = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathExpression)));
		return until;
	}
	// 51.clear textbox
	public static void clearTextbox(WebElement element) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			element.clear();
		}
	}
	// 52.a TakesScreenShot for a page (FILE format)
	public static void takesScreenshotPage(String name) throws IOException {
		if (name != null) {
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File s = screenshot.getScreenshotAs(OutputType.FILE);
			File d = new File(getProjectPath () + "\\Screenshots\\" + name + ".png");
			Files.copy(s, d);
		}
	}
	//52.b TakesScreenshot for a page (BYTE format)
	public static byte[] takesScreenshotByte() {
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		byte[] b = screenshot.getScreenshotAs(OutputType.BYTES);
		return b;
	}

	// 53.TakesScreenshot for a element
	public static void takesScreenshotElement(WebElement element, String name) throws IOException {
		if (isDisplayed(element) && name != null) {
			File s = element.getScreenshotAs(OutputType.FILE);
			File d = new File(getProjectPath () + "\\Screenshots\\" + name + ".png");
			Files.copy(s, d);
		}
	}
	// 54.MouseOverAction for single option
	public static void mouseOverActions(WebElement element) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			Actions actions = new Actions(driver);
			actions.contextClick(element).perform();
		}
	}
	// 55.DragAndDrop
	public static void mouseDragAndDrop(WebElement element1, WebElement element2) {
		visibilityOf(element1);
		visibilityOf(element2);
		if (isDisplayed(element1) && isDisplayed(element2)) {
			Actions actions = new Actions(driver);
			actions.dragAndDrop(element1, element2).perform();
		}
	}
	// 56.RightClick
	public static void mouseRightClick(WebElement element) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			Actions actions = new Actions(driver);
			actions.contextClick(element).perform();
		}
	}
	// 57.DoubleClick
	public static void mouseDoubleClick(WebElement element) {
		visibilityOf(element);
		if (isDisplayed(element)) {
			Actions actions = new Actions(driver);
			actions.doubleClick(element).perform();
		}
	}
	// 58.Insert value in Textbox and enter
	public static void insertAndEnterParallely(WebElement element, String value) {
		visibilityOf(element);
		if (isDisplayed(element) && value != null) {
			element.sendKeys(value, Keys.ENTER);
		}
	}
	// 59.Refresh page
	public static void refreshPage() {
		driver.navigate().refresh();
	}
	// 60.To forward a page
	public static void forwardpage() {
		driver.navigate().forward();
	}
	// 61.To back a page
	public static void backPage() {
		driver.navigate().back();
	}
	// 62.navigate to()
	public static void navigateTo(String url) {
		if (url != null) {
			driver.navigate().to(url);
		}
	}
	// 63.verify isSelected(rdo btn/check Box)
	public static boolean isSelected(WebElement element) {
		boolean b = element.isSelected();
		System.out.println(b);
		return b;
	}
	// 64.verify isEnabled(text/button-->edit/click-->yes/no
	public static boolean isEnabled(WebElement element) {
		boolean b = element.isEnabled();
		return b;
	}
	// 65.verify isDisplayed
	public static boolean isDisplayed(WebElement element) {
		boolean b = element.isDisplayed();
		return b;
	}
	// 66.close all windows
	public static void quitWindows() {
		driver.quit();
	}
	// 67.close current window
	public static void closeWindows() {
		driver.close();
	}
	// 68.Read the cell value
	public static String getCellData(String sheetName, int rownum, int cellnum) throws IOException {
		String res = null;
		File file = new File(getProjectPath () + "\\Data\\Data.XLSX");
		FileInputStream fileInputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellnum);
		CellType type = cell.getCellType();
		switch (type) {
		case STRING:
			res = cell.getStringCellValue();
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				Date dateCellValue = cell.getDateCellValue();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
				res = dateFormat.format(dateCellValue);
			} else {
				double numericCellValue = cell.getNumericCellValue();
				long round = Math.round(numericCellValue);
				if (numericCellValue == round) {
					res = String.valueOf(round);
				} else {
					res = String.valueOf(numericCellValue);
				}
			}
			break;
		default:
			break;
		}
		return res;
	}
	// 69. update the data
	public static void updateCelldata(String sheetName, int rownum, int cellnum, String oldData, String newData)
			throws IOException {
		File file = new File(getProjectPath () + "\\Data\\Data.XLSX");
		FileInputStream fileInputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellnum);
		String value = cell.getStringCellValue();
		if (value.equals(oldData)) {
			cell.setCellValue(newData);
		}
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
	}
	// 70. To write the cell data
	public static void writeCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {
		File file = new File(getProjectPath () + "\\Data\\Data.XLSX");
		FileInputStream fileInputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		org.apache.poi.ss.usermodel.Cell cell = row.getCell(cellnum);
		cell.setCellValue(data);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
	}
	// 71. To write the cell data
	public static void createCellData(String sheetName, int rownum, int cellnum, String data) throws IOException {
		File file = new File(getProjectPath () + "\\Data\\Data.XLSX");
		FileInputStream fileInputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rownum);
		org.apache.poi.ss.usermodel.Cell cell = row.createCell(cellnum);
		cell.setCellValue(data);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		workbook.write(fileOutputStream);
	}
	//72.a Press Tab and Enter
	public static void keyTabEnter() throws AWTException {
		Robot rbt = new Robot();
		rbt.keyPress(KeyEvent.VK_TAB);
		rbt.keyRelease(KeyEvent.VK_TAB);
		rbt.keyPress(KeyEvent.VK_ENTER);
		rbt.keyRelease(KeyEvent.VK_ENTER);
	}
	//72.b Down + Enter
	public static void keyDownEnter() throws AWTException {
		Robot rbt = new Robot();
		rbt.keyPress(KeyEvent.VK_KP_DOWN);
		rbt.keyRelease(KeyEvent.VK_KP_DOWN);
		rbt.keyPress(KeyEvent.VK_ENTER);
		rbt.keyRelease(KeyEvent.VK_ENTER);
	}
	//72.c Down + Down + Enter
	public static void keyDownDownEnter() throws AWTException {
		Robot rbt = new Robot();
		rbt.keyPress(KeyEvent.VK_KP_DOWN);
		rbt.keyRelease(KeyEvent.VK_KP_DOWN);
		rbt.keyPress(KeyEvent.VK_KP_DOWN);
		rbt.keyRelease(KeyEvent.VK_KP_DOWN);
		rbt.keyPress(KeyEvent.VK_ENTER);
		rbt.keyRelease(KeyEvent.VK_ENTER);
	}
	//73.To get the ProjectpathConfig
	public static String getProjectPath() {
		String value = System.getProperty("user.dir");
		return value;
	}

	// 74.to get propertyFile Value
	public static String getPropertyFileValue (String key) throws FileNotFoundException, IOException {
		Properties property = new Properties();
		property.load(new FileInputStream(getProjectPath () + "\\config\\config.properties"));

		Object obj = property.get(key);
		String value = (String) obj;
		return value;
	}
	// 75. GenerateJVMReport
//	public static void generateJVMReport(String reportFolder, String projectName, String jsonFile, String Key1, String Value1, String Key2, String Value2) {
		//1. Mention the path where the report has to be saved
//		File reportFile = new File(getProjectPath() + reportFolder);

		//2. Create the object for configuration class
//		Configuration configuration = new Configuration(reportFile, projectName);

		//3. Key - Value => Browser, Version, OS, Sprint, Testing Environment
//		configuration.addClassifications(Key1, Value1);
//		configuration.addClassifications(Key2, Value2);

		//4. Create the object for Report Builder class => Fetch result from json
//		List<String> jsonFiles = new ArrayList<String>();
//		jsonFiles.add(jsonFile);
//		ReportBuilder builder = new ReportBuilder(jsonFiles, configuration);

		//5. Generate the report
//		builder.generateReports();
//	}

	
	
//=====Rest Assured=====
//----------------------
	// 01. Add header
	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
		}
	// 02. Add body
	public void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}
	// 03. Add request body
	public Response addReqType(String reqType, String endPoint) {
		switch(reqType) {
		case "GET":
			response = reqSpec.get(endPoint);
			break;
		case "POST":
			response = reqSpec.get(endPoint);
			break;
		case "PUT":
			response = reqSpec.get(endPoint);
			break;
		case "PATCH":
			response = reqSpec.get(endPoint);
			break;
		case "DELETE":
			response = reqSpec.get(endPoint);
			break;
		default:
			break;
		}
		return response;
	}
	// 04. Get Response Status Code
	public int getResponseStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}
	// 05. Get Response body as pretty string
	public String getResponseAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;
	}
	// 06. Add basic authentication
	public void addBasicAuth(String userName, String pass) {
		reqSpec.given().auth().basic(userName, pass);
	}
}
