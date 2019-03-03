package com.iessaladillo.alejandro.adm_pr10_fct.data.local.model;

public class Student {
    private long id;
    private String name;
    private int phone;
    private String email;
    private String grade;
    private String company;
    private String nameTutor;
    private int phoneTutor;
    private String schedule;

    public Student(long id, String name, int phone, String email, String grade, String company,
                   String nameTutor, int phoneTutor, String schedule) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.grade = grade;
        this.company = company;
        this.nameTutor = nameTutor;
        this.phoneTutor = phoneTutor;
        this.schedule = schedule;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGrade() {
        return grade;
    }

    public String getCompany() {
        return company;
    }

    public String getNameTutor() {
        return nameTutor;
    }

    public int getPhoneTutor() {
        return phoneTutor;
    }

    public String getSchedule() {
        return schedule;
    }
}
