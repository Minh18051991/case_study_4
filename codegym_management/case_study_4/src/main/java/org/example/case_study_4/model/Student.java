package org.example.case_study_4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    private Boolean gender;

    private String address;

    private String phone;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "birth_date")
    private String dateOfBirth;


    @Column(name = "is_delete")
    private Boolean isDelete = false;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Classes classEntity;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}