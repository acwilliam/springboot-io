package com.acwilliam.springboot.utils;

import com.acwilliam.springboot.dominio.Anime;
import com.acwilliam.springboot.request.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {
    public static AnimePostRequestBody CriarAnimePostRequestBody() {
        return AnimePostRequestBody.builder()
                .nome(CriarAnime.CriarAnimeParaSerSalvo().getNome())
                .build();
    }

}
