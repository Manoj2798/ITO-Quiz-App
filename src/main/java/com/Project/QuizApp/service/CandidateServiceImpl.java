package com.Project.QuizApp.service;

import com.Project.QuizApp.model.Answer;
import com.Project.QuizApp.model.Candidate;
import com.Project.QuizApp.model.Question;
import com.Project.QuizApp.model.QuestionForm;
import com.Project.QuizApp.repository.AnswerRepository;
import com.Project.QuizApp.repository.CandidateRepository;
import com.Project.QuizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService{

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @Override
    public void candidateAssessment(Candidate candidate) {

        // checking if the emailId is already present in the candidate Table
        List<Candidate> candidates = candidateRepository.findAll();
        boolean flag = false;
        for(Candidate i : candidates)
        {
            if(i.getEmailId().equals(candidate.getEmailId()))
            {
                System.out.println("Email id exits, Please enter the request again...");
                flag = true;
            }
        }
        if(flag==false)
            candidateRepository.save(candidate);
    }

    @Override
    public List<QuestionForm> getQuestionList(Integer id) {
        Optional<Candidate> candidate = candidateRepository.findById(id);
        List<Question> questionList;
        List<QuestionForm> list = new ArrayList<>();
        if(candidate.isPresent())
        {

            List<Question> questions = questionRepository.findAll();
//            System.out.println(questions);
            List<QuestionForm> questionForms = new ArrayList<>();

            for(Question i:questions)
            {
                questionForms.add(new QuestionForm(i.getQuestion(),i.getOption1(),i.getOption2(),i.getOption3(),i.getOption4()));
            }

            System.out.println(questionForms);

//            System.out.println("Inside the For Loop...");
//            questionList = questionRepository.findAll();
//            for(int i=0;i<questionList.size();i++)
//            {
//                list.add(new QuestionForm(questionList.get(i).getQuestion(),questionList.get(i).getOption1(),questionList.get(i).getOption2(),questionList.get(i).getOption3(),questionList.get(i).getOption4()));
//            }
            return questionForms;
        }else
        {
            System.out.println("candidate with the id : " +id + " does not exist...");
            return null;
        }


    }

    @Override
    public void setIsStarted(Integer id,Candidate candidate) {
        Optional<Candidate> c1 = candidateRepository.findById(id);
        if(c1.isPresent())
        {
            Candidate c = c1.get();
            c.setIsStarted(candidate.getIsStarted());
            c.setIsSubmit(candidate.getIsSubmit());
            c.setName(candidate.getName());
            c.setEmailId(candidate.getEmailId());

            candidateRepository.save(c);
        }else
        {
            System.out.println("Candidate with the id : "+id + " not present..");
        }
    }

    @Override
    public void saveAnswer(List<Answer> answer ) {

        answerRepository.saveAll(answer);
    }



    public String GenerateResult(Integer id)
    {

        // To get the candidate Detail
        Optional<Candidate> candidate = candidateRepository.findById(id);
        Candidate c = candidate.get();

        List<Answer> Answers = answerService.findByCandidateId(id);
        int count = 0;

        // Fetching the Question_id and Answer from the Questions table for verification.
        List<Question> Questions = questionRepository.findAll();
        HashMap<Integer,Integer> QA = new HashMap<>();

        for(Question i : Questions)
        {
            QA.put(i.getQuestionId(),i.getAnswer());
        }
        System.out.println(QA.size());
        // fetching the values from Answer Table

        HashMap<Integer,Integer> Ans = new HashMap<>();
        for(Answer i : Answers)
        {
            Ans.put(i.getQuestionId(),i.getAnswer());
        }
        System.out.println(Ans.size());



        List<Integer> l = new ArrayList<>(QA.values());
        List<Integer> m = new ArrayList<>(Ans.values());



        for(int i = 0;i<l.size();i++)
        {
            if(l.get(i) == m.get(i))
            {
                count++;

            }
        }
        int passing = Questions.size()-2;
        int incorrect = Questions.size() - count;
        if(count>=passing)
        {
            return "Congratulations, \n" +"CandidateId: "+c.getCandidateId() +" : " + "CandidateName : "+c.getName() + " ,you Move to the Next Round..." +
                    "\n Correct Answers : " +count + "\nIncorrect Answers : "+ incorrect;
        }else {
            return "The student with the Details : \n"+
                    "candidateID: " +c.getCandidateId() + " : " + "CandidateName " +c.getName() +
                    "\nhas got " + count + " questions correct, " + incorrect  + " Answers incorrect..." +
                    "\n Need to score minimum : " +passing + " questions correct..." + " Better Luck Next time...";
        }
    }


}
