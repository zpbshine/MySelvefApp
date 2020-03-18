package com.example.administrator.myselvefapp.bean;

import java.util.ArrayList;

public class BenifitBean {
	public String market;
	public String olderPrice;
	public String nowPrice;
	public String content;

	
	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getOlderPrice() {
		return olderPrice;
	}

	public void setOlderPrice(String olderPrice) {
		this.olderPrice = olderPrice;
	}

	public String getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BenifitBean{" +
				"market='" + market + '\'' +
				", olderPrice='" + olderPrice + '\'' +
				", nowPrice='" + nowPrice + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
