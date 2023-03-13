package com.wipro.teste.bradesco.services;
import com.wipro.teste.bradesco.enums.EstadosEnum;
import com.wipro.teste.bradesco.pojo.ViaCep;
import com.wipro.teste.bradesco.restClient.RestTemplateClient;
import com.wipro.teste.model.EnderecoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Optional;

@Service
public class ConsultaEnderecoServiceImpl implements ConsultaEnderecoService{

    RestTemplateClient restTemplateClient;

    @Autowired
    public ConsultaEnderecoServiceImpl(RestTemplateClient restTemplateClient) {
        this.restTemplateClient = restTemplateClient;
    }

    @Override
    public EnderecoResponseDTO getEnderecoPorCep(String cep) {
        ViaCep viaCep = restTemplateClient.getEnderecoCep(cep);
        //Se o cep tem numero de caracteres certo mas nao existe, não lança exceção http(Deveria). Tratado aqui no service.
        if(Optional.ofNullable(viaCep.getErro()).isPresent()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return buildResponse(viaCep);
    }
    //Até onde sei o swagger codegen não suporta builder pattern. Por isso essa feiura. Não tive tempo de solucionar isso.
    private EnderecoResponseDTO buildResponse(ViaCep viaCep){
        EnderecoResponseDTO responseDTO = new EnderecoResponseDTO();
        responseDTO.setBairro(viaCep.getBairro());
        responseDTO.setCep(viaCep.getCep());
        responseDTO.setCidade(viaCep.getLocalidade());
        responseDTO.setComplemento(viaCep.getComplemento());
        responseDTO.setEstado(viaCep.getUf());
        responseDTO.setRua(viaCep.getLogradouro());
        responseDTO.setPreco(EstadosEnum.valueOf(viaCep.getUf()).getPreço());
        return responseDTO;
    };

}
