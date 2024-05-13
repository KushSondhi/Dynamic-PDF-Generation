package com.pdf.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdf.entity.JSONData;

@RestController
public class JSONDataController {

	@PostMapping("/data")
	public String processData(@RequestBody JSONData data) {

		return data.toString();
	}

	@PostMapping("/generatePDF")
	public ResponseEntity<byte[]> generatePDF(@RequestBody Map<String, Object> jsonData) throws IOException {

		String seller = (String) jsonData.get("seller");
		String sellerGstin = (String) jsonData.get("sellerGstin");
		String sellerAddress = (String) jsonData.get("sellerAddress");
		String buyer = (String) jsonData.get("buyer");
		String buyerGstin = (String) jsonData.get("buyerGstin");
		String buyerAddress = (String) jsonData.get("buyerGastAddress");
		ArrayList<Map<String, Object>> items = (ArrayList<Map<String, Object>>) jsonData.get("items");

		try (PDDocument pdf = new PDDocument()) {

			PDPage page = new PDPage();
			pdf.addPage(page);

			float pageWidth = page.getMediaBox().getWidth();
			float pageHeight = page.getMediaBox().getHeight();

			float half = pageWidth / 2;

			try (PDPageContentStream contentStream = new PDPageContentStream(pdf, page)) {

				contentStream.beginText();

				contentStream.newLineAtOffset(100, pageHeight - 100);
				contentStream.showText("Seller : ");
				contentStream.newLine();
				contentStream.showText(seller);
				contentStream.newLine();
				contentStream.showText(sellerAddress);
				contentStream.newLine();
				contentStream.showText(sellerGstin);
				contentStream.newLine();

				contentStream.newLineAtOffset(0, -50);

				contentStream.showText("Buyer :");
				contentStream.newLine();
				contentStream.showText(buyer);
				contentStream.newLine();
				contentStream.showText(buyerAddress);
				contentStream.newLine();
				contentStream.showText(buyerGstin);
				contentStream.newLine();

				contentStream.newLineAtOffset(0, -100);
				contentStream.moveTo(100, pageHeight - 300);
				contentStream.lineTo(300, pageHeight - 300);
				contentStream.moveTo(100, pageHeight - 400);
				contentStream.lineTo(300, pageHeight - 400);
				contentStream.stroke();

				contentStream.newLineAtOffset(0, -20);
				contentStream.showText("Item\n" + items.get(0));
				contentStream.newLineAtOffset(200, 0);

				contentStream.showText("Quantity\n" + items.get(1));
				contentStream.newLineAtOffset(300, 0);
				contentStream.showText("Rate\n" + items.get(2));
				contentStream.newLineAtOffset(400, 0);
				contentStream.showText("Amount\n" + items.get(3));

				contentStream.endText();
			} catch (Exception e) {
				e.getStackTrace();
			}

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			pdf.save(outputStream);
			pdf.close();

			byte[] pdfBytes = outputStream.toByteArray();

			org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
			header.setContentType(MediaType.APPLICATION_PDF);
			header.setContentDispositionFormData("pdfFile", "pdfData.pdf");

			ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(pdfBytes, header, HttpStatus.OK);

			return responseEntity;

		}
	}

}
