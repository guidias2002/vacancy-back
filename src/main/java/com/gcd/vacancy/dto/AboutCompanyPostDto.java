package com.gcd.vacancy.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AboutCompanyPostDto {

    @NotBlank(message = "Campo obrigatório")
    private String about;

    @NotBlank(message = "Campo obrigatório")
    private String sector;
    private String linkedin;
    private String webSite;
}
