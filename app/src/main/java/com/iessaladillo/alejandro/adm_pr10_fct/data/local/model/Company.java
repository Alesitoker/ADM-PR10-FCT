package com.iessaladillo.alejandro.adm_pr10_fct.data.local.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Company {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "cif")
    private String cif;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "phone")
    private int phone;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "urlLogo")
    private String logo;
    @ColumnInfo(name = "contactName")
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
