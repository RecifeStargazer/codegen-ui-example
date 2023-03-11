package com.wipro.teste.bradesco;


import com.wipro.teste.bradesco.restClient.RestTemplateClient;
import com.wipro.teste.bradesco.services.ConsultaEnderecoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class RestTemplateClientTest {

    private static RestTemplateClient restTemplateClient;

    @BeforeAll
    static void setup() {
        restTemplateClient = new RestTemplateClient(new RestTemplate());
    }

    @Test
    public void deveConsultarCepCorretoComHifen(){
        var cep = "52051-280";
        Assertions.assertEquals(cep, restTemplateClient.getEnderecoCep(cep).getCep());
    }

    @Test
    public void deveConsultarCepCorretoSemHifen(){
        var cep = "52051280";
        Assertions.assertEquals(cep, restTemplateClient.getEnderecoCep(cep).getCep().replace("-",""));
    }

    @Test
    public void deveConsultarCepErrado(){
       var cep = "11111111";
       Assertions.assertEquals(restTemplateClient.getEnderecoCep(cep).getErro(), true);
    }
}
