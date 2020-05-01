package com.utils;

import org.influxdb.dto.Point;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.concurrent.TimeUnit;

public class InfluxDBListener implements ITestListener {

	public void onTestStart(ITestResult iTestResult) {

	}

	public void onTestSuccess(ITestResult iTestResult) {
		this.postTestMethodStatus(iTestResult, "PASS");
	}

	public void onTestFailure(ITestResult iTestResult) {
		this.postTestMethodStatus(iTestResult, "FAIL");
	}

	public void onTestSkipped(ITestResult iTestResult) {
		this.postTestMethodStatus(iTestResult, "SKIPPED");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

	}

	public void onStart(ITestContext iTestContext) {

	}

	public void onFinish(ITestContext iTestContext) {
		this.postTestClassStatus(iTestContext);
	}

	private void postTestMethodStatus(ITestResult iTestResult, String status) {
		Point point = Point.measurement("testmethod").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				.tag("testclass", iTestResult.getTestClass().getName()).tag("name", iTestResult.getName())
				.tag("description", iTestResult.getMethod().getDescription()).tag("result", status)
				.addField("duration", (iTestResult.getEndMillis() - iTestResult.getStartMillis())).build();
		UpdateResults.post(point);
	}

	private void postTestClassStatus(ITestContext iTestContext) {
		Point point = Point.measurement("testclass").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				.tag("name", iTestContext.getAllTestMethods()[0].getTestClass().getName())
				.addField("duration", (iTestContext.getEndDate().getTime() - iTestContext.getStartDate().getTime()))
				.build();
		UpdateResults.post(point);
	}

}