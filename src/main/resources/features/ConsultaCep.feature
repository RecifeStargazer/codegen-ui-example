Feature: Consultar preco frete por cep

  Scenario Outline: Consultar Cep valido com hifen
    Given Consulto cep
    When Envio uma request com o <cep>
    Then E retornado o <status>
    Examples:
      | cep           | status  |
      | "52051280"    | 200     |
      | "52051-280"   | 200     |
      | "52051-2800"  | 404     |
