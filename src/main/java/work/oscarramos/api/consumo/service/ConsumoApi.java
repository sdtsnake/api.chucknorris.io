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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        List<ApiResponseDto> lista = new ArrayList<>();

        int i=0;
        do{
           ApiResponseDto res = cargaApi();
           if(!lista.stream().anyMatch(a-> a.getId().equals(res.getId()))){
                lista.add(res);
                i++;
           }
        }while(i<25);

    return lista;

    }
    public ApiResponseDto cargaApi(){
        return restTemplate.getForObject("https://api.chucknorris.io/jokes/random",ApiResponseDto.class);
    }


}
