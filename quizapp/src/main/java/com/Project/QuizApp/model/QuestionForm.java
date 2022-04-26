package com.Project.QuizApp.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QuestionForm implements Serializable {

    private Integer questionId;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
