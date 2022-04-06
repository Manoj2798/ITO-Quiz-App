package com.Project.QuizApp.service;


import com.Project.QuizApp.model.Question;
import com.Project.QuizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    QuestionRepository questionRepository;

    // create Question or Questions
    @Override
    public void createQuestions(List<Question> question) {

        questionRepository.saveAll(question);
    }

    // fetching the questions List
    @Override
    public List<Question> getQuestionList() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(Integer id) {
        Optional<Question> q1 = questionRepository.findById(id);
        if(q1.isPresent())
        {
            return q1.get();
        }else
        {
            System.out.println("Invalid Question id... or Question not Present");
        }

        return null;
    }

    @Override
    public Question updateQuestion(Integer id, Question question) {

        Optional<Question> q1 = questionRepository.findById(id);
        Question question1 = q1.get();
        if(q1.isPresent())
        {

            question1.setQuestion(question.getQuestion());
            question1.setAnswer(question.getAnswer());
            question1.setOption1(question.getOption1());
            question1.setOption2(question.getOption2());
            question1.setOption3(question.getOption3());
            question1.setOption4(question.getOption4());
            questionRepository.save(question1);
        }else
        {
            System.out.println("Invalid question id... cannot update");
        }

        return question1;
    }

    @Override
    public void DeleteQuestionsbyId(Integer id) {
        questionRepository.deleteById(id);
    }




}
