package com.Project.QuizApp.repository;

import com.Project.QuizApp.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {

    List<Answer> findByCandidateId(Integer candId);
}
