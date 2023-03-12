package com.wipro.teste.bradesco.api;

import com.wipro.teste.api.V1Api;
import com.wipro.teste.bradesco.services.ConsultaEnderecoService;
import com.wipro.teste.model.ConsultaCepRequestDTO;
import com.wipro.teste.model.EnderecoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ConsultaEnderecoController implements V1Api {

    ConsultaEnderecoService consultaEnderecoService;

    @Autowired
    public ConsultaEnderecoController(ConsultaEnderecoService consultaEnderecoService) {
        this.consultaEnderecoService = consultaEnderecoService;
    }

    @Override
    public ResponseEntity<EnderecoResponseDTO> consultarEndereco(ConsultaCepRequestDTO cep) {
        try{
            return new ResponseEntity<>(consultaEnderecoService.getEnderecoPorCep(cep.getCep()), HttpStatus.OK);
        }catch(HttpClientErrorException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Erro - Cep Não Encontrado, Verifique o CEP", e);
        }
    }
}
