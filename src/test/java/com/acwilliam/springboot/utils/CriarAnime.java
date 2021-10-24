package com.acwilliam.springboot.utils;

import com.acwilliam.springboot.dominio.Anime;

public class CriarAnime {

    public static Anime CriarAnimeParaSerSalvo() {
            return Anime.builder()
                    .nome("Dragon Ball Z")
                    .build();
    }

    public static Anime CriarAnimeValido() {
            return Anime.builder()
                    .nome("Dragon Ball Z")
                    .id(1L)
                    .build();


    }

    public static Anime atualizarAnime() {
            return Anime.builder()
                    .nome("Dragon Ball Z Hero")
                    .id(1L)
                    .build();
    }
}