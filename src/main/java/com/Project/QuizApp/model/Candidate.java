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
public class Candidate {
//    Candidate (candidate_id, name, emailId, isStarted, isSubmit)
@SequenceGenerator(
        sequenceName = "candidate_sequence",
        name = "candidate_sequence",
        allocationSize = 1
)
@GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "candidate_sequence"
)
    @Id
    private Integer candidateId;
    private String name;
    private String emailId;
    private Boolean isStarted;
    private Boolean isSubmit;
}
