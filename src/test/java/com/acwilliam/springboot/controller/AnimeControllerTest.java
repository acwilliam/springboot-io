package com.acwilliam.springboot.controller;

import com.acwilliam.springboot.dominio.Anime;
import com.acwilliam.springboot.service.AnimeService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

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


}