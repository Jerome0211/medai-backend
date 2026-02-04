package com.medai.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreatePatientRequest {

    @NotBlank
    private String fullName;

    private Integer age;
    private String gender;

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
