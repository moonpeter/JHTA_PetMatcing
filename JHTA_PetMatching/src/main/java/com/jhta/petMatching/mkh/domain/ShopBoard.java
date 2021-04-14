package com.jhta.petMatching.mkh.domain;

import org.springframework.web.multipart.MultipartFile;

public class ShopBoard {
	private int				shop_num;
	private String 			shop_category;
	private String 			shop_title;
	private String 			shop_price;
	private String 			shop_country_of_origin;
	private String 			shop_brand;
	
	private String		 	shop_thumnail;
	private String 			shop_thumnail_original;
	private MultipartFile	shop_upload_thumnail;

	private String 			shop_grade;
	
	private String		 	shop_img_content;
	private String 			shop_img_content_original;
	private MultipartFile	shop_upload_img_content;

	private String 			shop_text_content;
	
	
	
	public int getShop_num() {
		return shop_num;
	}
	
	public void setShop_num(int shop_num) {
		this.shop_num = shop_num;
	}
	
	public String getShop_category() {
		return shop_category;
	}
	
	public void setShop_category(String shop_category) {
		this.shop_category = shop_category;
	}
	
	public String getShop_title() {
		return shop_title;
	}
	
	public void setShop_title(String shop_title) {
		this.shop_title = shop_title;
	}
	
	public String getShop_price() {
		return shop_price;
	}
	
	public void setShop_price(String shop_price) {
		this.shop_price = shop_price;
	}
	
	public String getShop_country_of_origin() {
		return shop_country_of_origin;
	}
	
	public void setShop_country_of_origin(String shop_country_of_origin) {
		this.shop_country_of_origin = shop_country_of_origin;
	}
	
	public String getShop_brand() {
		return shop_brand;
	}
	
	public void setShop_brand(String shop_brand) {
		this.shop_brand = shop_brand;
	}
	
	public String getShop_thumnail() {
		return shop_thumnail;
	}
	
	public void setShop_thumnail(String shop_thumnail) {
		this.shop_thumnail = shop_thumnail;
	}
	
	public String getShop_grade() {
		return shop_grade;
	}
	
	public void setShop_grade(String shop_grade) {
		this.shop_grade = shop_grade;
	}
	
	public String getShop_img_content() {
		return shop_img_content;
	}
	
	public void setShop_img_content(String shop_img_cotent) {
		this.shop_img_content = shop_img_cotent;
	}
	
	public String getShop_text_content() {
		return shop_text_content;
	}
	
	public void setShop_text_content(String shop_text_content) {
		this.shop_text_content = shop_text_content;
	}

	public MultipartFile getShop_upload_thumnail() {
		return shop_upload_thumnail;
	}

	public void setShop_upload_thumnail(MultipartFile shop_upload_thumnail) {
		this.shop_upload_thumnail = shop_upload_thumnail;
	}

	public MultipartFile getShop_upload_img_content() {
		return shop_upload_img_content;
	}

	public void setShop_upload_img_content(MultipartFile shop_upload_img_content) {
		this.shop_upload_img_content = shop_upload_img_content;
	}

	public String getShop_thumnail_original() {
		return shop_thumnail_original;
	}

	public void setShop_thumnail_original(String shop_thumnail_original) {
		this.shop_thumnail_original = shop_thumnail_original;
	}

	public String getShop_img_content_original() {
		return shop_img_content_original;
	}

	public void setShop_img_content_original(String shop_img_content_original) {
		this.shop_img_content_original = shop_img_content_original;
	}

	
}
