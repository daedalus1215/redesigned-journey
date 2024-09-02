package com.example.pdf_flattener_java;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdfFlattenerJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PdfFlattenerJavaApplication.class, args);
        System.out.println("Processing started");

        try {
            // Define input and output folders
            String inputFolder = "flatten_input";
            String outputFolder = "flatten_output";

            // Load the PDF file from the input folder
            File inputFile = new File(inputFolder + "/document.pdf");
            try (PDDocument document = Loader.loadPDF(inputFile)) {
                String baseName = inputFile.getName().replaceFirst("[.][^.]+$", ""); // Remove extension

                // Create the output directory structure
                File outputDir = new File(outputFolder + "/" + baseName + "_images");
                if (!outputDir.exists()) {
                    outputDir.mkdirs();
                }

                PDFRenderer pdfRenderer = new PDFRenderer(document);
                for (int page = 0; page < document.getNumberOfPages(); ++page) {
                    BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
                    File outputFile = new File(outputDir, "page-" + page + ".png");
                    ImageIO.write(image, "PNG", outputFile);
                }
                System.out.println("Processing completed. Images saved to: " + outputDir.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
