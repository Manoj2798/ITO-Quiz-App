package com.Project.QuizApp.service;

import com.Project.QuizApp.model.Options;
import com.Project.QuizApp.model.Question;

import java.util.List;

public interface QuestionService {

    void createQuestions(List<Question> question);

    List<Question> getQuestionList();

    Question findById(Integer id);

    Question updateQuestion(Integer id,Question question);

    void DeleteQuestionsbyId(Integer id);


}
