package com.devi.ai.ai_resume_analyzer.entity;

import lombok.Data;

import java.util.List;

@Data
public class ResumeAnalysisResponse {

    private double score;
    private List<String> strengths;
    private List<String> weaknesses;
    private List<String> suggestions;
}

