package work.oscarramos.api.consumo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.oscarramos.api.consumo.service.ConsumoApi;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private final ConsumoApi consumoApi;

    @GetMapping(path ="/consumo", produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Map<String,Object>>> getApiRest(){
        return ResponseEntity.ok(consumoApi.getApiRest());
    }
}
