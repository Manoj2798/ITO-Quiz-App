package com.Project.QuizApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question{
//    Question (question_id, question, option1, option2, option3, option4, answer)
    @SequenceGenerator(
            sequenceName = "question_sequence",
            name = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )
    @Id
    private Integer questionId;

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private Integer answer;

}
