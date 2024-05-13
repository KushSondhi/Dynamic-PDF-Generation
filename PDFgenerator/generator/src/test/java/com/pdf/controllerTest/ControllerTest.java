package com.pdf.controllerTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.pdf.controller.JSONDataController;
import com.pdf.entity.JSONData;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

	@InjectMocks
	private JSONDataController jsonDataController;

	@Test
	public void TestGeneratePDF() throws IOException {

		Map<String, Object> jsonData = new HashMap<>();
		jsonData.put("seller", "ABC");
		byte[] pdfBytes = "SamplePDF".getBytes();

		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		header.setContentType(MediaType.APPLICATION_PDF);
		header.setContentDispositionFormData("pdfFile", "pdfData.pdf");

		ResponseEntity<byte[]> pdfByte = new ResponseEntity<byte[]>(pdfBytes, header, HttpStatus.OK);
		JSONDataController jsonControl = Mockito.mock(JSONDataController.class);
		jsonControl.generatePDF(jsonData);

		// Mockito.when(jsonControl.generatePDF(jsonData)).thenReturn(pdfByte);

	}

	@Test
	public void Test_JSONDATA() {

		JSONData jsonData = new JSONData();
		JSONData data = new JSONData("seller", "sGSTIN", "sAddress", "buyer", "buyerGsting", "buyerAddress",
				new ArrayList<>());

		data.setBuyer("buyer");
		data.setBuyerAddress("bAddress");
		data.setBuyerGstin("sGstin");
		data.setItems(new ArrayList<>());
		data.setSeller("seller");
		data.setSellerAddress("sAddress");
		data.setSellerGstin("sGST");
		data.getBuyer();
		data.getBuyerAddress();
		data.getBuyerGstin();
		data.getItems();
		data.getSeller();
		data.getSellerAddress();
		data.getSellerGstin();
	}

}
