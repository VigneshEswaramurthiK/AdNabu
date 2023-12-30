package com.adnabu.testrunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class Reporting {
public static void generateJVMReport(String jsonFile) {
	File file = new File("C:\\Users\\HP\\eclipse\\AdNabu\\ReportRepo\\");
	Configuration config = new Configuration(file, "report");
	
	config.addClassifications("Browser", "Chrome - V 120.0.6099.130");
	config.addClassifications("OS", "Windows 10 - 64bit");
	config.addClassifications("Java Version", "17.0.9");
	config.addClassifications("Environment", "Production");
	
	List<String> jsonFiles = new ArrayList<String>();
	jsonFiles.add(jsonFile);
	ReportBuilder builder = new ReportBuilder(jsonFiles, config);
	
	builder.generateReports();
}
}
