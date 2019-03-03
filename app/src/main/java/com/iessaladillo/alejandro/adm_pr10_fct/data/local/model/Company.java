package com.iessaladillo.alejandro.adm_pr10_fct.data.local.model;

public class Company {
    private long id;
    private String name;
    private String cif;
    private String address;
    private int phone;
    private String email;
    private String logo;
    private String contactName;

    public Company(long id, String name, String cif, String address, int phone, String email, String logo, String contactName) {
        this.id = id;
        this.name = name;
        this.cif = cif;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.logo = logo;
        this.contactName = contactName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCif() {
        return cif;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getLogo() {
        return logo;
    }

    public String getContactName() {
        return contactName;
    }
}
