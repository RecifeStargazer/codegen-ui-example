# codegen-ui-example

This is a exercise of how use swagger codegen and swagger ui together. 

## To use it: 

### requires maven 3.6.1 and java 11

### mvn clean install

### make sure that target folder is marked as source

### then start the springboot application.

The SwaggerCodegen generates the api interface and DTO classes, which are implemented/used on code.

You can see the documentation and test the api by accessing http://localhost:8080/swagger-ui.html

You can check the Automated and unit tests on ### maven install ### logs and also run them using a ide (but this will require plugin configurations and such).

### -------------------------------------------------------------------------------------------------------------------------

Este é um exercício de como usar o swagger codegen e o swagger ui juntos.

## Para usá-lo:

### requer maven 3.6.1 e java 11

### mvn clean install

### certifique-se que a pasta target está marcada como source

### em seguida, inicie a aplicação springboot..

O SwaggerCodegen gera a interface da api e as classes DTO(baseado no doc api.yaml), que são implementadas/usadas no código.

Você pode ver a documentação e testar a API acessando http://localhost:8080/swagger-ui.html

Os testes automatizados (Cucumber) e os testes unitarios podem ser verificados no log do ### maven install ### e também rodando direto de uma ide(mas isso pode requerer alguma configuração de plugins e coisas do tipo).
