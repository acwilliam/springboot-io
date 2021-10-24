package com.acwilliam.springboot.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnimePutRequestBody {
    @NotEmpty(message="Não pode ser vazio")
    private Long id;
    @NotEmpty(message="Não pode ser vazio")
    private String nome;
}
