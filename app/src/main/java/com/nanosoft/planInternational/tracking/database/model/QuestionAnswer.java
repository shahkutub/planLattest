package com.nanosoft.planInternational.tracking.database.model;

import java.util.ArrayList;

/**
 * Created by NanoSoft on 4/2/2017.
 */

public class QuestionAnswer {

    private int projectId ;
    private int questionId ;
    private String question = "";
    private String questionType = "";
    private String othersText = "";
    private ArrayList<String> answers = new ArrayList<>();

    public QuestionAnswer(int projectId, int questionId, String question, String questionType, String othersText, ArrayList<String> answers) {
        this.projectId = projectId;
        this.questionId = questionId;
        this.question = question;
        this.questionType = questionType;
        this.othersText = othersText;
        this.answers = answers;
    }


    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getOthersText() {
        return othersText;
    }

    public void setOthersText(String othersText) {
        this.othersText = othersText;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }
}
