# CPFToolkit API

Toolkit com uma API helper para tratamento e validação de CPF.

Possui um método para validar o CPF, que em caso de inconsistência configura o tipo, 
código, e mensagem de erro. Você poderá recuperá-los através dos métodos de captura. 
Inclui métodos para o tratamento dos dígitos do CPF.

A API também possui métodos overloaded para o tratamento dos dígitos de CPFs válidos, caso 
a validação não seja necessária.

Além do padrão sem formatação com 11 dígitos XXXXXXXXXXX incluindo o dígito verificador, 
todos os métodos da API permitem o formato XXX.XXX.XXX-XX.
