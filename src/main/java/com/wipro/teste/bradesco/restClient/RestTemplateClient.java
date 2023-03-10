package com.wipro.teste.bradesco.restClient;
import com.wipro.teste.bradesco.pojo.ViaCep;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateClient {
    RestTemplate restTemplate = new RestTemplate();

    private static final String BASEURL = "https://viacep.com.br/ws/";

    public ViaCep getEnderecoCep(String cep){
        return restTemplate.getForObject(BASEURL+cep+"/json/",ViaCep.class);
    }
}
