package com.acwilliam.springboot.cliente;

import com.acwilliam.springboot.dominio.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringCliente {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/4", Anime.class);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 4);

        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);

        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all", HttpMethod.GET, null,
        new ParameterizedTypeReference<>(){
        });

        log.info(exchange.getBody());

     /*   Anime kingdom = Anime.builder().nome("kingdom").build();
        Anime kingdomsalvo = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);
        log.info("Salvo anime {}", kingdomsalvo);*/

        Anime berserk = Anime.builder().nome("Berserk").build();
        ResponseEntity<Anime> berserksalvo = new RestTemplate().exchange("http://localhost:8080/animes", HttpMethod.POST,
                new HttpEntity<>(berserk,creatJsonHeader()), Anime.class);
        log.info("Salvo anime {}", berserksalvo);
    }

    private static HttpHeaders creatJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
