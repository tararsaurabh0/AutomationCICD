package GrowNet.FrameworkBuilding.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    
    
    public static ExtentReports getReportObject() 
    {
        String path = System.getProperty("user.dir")+"reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web AUtomatoin Results");
        reporter.config().setDocumentTitle(path);
        
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Saurabh");
        extent.createTest(path); // creates result on the path mentioned
        
        return extent;
        
      
    }
}
