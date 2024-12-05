package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    private Boolean gender;

    @Column()
    private String address;

    private String phone;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "birth_date")
    private String dateOfBirth;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "is_delete")
    private Boolean isDelete = false;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private EmployeePosition position;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}