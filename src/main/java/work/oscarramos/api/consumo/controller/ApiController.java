package work.oscarramos.api.consumo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.oscarramos.api.consumo.dto.ApiResponseDto;
import work.oscarramos.api.consumo.service.ConsumoApi;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {
    private final ConsumoApi consumoApi;

    @GetMapping(path ="/consumo", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<ApiResponseDto>> getApiRest(){
        return ResponseEntity.ok(consumoApi.getApiRest());
    }
}
