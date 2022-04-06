package com.Project.QuizApp.controller;

import com.Project.QuizApp.model.Answer;
import com.Project.QuizApp.model.Candidate;
import com.Project.QuizApp.model.Question;
import com.Project.QuizApp.model.QuestionForm;
import com.Project.QuizApp.repository.CandidateRepository;
import com.Project.QuizApp.repository.QuestionRepository;
import com.Project.QuizApp.service.AnswerService;
import com.Project.QuizApp.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CandidateController {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    CandidateService candidateService;

    @Autowired
    AnswerService answerService;

    // Getting candidate id and Insert name and Email in the DB
    @PostMapping("/candidate")
    public String candidateAssessment(@RequestBody Candidate candidate)
    {
        // checking for matching email ids in the Candidate Table..
        List<Candidate> candidateList = candidateRepository.findAll();
        for(Candidate i : candidateList)
        {
            if(i.getEmailId().equalsIgnoreCase(candidate.getEmailId()))
            {
                return "Email Id Exits, Please Enter the Request Again";
            }
        }
//        candidate.setIsStarted(false);
//        candidate.setIsSubmit(false);
        candidateService.candidateAssessment(candidate);
        return "Candidate with Details : " +"\nCandidate Id : " +candidate.getCandidateId() + "\nCandidate Name : " +candidate.getName() + "\n has been created successfully.";
    }

    // Fetching the QuestionList if the candidate is present
    @GetMapping("/candidate/getQuestions")
    public List<QuestionForm> getQuestionList(@RequestParam("candidateId") Integer id)
    {
       return candidateService.getQuestionList(id);
    }

    // Starting the Assessment
    @PutMapping("/candidate/start")
    public String isAssessmentStarted(@RequestParam("candidateId") Integer id,@RequestBody Candidate candidate)
    {
        Optional<Candidate> c1 = candidateRepository.findById(id);
        Candidate c = c1.get();
        if(c.getIsStarted() == true)
        {
            return "Exam Assessment Running...";
        }else {
            candidateService.setIsStarted(id, candidate);

            return "Exam Assessment has just Begun...";
        }
    }

    // submitting answer
    @PostMapping("/candidate/submit")
    public String saveAnswer(@RequestBody List<Answer> answer,@RequestParam("id") Integer id)
    {
        List<Answer> candidateAnswer = answerService.findByCandidateId(id);

        Optional<Candidate> candidate = candidateRepository.findById(id);
        // checking if the candidate has already submitted the answer
        Candidate c = candidate.get();

            if (c.getIsSubmit() == true) {
                return "Quiz Already Submitted" + "\nGood Luck for the Results (^_^)....";
            } else {

                if (candidate.isPresent()) {
                    candidateService.saveAnswer(answer);
                    c.setIsSubmit(true);
                    candidateRepository.save(c);
                    return "Candidate with Candidate id : " + id + " has submitted Quiz successfully...";
                } else {
                    return "Candidate with the id : " + id + " does not exist...";
                }
            }

    }


    // Fetching the answers of candidate from the Answer Table.
    @GetMapping("/candidate/fetchAnswer/{id}")
    public List<Answer> findAnswerByCandidateId(@PathVariable("id") Integer id)
    {
        return answerService.findByCandidateId(id);
    }


    // Generating the result
    @PostMapping("/candidate/generateResult/{id}")
    public String GenerateResult(@PathVariable("id") Integer id)
    {

        List<Question> questions = questionRepository.findAll();
        List<Answer> answer = answerService.findByCandidateId(id);
        if(answer.size() < questions.size())
        {
            return "Please attempt all the Quiz Questions..." +
                    "\nRequired :" +questions.size() +
                    "\nAttempted : " +answer.size() ;
        }else {
            return candidateService.GenerateResult(id);
        }
    }


}
