package com.Project.QuizApp.controller;

import com.Project.QuizApp.model.Options;
import com.Project.QuizApp.model.Question;
import com.Project.QuizApp.repository.QuestionRepository;
import com.Project.QuizApp.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class QuestionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;


    @PostMapping("/create")
    public String createQuestions(@RequestBody List<Question> question)
    {
        try {
            questionService.createQuestions(question);
            logger.info("{}","Created Question successfully...");
            return "Created the Questions Successfully....";
        }catch(Exception e)
        {
            e.printStackTrace();
            logger.error("{}","Unable to generate the Question");
            return "Failed to generate the questions";
        }
    }

    @GetMapping("/fetchList")
    public ResponseEntity<List<Question>> getQuestionsList()
    {
        List<Question> questionList = questionService.getQuestionList();
        if(questionList.isEmpty())
        {
            logger.info("{}","No Questions Available..");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {

            logger.info("{}","fetched QuestionList Successfully");

            return new ResponseEntity<>(questionList,HttpStatus.OK);

        }
    }

    //Reading a Question by id

    @GetMapping("/fetch/{id}")
    public ResponseEntity<Question> readById(@PathVariable("id") Integer id)
    {
        Optional<Question> Question = questionRepository.findById(id);
        if(Question.isPresent()) {
            return new ResponseEntity<>(questionService.findById(id),HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(questionService.findById(id),HttpStatus.NOT_FOUND);
        }
    }

    // updating the question by id

    @PutMapping("/update/{id}")
    public String updateQuestionById(@RequestBody Question question, @PathVariable("id") Integer id)
    {
        Optional<Question> id1 = questionRepository.findById(id);
        if(id1.isPresent()){
            questionService.updateQuestion(id,question);
            System.out.println("Question " +id + " updated successfully...");
            return "Question " +id + " updated successfully...";
        }else
        {
            System.out.println("Question with the Id " +id + " not present....");
            return "Question with the Id " +id + " not present....";
        }


    }

    // Deleting a single question

    @DeleteMapping("/question/delete/{id}")
    public String deleteQuestionById(@PathVariable("id") Integer id)
    {
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()) {
            questionService.DeleteQuestionsbyId(id);
            logger.info("{}", "Deleted question with id :" + id + " successfully.");
            return "Deleted question with id :" + id + " successfully.";
        }else
        {
            logger.info("{}","Question with id :" +id + " not available....");
            return "Question with id :" +id + " not available....";
        }
    }

    @DeleteMapping("/question/deleteMultiple")
    public ResponseEntity deleteMultipleQuestions(@RequestParam("ids") List<Integer> ids)
    {

        for(int i=0;i<ids.size();i++) {
            Optional<Question> question = questionRepository.findById(ids.get(i));
            if (question.isPresent()) {
                questionRepository.deleteById(ids.get(i));
                logger.info("{}", "Questions with the ids : " + ids.get(i) + " were deleted....");
            } else {
                logger.info("{}", "Questions with the ids : " + ids.get(i) + " not present");
            }
        }
        /*List<Question> questions = questionRepository.findAllById(ids);
        if(questions != null)
        {
            for(int i=0;i<questions.size();i++)
            questionService.DeleteMultipleQuestions(ids);
            return new ResponseEntity(HttpStatus.OK);
        }else
        {
            logger.error("{}","Questions with these ids are not available..");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }*/
        return new ResponseEntity(HttpStatus.OK);
    }

}
