package com.telconova.supportsuite.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTemplateRequest {
    @NotBlank(message = "El nombre de la plantilla no puede estar vacío.")
    private String name;
    @NotBlank(message = "El contenido del mensaje no puede estar vacío.")
    private String content;
}
