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

    @Autowired
    RestTemplateClient restTemplateClient;

    @Override
    public EnderecoResponseDTO getEnderecoPorCep(String cep) {
        ViaCep viaCep = restTemplateClient.getEnderecoCep(cep.replace("-",""));
        if(Optional.ofNullable(viaCep.getErro()).isPresent()){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Erro - Cep Não Encontrado, Verifique o CEP");
        }
        return buildResponse(viaCep);
    }
    //Até onde sei o swagger codegen não suporta builder pattern. Por isso essa feiura.
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
