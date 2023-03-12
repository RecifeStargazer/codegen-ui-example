Feature: Consultar preco frete por cep
  Scenario: Consultar Cep valido com hifen
    Given Consulto Cep com Hifen
    When Envio uma request com o "Cep"
    Then O endereco e retornado
  Scenario: Consultar "Cep" valido sem hifen
    Given Consulto Cep sem Hifen
    When Envio uma request com o "Cep"
    Then O endereco e retornado
  Scenario: Consultar Cep invalido
    Given Consulto Cep invalido
    When Envio uma request com o "Cep"
    Then Erro "404" e retornado