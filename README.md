# Desafio Tools Challenge Java
Implementar uma API de Pagamentos.

## Sobre o Projeto:
Você trabalha em um Banco, na área de cartões de crédito e faz parte do Time Elite.

## Tecnología utilizada: 

| Tecnología         | Versão     |
|--------------------|------------|
| Spring-Boot        |  2.7.2     |
| JUnit              |  5         |
| Geren. Dependecias |  mavem     |
| Java JDK           |  17        |
| Banco de Dados     |  H2        |


### As operações serão as seguintes:
#### Pagamento:
- Solicitação e resposta conforme JSON abaixo
- Estorno:
- consulta: por ID
- retorno: conforme JSON de retorno de estorno

#### Consulta:
- consulta: todos e por ID
- retorno: conforme JSON de retorno do pagamento

#### Requisitos das transações:
- Transacao > id: Deve ser único
- Transacao > Status: “AUTORIZADO”, “NEGADO”
- formaPagamento > Tipo: “AVISTA”, “PARCELADO LOJA”, “PARCELADO EMISSOR”

