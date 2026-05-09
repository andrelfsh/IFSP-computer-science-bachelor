<%-- 
    Document   : excluir
    Created on : 29 de set. de 2025, 21:19:27
    Author     : Vic, revisado por André
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
    <head>
        <title>Excluir Ator</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Roboto:wght@400;700&display=swap" rel="stylesheet">

        <style>
            body {
                margin: 0;
                font-family: 'Roboto', sans-serif;
                background-color: #002c2c;
                color: white;
                display: flex;
                flex-direction: column;
                align-items: center;
            }


            .header {
                width: 100%;
                background-color: #000;
                padding: 25px 40px;
                display: flex;
                align-items: center;
                justify-content: center; /* título no centro */
                position: relative;
            }

            .bolinhas {
                position: absolute;
                left: 60px;  /* fixa no canto esquerdo */
                top: 50%;
                transform: translateY(-50%); /* centraliza verticalmente */
                display: flex;
                gap: 8px;
            }

            .bola {
                width: 14px;
                height: 14px;
                border-radius: 50%;
                display: inline-block;
            }
            .azul {
                background-color: #4a6cf7;
            }
            .vermelha {
                background-color: #ff5f56;
            }
            .verde {
                background-color: #27c93f;
            }
            .roxa {
                background-color: #c678dd;
            }
                    /* Ícone do menu lateral (hamburger) */
            .menu-lateral {
                position: absolute;
                right: 60px;
                top: 50%;
                transform: translateY(-50%);
                cursor: pointer;
                display: flex;
                flex-direction: column;
                gap: 5px;
                z-index: 1001;
            }

            .menu-lateral div {
                width: 25px;
                height: 3px;
                background-color: white;
                border-radius: 2px;
                transition: 0.3s;
            }

         

            /* Painel do menu lateral */
            .painel-menu {
                position: fixed;
                top: 0;
                right: -250px;
                width: 250px;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.9);
                backdrop-filter: blur(8px);
                transition: right 0.4s ease;
                z-index: 1000;
                display: flex;
                flex-direction: column;
                justify-content: center;
            }

            .painel-menu ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .painel-menu li {
                margin: 20px 0;
                text-align: center;
            }

            .painel-menu a {
                color: #fff;
                font-size: 18px;
                text-decoration: none;
                transition: color 0.3s;
                font-family: 'Poppins', sans-serif;
            }

            .painel-menu a:hover {
                color:#d9443d ;
            }

            /* Quando o menu estiver ativo */
            .painel-menu.ativo {
                right: 0;
            }

            .titulo {
                font-family: 'Bebas Neue', sans-serif;
                font-size: 38px;
                font-weight: bold;
                letter-spacing: 1px;
                text-align: center;
            }


            .titulo:hover {
                color: #fe696e;
                transition: color 0.3s ease;
            }

            /* conteúdo */
            .container {
                margin-top: 50px;
                background: rgba(255,255,255,0.08);
                padding: 40px 50px;
                border-radius: 20px;
                width: 80%;
                max-width: 300px;
                min-height: 400px; /* define altura mínima */
            }


            h1 {
                font-family: 'Bebas Neue', sans-serif;
                font-size: 28px;
                text-align: center;
                margin-bottom: 25px;
            }

            table {
                width: 100%;
                color: white;
                font-size: 16px;
            }

            .alinharDireita {
                text-align: right;
                padding-right: 30px;
                font-weight: bold;
            }

            input[type="submit"] {
                background: #fe696e;
                border: none;
                color: white;
                padding: 12px 25px;
                border-radius: 12px;
                font-weight: bold;
                font-size: 15px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background: #d9443d;
            }
            .botaoVoltar {
                background-color:#fe696e;
                border: none;
                color: white;
                padding: 12px 25px;
                border-radius: 12px;
                font-weight: bold;
                font-size: 15px;
                cursor: pointer;
            }

            .botaoVoltar:hover {
                background-color: #d9443d;
            }

        </style>
    </head>

    <body>
        <!-- topo -->
        <div class="header">
            <div class="bolinhas">
                <span class="bola azul"></span>
                <span class="bola vermelha"></span>
                <span class="bola verde"></span>
                <span class="bola roxa"></span>
            </div>
              <!-- Ícone do menu lateral -->
            <div class="menu-lateral" onclick="toggleMenu(this)">
                <div></div>
                <div></div>
                <div></div>
            </div>
     

        <!-- Painel do menu lateral -->
        <nav class="painel-menu" id="painelMenu">
            <ul>
                <li><a href="${cp}/index.jsp">Início</a></li>
                <li><a href="${cp}/formularios/ator/listagem.jsp">Ator</a></li>
                <li><a href="${cp}//formularios/dvd/listagem.jsp">DVD</a></li>
                <li><a href="${cp}/formularios/classificacao_etaria/listagem.jsp">Classificações</a></li>
                 <li><a href="${cp}/formularios/genero/listagem.jsp">Gênero</a></li>

            </ul>
        </nav>
            <div class="titulo" onclick="window.location.href = '${cp}/index.jsp'">
                Skye
            </div>

        </div>
            </div>
        <!-- conteúdo -->
        <div class="container">
            <h1>Excluir Ator</h1>

            <form method="post" action="${cp}/processaAtor">
                <input name="acao" type="hidden" value="excluir"/>
                <input name="id" type="hidden" value="${requestScope.ator.id}"/>

                <table>
                    <tr>
                        <td class="alinharDireita">Nome:</td>
                        <td>${requestScope.ator.nome}</td>
                    </tr>
                    <tr>
                        <td class="alinharDireita">Sobrenome:</td>
                        <td>${requestScope.ator.sobrenome}</td>
                    </tr>
                    <tr>
                        <td class="alinharDireita">Data de estreia:</td>
                        <td>${requestScope.ator.data_estreia}</td>
                    </tr>
                    <tr>
                        <td >
                            <button type="button" class="botaoVoltar" onclick="window.location.href = '${cp}/formularios/ator/listagem.jsp'">
                                Voltar
                            </button>
                        </td>
                        <td class="alinharDireita">
                            <input type="submit" value="Excluir"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
                                        <!-- Script para abrir/fechar o menu -->
        <script>
            function toggleMenu(botao) {
                const menu = document.getElementById('painelMenu');
                botao.classList.toggle('ativo');
                menu.classList.toggle('ativo');
            }
        </script>
    </body>
</html>
