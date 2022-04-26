package com.Project.QuizApp.repository;

import com.Project.QuizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    public Question findByQuestion(String question);

    public List<Question> findByOrderByQuestionAsc();


}
