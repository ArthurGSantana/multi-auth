# Projeto de Autenticação Múltipla

Este projeto é uma implementação de referência para diferentes mecanismos de autenticação em aplicações Java Spring, oferecendo suporte para:

- Autenticação JWT (JSON Web Token)
- Autenticação OIDC (OpenID Connect)
- Autenticação SAML (Security Assertion Markup Language)

## Sobre o Projeto

Esta API foi desenvolvida para demonstrar as diferentes abordagens de autenticação e autorização em ambientes corporativos e aplicações distribuídas modernas. Cada método de autenticação está implementado em módulos separados para facilitar o entendimento e uso.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- OIDC (OpenID Connect)
- SAML 2.0
- Redis (para gerenciamento de sessões e tokens)
- Gradle

## Estrutura do Projeto

O projeto está organizado nos seguintes módulos:

- **JWT**: Implementação de autenticação baseada em tokens JWT com refresh token
- **OIDC**: Integração com provedores de identidade que suportam o protocolo OpenID Connect
- **SAML**: Implementação de autenticação usando Security Assertion Markup Language
- **Redis**: Implementação para armazenamento e validação de tokens e sessões

## Pré-requisitos

- Java 17 ou superior
- Gradle
- Docker (para executar Redis)
- Um provedor de identidade para testes OIDC/SAML (como Keycloak, Auth0, etc.)

## Configuração do Redis

O projeto utiliza Redis para gerenciamento de sessões e validação de tokens. Para executar o Redis localmente via Docker:

```bash
docker run --name redis-auth -p 6379:6379 -d redis