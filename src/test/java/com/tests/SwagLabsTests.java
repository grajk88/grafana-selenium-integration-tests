package com.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SwagLabsTests {

	WebDriver driver;

	public static int getRandomInteger(int maximum, int minimum) {
		return ((int) (Math.random() * (maximum - minimum))) + minimum;
	}

	@Test(description = "Launch and Login")
	public void launchApp() throws Exception {

		try {

			driver.get("https://www.saucedemo.com/");

			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("user-name")).sendKeys("secret_sauce");
			driver.findElement(By.id("user-name")).submit();

			driver.quit();
			
			System.out.println("Launch and Login - Done");

		} catch (Exception e) {

			e.printStackTrace();
			
		}

	}

	@Test(description = "Sort the Products", dependsOnMethods = "launchApp")
	public void sort() throws Exception {

		try {
			
			driver.get("https://www.saucedemo.com/");

			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("user-name")).sendKeys("secret_sauce");
			driver.findElement(By.id("user-name")).submit();

			Select sort = new Select(driver.findElement(By.className("product_sort_container")));
			sort.selectByValue("lohi");

			driver.quit();
			
			System.out.println("Sort the Products - Done");
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}

	}

	@Test(description = "Select a Product", dependsOnMethods = "sort")
	public void selectAProduct() throws Exception {

		try {
			
			driver.get("https://www.saucedemo.com/");

			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("user-name")).sendKeys("secret_sauce");
			driver.findElement(By.id("user-name")).submit();

			driver.findElement(By.linkText("Sauce Labs Backpack")).click();

			Assert.assertEquals(driver.getTitle().toString(), "Swag Labs");

			driver.quit();
			
			System.out.println("Select a Product - Done");
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}

	}

	@Test(description = "Go to About Page", dependsOnMethods = "selectAProduct")
	public void goToAboutPage() throws Exception {

		try {
			
			driver.get("https://www.saucedemo.com/");

			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("user-name")).sendKeys("secret_sauce");
			driver.findElement(By.id("user-name")).submit();

			driver.findElement(By.xpath("//*[@id=\"menu_button_container\"]/div/div[3]/div/button")).click();
			driver.findElement(By.linkText("About")).click();

			driver.quit();
			
			System.out.println("Go to About Page - Done");
			
		} catch (Exception e) {

			e.printStackTrace();
			
		}

	}

}