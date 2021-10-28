package com.acwilliam.springboot.service;

import com.acwilliam.springboot.controller.AnimeController;
import com.acwilliam.springboot.dominio.Anime;
import com.acwilliam.springboot.exception.BadRequestException;
import com.acwilliam.springboot.repository.AnimeRepository;
import com.acwilliam.springboot.request.AnimePostRequestBody;
import com.acwilliam.springboot.request.AnimePutRequestBody;
import com.acwilliam.springboot.utils.AnimePostRequestBodyCreator;
import com.acwilliam.springboot.utils.AnimePutRequestBodyCreator;
import com.acwilliam.springboot.utils.CriarAnime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AnimeServiceTest {

    @Mock
    private AnimeRepository animeRepository;

    @InjectMocks
    private AnimeService animeServiceMock;

    @BeforeEach
    void sertUp(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(CriarAnime.CriarAnimeValido()));
        BDDMockito.when(animeRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(animePage);

        BDDMockito.when(animeRepository.findAll())
                .thenReturn(List.of(CriarAnime.CriarAnimeValido()));

        BDDMockito.when(animeRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(java.util.Optional.ofNullable(CriarAnime.CriarAnimeValido()));

        BDDMockito.when(animeRepository.findByNome(ArgumentMatchers.any()))
                .thenReturn(List.of(CriarAnime.CriarAnimeValido()));

        BDDMockito.when(animeRepository.save(ArgumentMatchers.any(Anime.class)))
                .thenReturn(CriarAnime.CriarAnimeValido());

        BDDMockito.doNothing().when(animeRepository).delete(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("Retornando lista de anime dentro de uma page")
    void listAllPageAnime(){
        String nomeEsperado = CriarAnime.CriarAnimeValido().getNome();
        Page<Anime> animePage = animeServiceMock.listaAll(PageRequest.of(1,1));
        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getNome()).isEqualTo(nomeEsperado);

    }

    @Test
    @DisplayName("Retornando lista de anime dentro")
    void listAllNonPageAnime(){
        String nomeEsperado = CriarAnime.CriarAnimeValido().getNome();
        List<Anime> animes = animeServiceMock.listaAllNoPageable();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    @DisplayName("FindById retorna anime com sucesso")
    void findById(){
        Long idEsperado = CriarAnime.CriarAnimeValido().getId();

        Anime anime = animeServiceMock.findByIdOrThrowException(1);

        Assertions.assertThat(anime)
                .isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(idEsperado);
    }


    @Test
    @DisplayName("FindByNome retorna anime com sucesso")
    void findByNome(){
        String nomeEsperado = CriarAnime.CriarAnimeValido().getNome();
        List<Anime> animes = animeServiceMock.findByNome("nome de anime");

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    @DisplayName("FindByNome retorna lista de anime vazia")
    void findByNomeListVazia(){
        BDDMockito.when(animeRepository.findByNome(ArgumentMatchers.any()))
                .thenReturn(Collections.emptyList());

        List<Anime> animes = animeServiceMock.findByNome("nome de anime");

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Salvar e retorna anime com sucesso")
    void salvarAnimeComSucesso(){

        Anime anime = animeServiceMock.save(AnimePostRequestBodyCreator.CriarAnimePostRequestBody());

        Assertions.assertThat(anime)
                .isNotNull()
                .isEqualTo(CriarAnime.CriarAnimeValido());

    }

    @Test
    @DisplayName("Replace e retorna anime com sucesso")
    void replaceAnimeComSucesso(){



        Assertions.assertThatCode(()->animeServiceMock.replace(AnimePutRequestBodyCreator.CriarAnimePostRequestBody()))
                .doesNotThrowAnyException();


    }

    @Test
    @DisplayName("Delete e remove anime com sucesso")
    void deleteAnimeComSucesso(){

        Assertions.assertThatCode(()->animeServiceMock.delete(1))
                .doesNotThrowAnyException();

    }

}