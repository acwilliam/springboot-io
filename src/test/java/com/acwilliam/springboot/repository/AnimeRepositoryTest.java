package com.acwilliam.springboot.repository;

import com.acwilliam.springboot.dominio.Anime;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@DisplayName("Testes para o anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Criar Anime com sucesso")
    void savePersistAnimeSucesso(){
        Anime salvarAnime = createAnime();
        Anime animeSalvo = this.animeRepository.save(salvarAnime);
        Assertions.assertThat(animeSalvo).isNotNull();
        Assertions.assertThat(animeSalvo.getId()).isNotNull();
        Assertions.assertThat(animeSalvo.getNome().equals(animeSalvo.getNome())).isNotNull();

    }

    private Anime createAnime(){
        return Anime.builder().nome("Salvar este anime")
                .build();
    }
}