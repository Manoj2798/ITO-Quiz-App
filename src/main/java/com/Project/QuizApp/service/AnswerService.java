package com.Project.QuizApp.service;

import com.Project.QuizApp.model.Answer;

import java.util.List;

public interface AnswerService {


    List<Answer> findByCandidateId(Integer id);
}
