package model;

import java.util.Date;

public class Currency {
	
	public Currency(String bankName, float buyValue, float saleValue){
		this.bankName = bankName;
		this.buyValue = buyValue;
		this.saleValue = saleValue;
		this.date = new Date();
	}
	
	public void setBuyValue(float buyValue){
		this.buyValue = buyValue;
	}
	
	public void setSaleValue(float saleValue){
		this.saleValue = saleValue;
	}
	
	public void setBankName(String bankName){
		this.bankName = bankName;
	}
	
	public float getBuyValue(){
		return buyValue;
	}
	
	public float getSaleValue(){
		return saleValue;
	}
	
	public String getBankName(){
		return bankName;
	}
	
	public Date getDate(){
		return date;
	}
	
	String bankName;
	Date date;
	float buyValue;
	float saleValue;

}
