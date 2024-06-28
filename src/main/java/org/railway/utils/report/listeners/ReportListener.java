package org.railway.utils.report.listeners;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.railway.utils.ConfigLoader;
import org.railway.utils.Driver;
import org.railway.utils.report.extentreports.ExtentTestManager;
import org.railway.utils.report.logs.Log;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.railway.utils.report.helpers.CaptureHelpers;

import java.net.MalformedURLException;

import static org.railway.utils.report.extentreports.ExtentManager.getExtentReports;

public class ReportListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        String browser = System.getProperty("browser", ConfigLoader.getProperty("browser"));
        try{
            Driver.setupDriver(browser);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.info("Start testing " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", Driver.driver);
        try {
            CaptureHelpers.startRecord(iTestContext.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("End testing " + iTestContext.getName());
        getExtentReports().flush();
        try {
            CaptureHelpers.stopRecord();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " test is starting...");
        ExtentTestManager.saveToReport(iTestResult.getName(), iTestResult.getTestName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestName(iTestResult) + " test is passed.");
        ExtentTestManager.logMessage(Status.PASS, getTestDescription(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.error(getTestName(iTestResult) + " test is failed.");
        ExtentTestManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getName() + " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.warn(getTestName(iTestResult) + " test is skipped.");
        ExtentTestManager.logMessage(Status.SKIP, getTestName(iTestResult) + " test is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.error("Test failed but it is in defined success" + getTestName(iTestResult));
        ExtentTestManager.logMessage("Test failed but it is in defined success" + getTestName(iTestResult));
    }
}
