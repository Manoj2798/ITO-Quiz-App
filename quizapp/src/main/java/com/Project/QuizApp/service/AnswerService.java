package com.Project.QuizApp.service;

import com.Project.QuizApp.model.Answer;

import java.util.List;

public interface AnswerService {

    /**
     * This method returns the Answers submitted by a particular candidate
     * @param id
     * @return list of Answers
     */
    List<Answer> findByCandidateId(Integer id);
}
