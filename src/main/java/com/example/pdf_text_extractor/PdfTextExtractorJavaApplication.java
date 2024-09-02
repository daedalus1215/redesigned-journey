package com.example.pdf_text_extractor;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

@SpringBootApplication
public class PdfTextExtractorJavaApplication {

    public static void main(String[] args) {
        String pdfPath = "path/to/your/document.pdf";
        try {
            // Create PdfReader and PdfDocument instances
            PdfReader reader = new PdfReader(pdfPath);
            // Extract text from each page
            try (PdfDocument pdfDoc = new PdfDocument(reader)) {
                // Extract text from each page
                for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
                    String pageText = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i));
                    System.out.println("Page " + i + " Text: ");
                    System.out.println(pageText);
                }
                // Close the PdfDocument
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
