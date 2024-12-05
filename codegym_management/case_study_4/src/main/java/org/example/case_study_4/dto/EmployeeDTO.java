package org.example.case_study_4.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.case_study_4.model.Account;
import org.example.case_study_4.model.EmployeePosition;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private int id;

    private String name;

    private String email;

    private Boolean gender;


    private String address;

    private String phone;


    private String imageUrl;


    private String dateOfBirth;


    private Double salary;


    private EmployeePosition position;


    private Account account;
}
