package com.acwilliam.springboot.mapper;

import com.acwilliam.springboot.dominio.Anime;
import com.acwilliam.springboot.request.AnimePostRequestBody;
import com.acwilliam.springboot.request.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    public abstract Anime ToAnime(AnimePostRequestBody animePostRequestBody);

    public abstract Anime ToAnime(AnimePutRequestBody animePutRequestBody);

}
