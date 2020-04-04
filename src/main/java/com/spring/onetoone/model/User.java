package com.spring.onetoone.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 4, max=65, message = "FirstName Should between 4 and 65")
    private String firstName;

    @Size(max=65)
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL,
              mappedBy = "user")
    private UserProfile userProfile;



    public User(String rajeev, String patel, String s, String password) {
        this.firstName = rajeev;
        this.lastName = patel;
        this.email = s;
        this.password = password;
    }
}
