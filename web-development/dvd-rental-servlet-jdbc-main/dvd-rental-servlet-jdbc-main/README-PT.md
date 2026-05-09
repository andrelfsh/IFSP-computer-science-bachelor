# DVD Rental Web Project (Locação de DVDs)

**[English Version](README.md)**

Este projeto é um CRUD simples para locação de DVDs, desenvolvido para praticar os fundamentos do desenvolvimento Web com Java. O foco principal é a implementação da comunicação entre uma aplicação Web e um banco de dados relacional utilizando **Java Servlets** e o padrão **DAO (Data Access Object)**, utilizando **MySQL** e **GlassFish**.

## Funcionalidades
* **CRUD Completo**: Criação, Leitura, Atualização e Exclusão de registros de DVDs.
* **Persistência**: Interação direta com o banco de dados via JDBC.
* **Padrão de Projeto**: Separação clara da lógica de banco de dados usando o padrão **DAO**.
* **Interface**: Renderização dinâmica utilizando JSP e JSTL.

## Tecnologias Utilizadas
* **Java** (JDK 17 ou superior)
* **Java Servlets** (API Nativa)
* **MySQL / MariaDB** (via XAMPP e MySQL Workbench)
* **JSP & JSTL** (JavaServer Pages)
* **GlassFish** (Servidor de Aplicação)
* **NetBeans** (IDE e Sistema de Build)

## Configuração do Banco de Dados
Este projeto inclui um arquivo de modelo visual: `ModeloBanco.mwb`.

1. Abra o **XAMPP Control Panel** e inicie o módulo **MySQL**.
2. Abra o **MySQL Workbench**.
3. Você pode abrir o arquivo `ModeloBanco.mwb` para visualizar o diagrama ERR ou simplesmente executar o script abaixo em uma aba de Query:

## Como Executar

1. Clone este repositório.
2. Certifique-se de que as bibliotecas MariaDB/MySQL Connector e JSTL (localizadas na pasta lib/) foram adicionadas às bibliotecas do seu projeto no NetBeans.
3. Atualize a sua classe ConnectionFactory com as suas credenciais do MySQL (usuário padrão do XAMPP: root, sem senha).
4. Utilize a opção Clean and Build (Limpar e Construir) no NetBeans.
5. Faça o deploy e execute no servidor GlassFish.