package com.Project.QuizApp.service;

import com.Project.QuizApp.model.Answer;
import com.Project.QuizApp.model.Candidate;
import com.Project.QuizApp.model.Question;
import com.Project.QuizApp.repository.AnswerRepository;
import com.Project.QuizApp.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CandidateRepository candidateRepository;


    @Override
    public List<Answer> findByCandidateId(Integer id) {
        return answerRepository.findByCandidateId(id);
    }
}
