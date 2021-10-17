package com.acwilliam.springboot.service;

import com.acwilliam.springboot.dominio.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService  {
    public List<Anime> listaAll(){
        return List.of(new Anime(1L,"DBZ"),new Anime(2L,"Naruto"), new Anime(3L,"Boruto"));
    }
}
