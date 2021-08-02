package com.elastic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "accounts")
public class Account {

    @Id
    private String id;

    @Field(name = "account_number", type = FieldType.Long)
    private Long accountNumber;

    @Field(name = "address", type = FieldType.Text)
    private String text;

    @Field(name = "age", type = FieldType.Long)
    private Long age;

    @Field(name = "balance", type = FieldType.Long)
    private Long balance;

    @Field(name = "city", type = FieldType.Text)
    private String city;

    @Field(name = "email", type = FieldType.Text)
    private String email;

    @Field(name = "employer", type = FieldType.Text)
    private String employer;

    @Field(name = "firstname", type = FieldType.Text)
    private String firstName;

    @Field(name = "lastname", type = FieldType.Text)
    private String lastName;

    @Field(name = "gender", type = FieldType.Keyword)
    private String gender;

    @Field(name = "state", type = FieldType.Keyword)
    private String state;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", accountNumber=" + accountNumber +
                ", text='" + text + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", employer='" + employer + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
