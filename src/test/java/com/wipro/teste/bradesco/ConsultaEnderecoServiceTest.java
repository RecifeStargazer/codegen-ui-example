package com.wipro.teste.bradesco;

import com.wipro.teste.bradesco.restClient.RestTemplateClient;
import com.wipro.teste.bradesco.services.ConsultaEnderecoService;
import com.wipro.teste.bradesco.services.ConsultaEnderecoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ConsultaEnderecoServiceTest {

    private static ConsultaEnderecoService consultaEnderecoService;

    @BeforeAll
    static void setup() {
        consultaEnderecoService = new ConsultaEnderecoServiceImpl(new RestTemplateClient(new RestTemplate()));
    }

    @Test
    public void deveConsultarCepCorretoComHifen(){
        var cep = "52051-280";
        Assertions.assertEquals(cep, consultaEnderecoService.getEnderecoPorCep(cep).getCep());
    }

    @Test
    public void deveConsultarCepCorretoSemHifen(){
        var cep = "52051280";
        Assertions.assertEquals(cep, consultaEnderecoService.getEnderecoPorCep(cep).getCep().replace("-",""));
    }

    @Test
    public void deveConsultarCepErrado(){
        var cep = "11111111";
        Assertions.assertThrows(HttpClientErrorException.class, () -> consultaEnderecoService.getEnderecoPorCep(cep));
    }
}
