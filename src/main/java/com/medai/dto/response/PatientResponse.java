package com.medai.dto.response;

public class PatientResponse {

    private Long id;
    private String fullName;
    private Integer age;
    private String gender;

    public PatientResponse() {}

    public PatientResponse(Long id, String fullName, Integer age, String gender) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public void setId(Long id) {
        this.id = id;
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
