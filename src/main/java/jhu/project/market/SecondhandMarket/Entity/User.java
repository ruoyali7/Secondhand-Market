package jhu.project.market.SecondhandMarket.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name")
    @NotNull(message = "First Name cannot be null")
    @Size(min = 1, max = 16)
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name cannot be null")
    @Size(min = 1, max = 16)
    private String lastName;

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank
    @Size(min = 6, max = 10, message
            = "Username must be between 6 and 10 characters")
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    @NotBlank
    @Size(max = 150, message
            = "Address must not be longer than 150 characters")
    private String address;

    @Column(name = "phone_number")
    @NotBlank
    @Pattern(regexp="\\(\\d{3}\\)\\d{3}-\\d{4}")
    private String phoneNumber;

}

