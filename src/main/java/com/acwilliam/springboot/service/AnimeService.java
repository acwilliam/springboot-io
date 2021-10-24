package com.acwilliam.springboot.service;

import com.acwilliam.springboot.dominio.Anime;
import com.acwilliam.springboot.mapper.AnimeMapper;
import com.acwilliam.springboot.repository.AnimeRepository;
import com.acwilliam.springboot.request.AnimePostRequestBody;
import com.acwilliam.springboot.request.AnimePutRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnimeService  {

    @Autowired
    private AnimeRepository animeRepository;

    public List<Anime> listaAll(){
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowException(long id){
        return animeRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Anime não encontrado"));


    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.ToAnime(animePostRequestBody));

       // Anime anime =Anime.builder().nome(animePostRequestBody.getNome()).build();
       // return animeRepository.save(anime);
    }

    public void delete(long id) {
        animeRepository.delete((findByIdOrThrowException(id)));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime animeSalvo = findByIdOrThrowException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.ToAnime(animePutRequestBody);
        anime.setId(animeSalvo.getId());
       animeRepository.save(anime);


        //findByIdOrThrowException(animePutRequestBody.getId());
        //Anime anime = Anime.builder()
          //      .id(animePutRequestBody.getId())
            //    .nome(animePutRequestBody.getNome())
             //   .build();
      //  animeRepository.save(anime);
     
    }
}
