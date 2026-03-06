package com.devi.ai.ai_resume_analyzer.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfService {

    public String extractPdf (MultipartFile file) throws IOException {

        PDDocument pdDocument = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfTextStripper = new PDFTextStripper();
        String pdfContent = pdfTextStripper.getText(pdDocument);
        pdDocument.close();
        return pdfContent;

    }
}
