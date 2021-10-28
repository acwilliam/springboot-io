package com.acwilliam.springboot.integracao;

import com.acwilliam.springboot.dominio.Anime;
import com.acwilliam.springboot.utils.CriarAnime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class AnimeControllerIntegracao {
    @Autowired
    private TestRestTemplate testResTemplate;

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Retornando lista de anime dentro de uma page")
    void listPageAnime(){
        String nomeEsperado = CriarAnime.CriarAnimeValido().getNome();

        testResTemplate.exchange("/animes", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageImpl<Anime>>() {
                }).getBody();

       /* Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getNome()).isEqualTo(nomeEsperado);*/

    }



}
