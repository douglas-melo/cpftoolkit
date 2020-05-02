# CPFToolkit

### Sobre CPFToolkit
Toolkit com um módulo para validação de CPF, e uma API helper para o tratamento dos dígitos.

Possui um conjunto de métodos para validar o CPF. Nos casos de inconsistência, configura o motivo, 
o código, e a mensagem com a descrição do erro. Você poderá recuperá-los através dos métodos de captura.

Além do padrão sem formatação com 11 dígitos XXXXXXXXXXX incluindo o dígito verificador, 
o construtor e todos os métodos da API permitem CPF com o formato no padrão XXX.XXX.XXX-XX.

### Utilização
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