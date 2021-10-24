package com.acwilliam.springboot.controller;

import com.acwilliam.springboot.dominio.Anime;
import com.acwilliam.springboot.request.AnimePostRequestBody;
import com.acwilliam.springboot.request.AnimePutRequestBody;
import com.acwilliam.springboot.service.AnimeService;
import com.acwilliam.springboot.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("animes")
public class AnimeController {

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private AnimeService animeService;

    /*@GetMapping
    public ResponseEntity<List<Anime>>list() {
        log.info(dateUtil.formatLocalDateTimeToDateBaseStyle(LocalDateTime.now()));
        return new ResponseEntity<> (animeService.listaAll(), HttpStatus.OK);
    }*/

     @GetMapping
    public ResponseEntity<Page<Anime>>list(Pageable pageable) {
       // log.info(dateUtil.formatLocalDateTimeToDateBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listaAll(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Anime>>listAll( ) {
     //   log.info(dateUtil.formatLocalDateTimeToDateBaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listaAllNoPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime>findById(@PathVariable long id) {
        return  ResponseEntity.ok(animeService.findByIdOrThrowException(id));
    }

    @GetMapping(path = "/find")
    public ResponseEntity<List<Anime>>findByNome(@RequestParam String nome) {
        return  ResponseEntity.ok(animeService.findByNome(nome));
    }

    @PostMapping
    @ResponseStatus
    public ResponseEntity<Anime> save(@Valid @RequestBody AnimePostRequestBody animePostRequestBody){
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping (path = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void>replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}