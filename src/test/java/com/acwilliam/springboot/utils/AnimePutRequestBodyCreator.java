package com.acwilliam.springboot.utils;

import com.acwilliam.springboot.request.AnimePostRequestBody;
import com.acwilliam.springboot.request.AnimePutRequestBody;

public class AnimePutRequestBodyCreator {
    public static AnimePutRequestBody CriarAnimePostRequestBody() {
        return AnimePutRequestBody.builder()
                .nome(CriarAnime.atualizarAnime().getNome())
                .id(CriarAnime.atualizarAnime().getId())
                .build();
    }

}
