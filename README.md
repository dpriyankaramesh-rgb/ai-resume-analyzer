# AI Resume Analyzer

An AI-powered backend application that analyzes resumes and provides structured insights using OpenAI.

## 🚀 Features

- Upload resume in **PDF format**
- Extract resume text using **Apache PDFBox**
- Analyze resume using **OpenAI API**
- Return structured insights including:
  - Resume Score
  - Strengths
  - Weaknesses
  - Suggestions for improvement

## 🛠 Tech Stack

- Java 17
- Spring Boot
- REST APIs
- OpenAI API
- Apache PDFBox
- Maven

## 📊 Sample API Response
{
  "score": 8.4,
  "strengths": [
    "Strong backend development experience"
  ],
  "weaknesses": [
    "Limited system design exposure"
  ],
  "suggestions": [
    "Add measurable achievements in projects"
  ]
}
