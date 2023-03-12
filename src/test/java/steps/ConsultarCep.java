package steps;

import com.wipro.teste.bradesco.api.ConsultaEnderecoController;
import com.wipro.teste.bradesco.pojo.ViaCep;
import com.wipro.teste.bradesco.restClient.RestTemplateClient;
import com.wipro.teste.bradesco.services.ConsultaEnderecoService;
import com.wipro.teste.bradesco.services.ConsultaEnderecoServiceImpl;
import com.wipro.teste.model.ConsultaCepRequestDTO;
import com.wipro.teste.model.EnderecoResponseDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.when;


public class ConsultarCep {

    @Mock
    RestTemplate mockedRestTemplate;

    ConsultaEnderecoService service;

    ConsultaEnderecoController controller;

    ViaCep viaCepHifen;

    ViaCep viaCepSemHifem;

    ViaCep viaCepErro;

    ConsultaCepRequestDTO requestDTO;

    Integer response;

    @Given("Consulto cep")
    public void consultoCep() {
        mockedRestTemplate = Mockito.mock(RestTemplate.class);
        service = new ConsultaEnderecoServiceImpl(new RestTemplateClient(mockedRestTemplate));
        controller = new ConsultaEnderecoController(service);
        viaCepHifen = new ViaCep();
        viaCepHifen.setUf("PE");
        viaCepSemHifem = new ViaCep();
        viaCepSemHifem.setUf("PE");
        viaCepErro = new ViaCep();
        viaCepErro.setErro(true);
        requestDTO = new ConsultaCepRequestDTO();
    }

    @When("Envio uma request com o {string}")
    public void envioUmaRequestComO(String cep) {
        when(mockedRestTemplate.getForObject("https://viacep.com.br/ws/52051-280/json/", ViaCep.class)).thenReturn(viaCepHifen);
        when(mockedRestTemplate.getForObject("https://viacep.com.br/ws/52051280/json/", ViaCep.class)).thenReturn(viaCepSemHifem);
        when(mockedRestTemplate.getForObject("https://viacep.com.br/ws/52051-2800/json/", ViaCep.class)).thenReturn(viaCepErro);
        requestDTO.setCep(cep);
        try{
            response = controller.consultarEndereco(requestDTO).getStatusCodeValue();
        }catch(ResponseStatusException e){
            response = e.getStatus().value();
        }
    }

    @Then("E retornado o {int}")
    public void eRetornadoO(Integer status) {
        Assertions.assertEquals(response, status);
    }
}
