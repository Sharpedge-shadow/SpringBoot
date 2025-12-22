package com.eazybytes.EazySchoolApplication.model;
//commit
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Entity
@Table(name="holidays")
public class Holiday extends BaseEntity{

    @Id
    private String day;

    private String reason;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}