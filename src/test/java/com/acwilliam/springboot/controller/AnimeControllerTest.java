package com.acwilliam.springboot.controller;

import com.acwilliam.springboot.dominio.Anime;
import com.acwilliam.springboot.request.AnimePostRequestBody;
import com.acwilliam.springboot.request.AnimePutRequestBody;
import com.acwilliam.springboot.service.AnimeService;
import com.acwilliam.springboot.utils.AnimePostRequestBodyCreator;
import com.acwilliam.springboot.utils.AnimePutRequestBodyCreator;
import com.acwilliam.springboot.utils.CriarAnime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;

    @Mock
    private AnimeService animeServiceMock;

    @BeforeEach
    void sertUp(){
        PageImpl<Anime> animePage = new PageImpl<>(List.of(CriarAnime.CriarAnimeValido()));
        BDDMockito.when(animeServiceMock.listaAll(ArgumentMatchers.any()))
                .thenReturn(animePage);

        BDDMockito.when(animeServiceMock.listaAllNoPageable())
                .thenReturn(List.of(CriarAnime.CriarAnimeValido()));

        BDDMockito.when(animeServiceMock.findByIdOrThrowException(ArgumentMatchers.anyLong()))
                .thenReturn(CriarAnime.CriarAnimeValido());

        BDDMockito.when(animeServiceMock.findByNome(ArgumentMatchers.any()))
                .thenReturn(List.of(CriarAnime.CriarAnimeValido()));

        BDDMockito.when(animeServiceMock.save(ArgumentMatchers.any(AnimePostRequestBody.class)))
                .thenReturn(CriarAnime.CriarAnimeValido());

        BDDMockito.doNothing().when(animeServiceMock).replace(ArgumentMatchers.any(AnimePutRequestBody.class));

        BDDMockito.doNothing().when(animeServiceMock).delete(ArgumentMatchers.anyLong());

    }

    @Test
    @DisplayName("Retornando lista de anime dentro de uma page")
    void listPageAnime(){
        String nomeEsperado = CriarAnime.CriarAnimeValido().getNome();
        Page<Anime> animePage = animeController.list(null).getBody();
        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getNome()).isEqualTo(nomeEsperado);

    }

    @Test
    @DisplayName("Retornando lista de anime dentro")
    void listAllPageAnime(){
        String nomeEsperado = CriarAnime.CriarAnimeValido().getNome();
        List<Anime> animes = animeController.listAll().getBody();

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

        Anime anime = animeController.findById(1).getBody();

        Assertions.assertThat(anime)
                .isNotNull();

        Assertions.assertThat(anime.getId()).isNotNull().isEqualTo(idEsperado);
    }

    @Test
    @DisplayName("FindByNome retorna anime com sucesso")
    void findByNome(){
        String nomeEsperado = CriarAnime.CriarAnimeValido().getNome();
        List<Anime> animes = animeController.findByNome("nome de anime").getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animes.get(0).getNome()).isEqualTo(nomeEsperado);
    }

    @Test
    @DisplayName("FindByNome retorna lista de anime vazia")
    void findByNomeListVazia(){
        BDDMockito.when(animeServiceMock.findByNome(ArgumentMatchers.any()))
                .thenReturn(Collections.emptyList());

        List<Anime> animes = animeController.findByNome("nome de anime").getBody();

        Assertions.assertThat(animes)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Salvar e retorna anime com sucesso")
    void salvarAnimeComSucesso(){

        Anime anime = animeController.save(AnimePostRequestBodyCreator.CriarAnimePostRequestBody()).getBody();

        Assertions.assertThat(anime)
                .isNotNull()
                .isEqualTo(CriarAnime.CriarAnimeValido());

    }

    @Test
    @DisplayName("Replace e retorna anime com sucesso")
    void replaceAnimeComSucesso(){

        ResponseEntity<Void> entity = animeController.replace(AnimePutRequestBodyCreator.CriarAnimePostRequestBody());
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThatCode(()->animeController.replace(AnimePutRequestBodyCreator.CriarAnimePostRequestBody()))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("Delete e remove anime com sucesso")
    void deleteAnimeComSucesso(){

        ResponseEntity<Void> entity = animeController.delete(1);
        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThatCode(()->animeController.delete(1))
                .doesNotThrowAnyException();

    }


}