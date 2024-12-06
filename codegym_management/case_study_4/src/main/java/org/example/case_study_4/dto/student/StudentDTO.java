package org.example.case_study_4.dto.student;

import org.example.case_study_4.model.Classes;

public class StudentDTO {
    private Integer id;
    private String name;
    private String email;
    private Boolean gender;
    private String address;
    private String phone;
    private String imageUrl;
    private Classes classEntity;

    public StudentDTO() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Classes getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(Classes classEntity) {
        this.classEntity = classEntity;
    }
}
