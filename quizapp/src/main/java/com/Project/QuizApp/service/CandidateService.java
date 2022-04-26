package com.Project.QuizApp.service;

import com.Project.QuizApp.model.Answer;
import com.Project.QuizApp.model.Candidate;
import com.Project.QuizApp.model.Question;
import com.Project.QuizApp.model.QuestionForm;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface CandidateService {

    /**
     * This method generates the result for a particular candidate
     * @param id
     * @return Returns the Response of the Result
     */
    String GenerateResult(Integer id);

    /**
     * This method is used to take Assessment and save details of the candidate
     * @param candidate
     */
    void candidateAssessment(Candidate candidate);

    /**
     * This method fetches the Question List based on valid candidate id
     * @param id
     * @return list of questions
     */
    List<QuestionForm> getQuestionList(Integer id);

    /**
     * This method is used to update the details of candidate, once the assessment has started
     * @param id
     * @param candidate
     */
    void setIsStarted(Integer id,Candidate candidate);

    /**
     * This method is used to submit the answers
     * @param answer
     */
    void saveAnswer(List<Answer> answer);

    /**
     * This method fetches all the candidates
     * @return List of Candidates
     */
    List<Candidate> findAllCandidates();

    /**
     * This methods returns a Candidate based on candidate Id
     * @param id
     * @return Candidate
     */
    Candidate findByCandidateId(Integer id);
}
