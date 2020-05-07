# CPFToolkit

### Sobre CPFToolkit
Toolkit com um módulo para validação de CPF, e uma API helper para o tratamento dos dígitos.

Possui um conjunto de métodos para validar o CPF. Nos casos de inconsistência, configura o motivo, 
o código, e a mensagem com a descrição do erro. Você poderá recuperá-los através dos métodos de captura.

Além do padrão sem formatação com 11 dígitos XXXXXXXXXXX incluindo o dígito verificador, 
o construtor e todos os métodos da API permitem CPF com o formato no padrão XXX.XXX.XXX-XX.

### API para validação
```java
import br.com.dsm.cpftoolkit.security.CPFToolkitAuth;

String invalidCPF = "123.321.456-78";

CPFToolkitAuth cpfToolkitAuth = new CPFToolkitAuth(invalidCPF);
cpfToolkitAuth.isCPFValid();
cpfToolkitAuth.getReason();
cpfToolkitAuth.getReasonToJSON();
cpfToolkitAuth.getCode();
cpfToolkitAuth.getMessage();
```
##### Output:
```bash
false
INVALID_CPF
invalidcpf
4
O CPF 123.321.456-78 é inválido. Não foi possível calcular o dígito verificador.
```
*Tabela de validação.*
| Código |       Motivo      |       JSON       |  Status |
|:------:|:-----------------:|:----------------:|:-------:|
|   `0`  |   `TRUSTED_CPF`   |   `trustedcpf`   |  `true` |
|   `1`  |     `NULL_CPF`    |     `nullcpf`    | `false` |
|   `2`  |    `EMPTY_CPF`    |    `emptycpf`    | `false` |
|   `3`  | `INVALID_PATTERN` | `invalidpattern` | `false` |
|   `4`  |   `INVALID_CPF`   |   `invalidcpf`   | `false` |
### API Helper
*API para o tratamento e formatação dos dígitos.*
```java
import br.com.dsm.cpftoolkit.util.CPFToolkitFormatter;

String cpf = "12332145678";

CPFToolkitFormatter.insertDelimiter(cpf);
CPFToolkitFormatter.getFirstThreeDigits(cpf);
CPFToolkitFormatter.getMiddleThreeDigits(cpf);
CPFToolkitFormatter.getLastThreeDigits(cpf);
CPFToolkitFormatter.getCheckDigit(cpf);
CPFToolkitFormatter.removeDelimiter(cpf);
```
##### Output:
```bash
123.321.456-78
123
321
456
78
12332145678
```
### Dependências
* [Java 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/index.html)
* [Gradle](https://github.com/gradle/gradle)
* [JUnit 4.13](https://github.com/junit-team/junit4)
* [JitPack](https://github.com/jitpack/jitpack.io)

### Utilização
Para utilizar, adicione o repositório maven do JitPack à sua lista de repositórios, e adicione este repositório do GitHub  
à sua lista de depedências no seu arquivo `build.gradle` conforme abaixo:
```
   repositories {
        maven { url 'https://jitpack.io' }
   }

   dependencies {
        implementation 'com.github.douglas-melo:cpftoolkit:0.1.0-beta'
   }
```
Thanks for that [JitPack](https://github.com/jitpack/jitpack.io)! :)