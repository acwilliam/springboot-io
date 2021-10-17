package com.acwilliam.springboot.service;

import com.acwilliam.springboot.dominio.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService  {

    private List<Anime> animes = List.of(new Anime(1L,"DBZ"),new Anime(2L,"Naruto"), new Anime(3L,"Boruto"));

    public List<Anime> listaAll(){
        return animes;
    }

    public Anime findById(long id){
        return animes.stream()
                .filter(anime -> anime.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Anime não encontrado"));
    }

}
