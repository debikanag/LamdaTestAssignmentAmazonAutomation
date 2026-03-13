package com.amazon;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LamdaTestAssignment {

	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--start--maximized");
		WebDriver wd = new ChromeDriver(options);
		wd.get("https://www.amazon.in");

		WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(30));

		By searchTextBoxLocator = By.id("twotabsearchtextbox");

		WebElement searchTextBox = wait.until(ExpectedConditions.visibilityOfElementLocated(searchTextBoxLocator));
		searchTextBox.sendKeys("iphone");

		By searchButtonLocator = By.id("nav-search-submit-button");

		WebElement searchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButtonLocator));
		searchButton.click();

		By productListLocator = By.xpath("//div[@data-cy=\"asin-faceout-container\"]");
		List<WebElement> productList = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productListLocator));
		System.out.println(productList.size());

//		for (WebElement product : productList) {
//			System.out.println(product.getText());

		// productList.forEach(i -> System.out.println(i.getText()));//same as the above
		// for each loop

		List<Product> productPojoList = new ArrayList<Product>();

		for (WebElement product : productList) {
			By productNameLocator = By.tagName("h2");
			WebElement productNameElement = product.findElement(productNameLocator);

			String productName = productNameElement.getText();
			// System.out.println(productName);

			By productLinkLocator = By.xpath(".//a[contains(@class,\"a-link-normal\")]");
			WebElement productLinkElement = product.findElement(productLinkLocator);
			String productLink = productLinkElement.getAttribute("href");
			// System.out.println(productLink);

			By productPriceLocator = By.xpath(".//span[@class=\"a-price-whole\"]");
			List<WebElement> productPriceListElement = product.findElements(productPriceLocator);

			String productPrice;
			if (productPriceListElement.size() > 0) {

				productPrice = productPriceListElement.get(0).getText();
			} else {
				productPrice = "Not Available";
			}

			By addToCartButtonLocator = By.xpath(".//button[@name=\"submit.addToCart\"]");
			List<WebElement> addToCartButtonList = product.findElements(addToCartButtonLocator);

			WebElement addToCartButton = null;
			if (addToCartButtonList.size() > 0) {

				addToCartButton = addToCartButtonList.get(0);
			}

			Product productPojo = new Product(productName, productPrice, addToCartButton, productLinkElement);
			productPojoList.add(productPojo);

		}

		// productPojoList.forEach(i -> System.out.println(i));

		productPojoList.get(1).getProductLink().click();

		String parentHandle = wd.getWindowHandle();// unique identification of the tab which is currently handled by
													// webdriver
		System.out.println(parentHandle);

		Set<String> allWindowHandles = wd.getWindowHandles();

		String childHandle = null;
		for (String handle : allWindowHandles) {
			if (!(handle == parentHandle)) {
				childHandle = handle;
			}
		}
		System.out.println(allWindowHandles);
		System.out.println(childHandle);

		wd.switchTo().window(childHandle);

		By addToCartButtonLocator = By.xpath("//div[@id=\"newAccordionRow_0\"]//input[@id=\"add-to-cart-button\"]");
		WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButtonLocator));
		addToCartButton.click();

	}
}
