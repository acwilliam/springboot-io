package com.acwilliam.springboot.repository;

import com.acwilliam.springboot.dominio.Anime;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.util.Assert.isTrue;

@DataJpaTest
@DisplayName("Testes para o anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Persistir anime com sucesso")
    void savePersistAnimeSucesso(){
        Anime salvarAnime = createAnime();
        Anime animeSalvo = this.animeRepository.save(salvarAnime);
        Assertions.assertThat(animeSalvo).isNotNull();
        Assertions.assertThat(animeSalvo.getId()).isNotNull();
        Assertions.assertThat(animeSalvo.getNome().equals(animeSalvo.getNome())).isNotNull();

    }

    @Test
    @DisplayName("Atualizar Anime com sucesso")
    void atualizarAnimeSucesso(){
        Anime salvarAnime = createAnime();
        Anime animeSalvo = this.animeRepository.save(salvarAnime);

        animeSalvo.setNome("Overlord");

        Anime animeAtualizacao = this.animeRepository.save(animeSalvo);

        Assertions.assertThat(animeAtualizacao).isNotNull();
        Assertions.assertThat(animeAtualizacao.getId()).isNotNull();
        Assertions.assertThat(animeAtualizacao.getNome().equals(animeSalvo.getNome())).isNotNull();

    }

    @Test
    @DisplayName("Deletar Anime com sucesso")
    void deletarAnimeSucesso(){
        Anime salvarAnime = createAnime();
        Anime animeSalvo = this.animeRepository.save(salvarAnime);

        this.animeRepository.delete(animeSalvo);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSalvo.getId());

        Assertions.assertThat(animeOptional).isEmpty();

    }

    @Test
    @DisplayName("Buscar Anime com sucesso")
    void buscarAnimeSucesso(){
        Anime salvarAnime = createAnime();
        Anime animeSalvo = this.animeRepository.save(salvarAnime);

        String nome = animeSalvo.getNome();

        List<Anime> animes = this.animeRepository.findByNome(nome);

        Optional<Anime> animeOptional = this.animeRepository.findById(animeSalvo.getId());

        Assertions.assertThat(animes).isNotEmpty();
        Assertions.assertThat(animes).contains(animeSalvo);

    }

    @Test
    @DisplayName("Buscando Anime e retornando lista vazia")
    void buscarAnimeError(){
        List<Anime> animes = this.animeRepository.findByNome("the king of figther");

        Assertions.assertThat(animes).isEmpty();

    }

    private Anime createAnime(){
        return Anime.builder().nome("Salvar este anime")
                .build();
    }
}