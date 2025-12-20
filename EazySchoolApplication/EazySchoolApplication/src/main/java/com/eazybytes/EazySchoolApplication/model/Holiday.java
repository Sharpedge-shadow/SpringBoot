package com.eazybytes.EazySchoolApplication.model;
//commit
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@Valid
public class Holiday extends BaseEntity{

    @NotNull
    private  String day;
    private  String reason;
    private  Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}