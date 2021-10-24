package com.acwilliam.springboot.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AnimePutRequestBody {
    @NotEmpty(message="Não pode ser vazio")
    private Long id;
    @NotEmpty(message="Não pode ser vazio")
    private String nome;
}
