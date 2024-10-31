package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AboutMePostDto {


    @NotBlank(message = "Campo obrigatório")
    private String fullName;

    @NotBlank(message = "Campo obrigatório")
    private String location;

    @NotBlank(message = "Campo obrigatório")
    private String cellphoneNumber;

    @NotBlank(message = "Campo obrigatório")
    private String linkedin;
}
