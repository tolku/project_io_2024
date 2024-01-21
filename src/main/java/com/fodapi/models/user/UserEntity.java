package com.fodapi.models.user;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    public UserEntity(String email, String passwordHash, boolean isEnabled) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.isEnabled = isEnabled;
    }

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String passwordHash;
    private Integer age;
    private Double weight;
    private Double height;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private boolean isEnabled;

}
