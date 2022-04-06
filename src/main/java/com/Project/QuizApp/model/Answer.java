package com.Project.QuizApp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
//    Answer (id, candidate_id, question_id, answer)
    @Id
    @SequenceGenerator(
            sequenceName = "answer_sequence",
            name = "answer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "answer_sequence"
    )
    private Integer id;
    private Integer candidateId;

    private int answer;
    private Integer questionId;

}
