package com.wipro.teste.bradesco;
import com.wipro.teste.bradesco.pojo.ViaCep;
import com.wipro.teste.bradesco.restClient.RestTemplateClient;
import com.wipro.teste.bradesco.services.ConsultaEnderecoService;
import com.wipro.teste.bradesco.services.ConsultaEnderecoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultaEnderecoServiceTest {

    private static ConsultaEnderecoService consultaEnderecoService;

    private static RestTemplateClient mockedRestTemplateClient;

    @BeforeAll
    static void setup() {
        mockedRestTemplateClient = Mockito.mock(RestTemplateClient.class);
        consultaEnderecoService = new ConsultaEnderecoServiceImpl(mockedRestTemplateClient);
    }

    @Test
    public void deveConsultarCepCorretoComHifen(){
        var cep = "52051-280";
        var viaCep =  new ViaCep();
        viaCep.setCep(cep);
        viaCep.setUf("PE");
        when(mockedRestTemplateClient.getEnderecoCep(cep)).thenReturn(viaCep);
        Assertions.assertEquals(cep, consultaEnderecoService.getEnderecoPorCep(cep).getCep());
    }

    @Test
    public void deveConsultarCepCorretoSemHifen(){
        var cep = "52051280";
        var viaCep =  new ViaCep();
        viaCep.setCep(cep);
        viaCep.setUf("PE");
        when(mockedRestTemplateClient.getEnderecoCep(cep)).thenReturn(viaCep);
        Assertions.assertEquals(cep, consultaEnderecoService.getEnderecoPorCep(cep).getCep());
    }

    @Test
    public void deveConsultarCepErrado(){
        var cep = "11111111";
        when(mockedRestTemplateClient.getEnderecoCep(cep)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        Assertions.assertThrows(HttpClientErrorException.class, () -> consultaEnderecoService.getEnderecoPorCep(cep));
    }
}
