package com.Project.QuizApp.service;

import com.Project.QuizApp.model.Answer;
import com.Project.QuizApp.model.Candidate;
import com.Project.QuizApp.model.Question;
import com.Project.QuizApp.model.QuestionForm;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CandidateService {

    String GenerateResult(Integer id);

    void candidateAssessment(Candidate candidate);

    List<QuestionForm> getQuestionList(Integer id);

    void setIsStarted(Integer id,Candidate candidate);

    void saveAnswer(List<Answer> answer);

}
