package com.amazon;

import org.openqa.selenium.WebElement;

public class Product {

	private String productName;
	private String price;
	private WebElement addToCartButton;
	private WebElement productLink;

	public Product(String productName, String price, WebElement addToCartButton, WebElement productLink) {
		super();
		this.productName = productName;
		this.price = price;
		this.addToCartButton = addToCartButton;
		this.productLink = productLink;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public WebElement getAddToCartButton() {
		return addToCartButton;
	}

	public void setAddToCartButton(WebElement addToCartButton) {
		this.addToCartButton = addToCartButton;
	}

	public WebElement getProductLink() {
		return productLink;
	}

	public void setProductLink(WebElement productLink) {
		this.productLink = productLink;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", price=" + price + ", addToCartButton=" + addToCartButton
				+ ", productLink=" + productLink + "]";
	}

}
