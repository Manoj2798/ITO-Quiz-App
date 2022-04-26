package com.Project.QuizApp.service;


import com.Project.QuizApp.Exception.ResourceNotFoundException;
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
    public boolean createQuestions(List<Question> question) {

        List<Question> questions = questionRepository.saveAll(question);
        if(questions != null)
            return true;
        else
            return false;
    }

    // fetching the questions List
    @Override
    public List<Question> getQuestionList() {

        return questionRepository.findAll();
    }

    @Override
    public Question findById(Integer id) {
        Optional<Question> q = questionRepository.findById(id);
        if(q.isEmpty())
        {
            System.out.println("Question with Question id : " +id + " not available");
        }else {

            return q.get();
        }
        return null;
    }

    @Override
    public Question updateQuestion(Integer id, Question question) {

        Optional<Question> q1 = questionRepository.findById(id);
       Question question1 = q1.get();
        if(question1 != null)
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
    public void deleteQuestionsById(Integer id) {
        questionRepository.deleteById(id);
    }


    @Override
    public List<Question> findQuestionsInAlphabetical() {
        return questionRepository.findByOrderByQuestionAsc();
    }

    @Override
    public Question findByQuestionName(String names) {
        return questionRepository.findByQuestion(names);
    }


    @Override
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }




}
