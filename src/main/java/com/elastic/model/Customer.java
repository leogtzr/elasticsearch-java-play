package com.elastic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;

@Document(indexName = "leotechie")
public class Customer {

    @Id
    private String id;

    // @Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSZZ", store = true, name = "@timestamp")
    private LocalDateTime dateField;

    private String firstName;
    private String lastName;
    private int age;

    public Customer(
            final String id
            , final LocalDateTime dateField
            , final String firstName
            , final String lastName
            , final int age) {
        this.id = id;
        this.dateField = dateField;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Customer() {}

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public LocalDateTime getDateField() {
        return dateField;
    }

    public void setDateField(final LocalDateTime dateField) {
        this.dateField = dateField;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", dateField=" + dateField +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
