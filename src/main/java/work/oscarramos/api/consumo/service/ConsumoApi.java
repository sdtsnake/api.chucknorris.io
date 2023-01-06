package work.oscarramos.api.consumo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import work.oscarramos.api.consumo.dto.ApiResponseDto;

import java.io.IOException;
import java.util.*;

@Service
public class ConsumoApi {
    @Autowired
    RestTemplate restTemplate;
    public List<ApiResponseDto> getApiRest(){

        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
                    throws IOException {
                request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                request.getHeaders().setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
                return execution.execute(request, body);
            }
        });

        Set<String> ids = new HashSet<>();
        List<ApiResponseDto> lista = new ArrayList<>();

        while (ids.size()<25){
            ApiResponseDto res = cargaApi();
            if(!ids.contains(res.getId())){
                lista.add(res);
                ids.add(res.getId());
            }
        }

        return lista;
    }
    public ApiResponseDto cargaApi(){
        return restTemplate.getForObject("https://api.chucknorris.io/jokes/random",ApiResponseDto.class);
    }


}
