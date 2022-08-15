package uz.isystem.userservice.model;

import lombok.Getter;
import lombok.Setter;
import uz.isystem.userservice.type.UserType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
@Table(name = ("users"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private UserType userType;
    private String phone;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private String avatar; // https://isystem.uz/profile/1/image/1231asfd24fknaskd.png
    private LocalDate birth;
    private String address;
    private String address2;
    private Integer cityId;
    private String postcode;
    private String qrCode;
    private LocalDateTime emailVerifiedAt;
    private LocalDateTime phoneVerifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private Boolean status;
}
