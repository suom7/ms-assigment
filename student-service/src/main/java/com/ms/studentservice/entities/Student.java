package com.ms.studentservice.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "student")
public class Student {
    /**
     * Auto generate id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Student Identity
     */
    @Column(name = "student_id")
    private String studentId;
    /**
     * Student portrait photo Url
     */
    @Column(name = "portrait_photo_url")
    private String portraitPhotoUrl;
    /**
     * Name
     */
    @Column(name = "name")
    private String name;
    /**
     * Phone number
     */
    @Column(name = "phone_number")
    private String phoneNumber;
    /**
     * Email address
     */
    @Column(name = "email")
    private String email;
}
