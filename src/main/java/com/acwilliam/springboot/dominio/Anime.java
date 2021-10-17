package com.acwilliam.springboot.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data//cria o get/set/equals/hashcod/tostring
@AllArgsConstructor
public class Anime {
    Long id;
    private String nome;

}
