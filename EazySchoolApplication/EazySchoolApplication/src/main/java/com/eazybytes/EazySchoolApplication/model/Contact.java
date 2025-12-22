package com.eazybytes.EazySchoolApplication.model;
//commit
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

/*
@Data annotation is provided by Lombok library which generates getter, setter,
equals(), hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean.
* */
@Entity
@Table(name="contact_msg")
@Data
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @Column(name = "contact_id")
    private int contactId;
    /*
   * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
     @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
     @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
   * */
    @NotNull
    @NotEmpty
    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    @Column(name="NAME")
    private String name;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Column(name="MOBILE_NUM")
    private String mobileNum;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    @Column(name="EMAIL")
    private String email;

    @NotBlank(message="Subject must not be blank")
    @Size(min=5, message="Subject must be at least 5 characters long")
    @Column(name="SUBJECT")
    private String subject;

    @NotBlank(message="Message must not be blank")
    @Size(min=10, message="Message must be at least 10 characters long")
    @Column(name="MESSAGE")
    private String message;

    @Column(name="STATUS")
    private String status;
}