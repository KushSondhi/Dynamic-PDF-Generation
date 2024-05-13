package com.pdf.entity;

import java.util.ArrayList;
import java.util.Map;

public class JSONData {

	private String seller;
	private String sellerGstin;
	private String sellerAddress;
	private String buyer;
	private String buyerGstin;
	private String buyerAddress;
	private ArrayList<Map<String, Object>> items = new ArrayList<>();

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public String getSellerGstin() {
		return sellerGstin;
	}

	public void setSellerGstin(String sellerGstin) {
		this.sellerGstin = sellerGstin;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getBuyerGstin() {
		return buyerGstin;
	}

	public void setBuyerGstin(String buyerGstin) {
		this.buyerGstin = buyerGstin;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public ArrayList<Map<String, Object>> getItems() {
		return items;
	}

	public void setItems(ArrayList<Map<String, Object>> items) {
		this.items = items;
	}

	public JSONData(String seller, String sellerGstin, String sellerAddress, String buyer, String buyerGstin,
			String buyerAddress, ArrayList<Map<String, Object>> items) {
		super();
		this.seller = seller;
		this.sellerGstin = sellerGstin;
		this.sellerAddress = sellerAddress;
		this.buyer = buyer;
		this.buyerGstin = buyerGstin;
		this.buyerAddress = buyerAddress;
		this.items = items;
	}

	public JSONData() {

	}

	@Override
	public String toString() {

		return "Seller : " + this.getSeller();
	}

}
