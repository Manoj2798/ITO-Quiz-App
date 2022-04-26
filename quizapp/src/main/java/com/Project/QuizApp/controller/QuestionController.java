package com.Project.QuizApp.controller;

import com.Project.QuizApp.model.Question;
import com.Project.QuizApp.repository.QuestionRepository;
import com.Project.QuizApp.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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


    @PostMapping("/createQuestions")
    public String createQuestions(@RequestBody List<Question> question)
    {
        boolean result = questionService.createQuestions(question);
        if(result) {
            logger.info("{}", "Created Question successfully...");
            return "Created the Questions Successfully....";
        }else
        {
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
        Question question = questionService.findById(id);
        if(question != null) {
            return new ResponseEntity<>(question,HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // updating the question by id

    @PutMapping("/update/{id}")
    public String updateQuestionById(@RequestBody Question question, @PathVariable("id") Integer id)
    {
        Question q1 = questionService.findById(id);
        if(q1 != null){
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
        Question question = questionService.findById(id);
        if(question != null) {
            questionService.deleteQuestionsById(id);
            logger.info("{}", "Deleted question with id :" + id + " successfully.");
            return "Deleted question with id :" + id + " successfully.";
        }else
        {
            logger.info("{}","Question with id :" +id + " not available....");
            return "Question with id :" +id + " not available....";
        }
    }

    // Deleting multiple Questions...
    @DeleteMapping("/question/deleteMultiple")
    public ArrayList<String> deleteMultipleQuestions(@RequestParam("ids") List<Integer> ids)
    {
        ArrayList<String> response = new ArrayList<>();
        for(int i=0;i<ids.size();i++) {
            Question question = questionService.findById(ids.get(i));
            if (question != null) {
                questionService.deleteQuestionsById(ids.get(i));
                logger.info("{}", "Questions with the ids : " + ids.get(i) + " were deleted....");
                response.add("Questions with the ids : " + ids.get(i) + " were deleted....");
            } else {
                logger.info("{}", "Questions with the ids : " + ids.get(i) + " not present");
                response.add("Questions with the ids : " + ids.get(i) + " not present....");
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

        return response;
    }

        @GetMapping("/fetchQuestions")
        public List<Question> getQuestionsInOrder()
        {
            return questionService.findQuestionsInAlphabetical();
        }

        @GetMapping("/GetQByName")
        public ResponseEntity<Question> getQuestionByName(@RequestParam("question") String question)
        {
            Question q = questionService.findByQuestionName(question);
            if(q != null)
            {
                return new ResponseEntity<>(q,HttpStatus.OK);
            }else
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }


}
