package com.riwi.RIWIvirtual.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class ClassDTO {

    private Long id;

    @NotBlank(message = "El nombre de la clase es obligatorio.")
    private String name;

    private String description;

    @NotNull(message = "El estado activo es obligatorio.")
    private Boolean active;
}
