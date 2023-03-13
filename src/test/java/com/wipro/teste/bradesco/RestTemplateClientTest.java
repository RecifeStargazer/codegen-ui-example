package com.wipro.teste.bradesco;

import com.wipro.teste.bradesco.pojo.ViaCep;
import com.wipro.teste.bradesco.restClient.RestTemplateClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestTemplateClientTest {

    private static RestTemplateClient restTemplateClient;

    private static RestTemplate mockedRestTemplate;


    @BeforeAll
    static void setup() {
        mockedRestTemplate = Mockito.mock(RestTemplate.class);
        restTemplateClient = new RestTemplateClient(mockedRestTemplate);
    }

    @Test
    public void deveConsultarCepCorretoComHifen(){
        var cep = "52051-280";
        ViaCep viaCep = new ViaCep();
        viaCep.setCep(cep);
        when(mockedRestTemplate.getForObject("https://viacep.com.br/ws/52051-280/json/", ViaCep.class)).thenReturn(viaCep);
        Assertions.assertEquals(cep, restTemplateClient.getEnderecoCep(cep).getCep());
    }

    @Test
    public void deveConsultarCepCorretoSemHifen(){
        var cep = "52051280";
        ViaCep viaCep = new ViaCep();
        viaCep.setCep(cep);
        when(mockedRestTemplate.getForObject("https://viacep.com.br/ws/52051280/json/", ViaCep.class)).thenReturn(viaCep);
        Assertions.assertEquals(cep,restTemplateClient.getEnderecoCep(cep).getCep());
    }

    @Test
    public void deveConsultarCepErrado(){
        var cep = "11111111";
        ViaCep viaCep = new ViaCep();
        viaCep.setErro(true);
        when(mockedRestTemplate.getForObject("https://viacep.com.br/ws/11111111/json/", ViaCep.class)).thenReturn(viaCep);
        Assertions.assertEquals(restTemplateClient.getEnderecoCep(cep).getErro(), true);
    }
}
