package work.oscarramos.api.consumo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
public class ConsumoApi {
    @Autowired
    RestTemplate restTemplate;
    public List<Map<String,Object>> getApiRest(){

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
        List<Map<String,Object>> lista = new ArrayList<>();

        while (ids.size()<25){
            Map<String,Object> res = cargaApi();
            var id = res.get("id");
            if(id != null && !ids.contains(id)){
                lista.add(res);
                ids.add(id.toString());
            }
        }

        return lista;
    }
    public Map<String,Object> cargaApi(){
        return restTemplate.getForObject("https://api.chucknorris.io/jokes/random",Map.class);
    }


}
