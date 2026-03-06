package com.devi.ai.ai_resume_analyzer.service;

import com.devi.ai.ai_resume_analyzer.entity.OpenAIRequest;
import com.devi.ai.ai_resume_analyzer.entity.ResumeAnalysisResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResumeAnalysisResponse analyzeResume(String resumeText) {

        List<OpenAIRequest.Message> messages = List.of(
                new OpenAIRequest.Message("system", "You are an expert resume analyzer. Return analysis strictly only in valid JSON Format. Format:\n" +
                        "{\n" +
                        " \"score\": number between 1 and 10 ,\n" +
                        " \"strengths\": [],\n" +
                        " \"weaknesses\": [],\n" +
                        " \"suggestions\": []\n" +
                        "}\n" +
                        "\n" +
                        "Do not include markdown.\n" +
                        "Do not include explanation.\n" +
                        "Return JSON only"),
                new OpenAIRequest.Message("user", "Analyze this resume and give strengths, weaknesses, suggestion. Resume : " + resumeText)
        );
        
        OpenAIRequest openAIRequest = new OpenAIRequest();
        openAIRequest.setModel("gpt-4o-mini");
        openAIRequest.setMessages(messages);
        openAIRequest.setTemperature(0.3);
        openAIRequest.setMax_tokens(500);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<OpenAIRequest> entity = new HttpEntity<>(openAIRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(response.getBody());

        String content = root
                .path("choices")
                .get(0)
                .path("message")
                .path("content")
                .asText();

        ResumeAnalysisResponse resumeAnalysisResponse = mapper.readValue(content, ResumeAnalysisResponse.class);
        return resumeAnalysisResponse;
    }
}
