package com.wipro.teste.bradesco.services;

import com.wipro.teste.model.EnderecoResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ConsultaEnderecoService {

    EnderecoResponseDTO getEnderecoPorCep(String cep);

}
