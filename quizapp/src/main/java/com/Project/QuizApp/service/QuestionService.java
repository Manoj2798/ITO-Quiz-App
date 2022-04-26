package com.Project.QuizApp.service;

import com.Project.QuizApp.model.Question;

import java.util.List;

public interface QuestionService {

    /**
     * This method is used to post list of questions
     * @param question
     */
    boolean createQuestions(List<Question> question);

    /**
     * This method fetches the Questions list
     * @return List of Questions
     */
    List<Question> getQuestionList();

    /**
     * This method return a question based on the question id
     * @param id
     * @return question
     */
    Question findById(Integer id);

     /**
     * This Method updates a particular question based on questionId if its present
     * @param id
     * @param question
     * @return updated Question
     */
    Question updateQuestion(Integer id,Question question);

    /**
     * This method will delete a particular question based on the question Id Passed
     * @param id
     */
    void deleteQuestionsById(Integer id);

    /**
     * This method will fetch all the questions in an alphabetical Order
     * @return List of Questions
     */
    List<Question> findQuestionsInAlphabetical();

    /**
     * This method finds a Particular Question by the question.
     * @param name
     * @return Question
     */
    Question findByQuestionName(String name);


    /**
     * This method fetches all the questions
     * @return List of Questions
     */
    List<Question> findAllQuestions();
}
