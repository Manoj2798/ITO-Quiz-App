package com.Project.QuizApp.repository;

import com.Project.QuizApp.model.Answer;
import com.Project.QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    List<Answer> findByCandidateId(Integer candId);

}
