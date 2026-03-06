package com.devi.ai.ai_resume_analyzer.controller;

import com.devi.ai.ai_resume_analyzer.entity.ResumeAnalysisResponse;
import com.devi.ai.ai_resume_analyzer.service.OpenAIService;
import com.devi.ai.ai_resume_analyzer.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final OpenAIService openAIService;

    public ResumeController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @Autowired
    private PdfService pdfService;

    @PostMapping("/analyze")
    public ResumeAnalysisResponse analyze(@RequestParam("file") MultipartFile file) throws IOException {

        return openAIService.analyzeResume(pdfService.extractPdf(file));
    }
}
