package com.example.model.person;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    @Id
    private String id;

    @Field("firstName")
    private String firstName;

    @Field("lastName")
    private String lastName;

    @Field("address")
    private String address;

    @Field("email")
    private String email;

    @Field("Comment")
    private String comment;

    private String maker;

    public Person(String firstName, String lastName, String address, String email, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.comment = comment;
    }
}