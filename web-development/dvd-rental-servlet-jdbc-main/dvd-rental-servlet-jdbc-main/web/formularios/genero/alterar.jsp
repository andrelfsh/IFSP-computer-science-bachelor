<%-- 
    Author     : André
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
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
                min-height: 100vh;
            }

            .header {
                width: 100%;
                background-color: #000;
                padding: 25px 40px;
                display: flex;
                align-items: center;
                justify-content: center;
                position: relative;
            }

            .bolinhas {
                position: absolute;
                left: 60px;
                top: 50%;
                transform: translateY(-50%);
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
                color:#d36fed;
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
                color: #d36fed;
                transition: color 0.3s ease;
            }

            /* Container semi-transparente */
            .container {
                margin-top: 30px;
                background: rgba(255,255,255,0.08);
                padding: 30px 40px;
                border-radius: 20px;
                width: 90%;
                max-width: 500px;
                box-sizing: border-box;
            }

            h1 {
                font-family: 'Bebas Neue', sans-serif;
                font-size: 32px;
                text-align: center;
                margin-bottom: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                font-size: 16px;
                color: white;
            }

            table th, table td {
                padding: 10px;
                text-align: left;
            }

            .alinharDireita {
                text-align: right;
                padding-right: 20px;
                font-weight: bold;
            }

            input[type="text"] {
                width: 100%;
                padding: 8px 10px;
                border-radius: 6px;
                border: none;
                font-size: 16px;
                background-color: #ffffff;
                color: black;
            }

            input[type="submit"] {
                background: #d36fed;
                border: none;
                color: white;
                padding: 12px 25px;
                border-radius: 12px;
                font-weight: bold;
                font-size: 16px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #dcbbe5;
            }

            .botaoVoltar {
                background: #d36fed;
                border: none;
                color: white;
                padding: 12px 25px;
                border-radius: 12px;
                font-weight: bold;
                font-size: 16px;
                cursor: pointer;
                margin-right: 10px; /* espaço entre os botões */
            }

            .botaoVoltar:hover {
                background-color: #dcbbe5;
            }
            
        </style>
    </head>

    <body>
        <!-- Header -->
        <div class="header">
            <!-- Bolinhas -->
            <div class="bolinhas">
                <span class="bola azul"></span>
                <span class="bola vermelha"></span>
                <span class="bola verde"></span>
                <span class="bola roxa"></span>
            </div>

            <!-- Título central -->
            <div class="titulo" onclick="window.location.href = '${cp}/index.jsp'">
                Skye
            </div>

            <!-- Ícone do menu lateral -->
            <div class="menu-lateral" onclick="toggleMenu(this)">
                <div></div>
                <div></div>
                <div></div>
            </div>
        </div>

        <!-- Painel do menu lateral -->
        <nav class="painel-menu" id="painelMenu">
            <ul>
                <li><a href="${cp}/index.jsp">Início</a></li>
                <li><a href="${cp}/formularios/ator/listagem.jsp">Ator</a></li>
                <li><a href="${cp}/formularios/dvd/listagem.jsp">DVD</a></li>
                <li><a href="${cp}/formularios/classificacao_etaria/listagem.jsp">Classificações</a></li>
                <li><a href="${cp}/formularios/genero/listagem.jsp">Gênero</a></li>
            </ul>
        </nav>

        <!-- Container semi-transparente -->
        <div class="container">
            <h1>Alterar gênero</h1>

            <form method="post" action="${cp}/processaGenero">

                <input name="acao" type="hidden" value="alterar"/>
                <input name="id" type="hidden" value="${requestScope.genero.id}">

                <table>
                    <tr>
                        <td class="alinharDireita">Descrição:</td>
                        <td>
                            <input name="descricao" type="text" size="20" maxlength="30" value="${requestScope.genero.descricao}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button type="button" class="botaoVoltar"
                                    onclick="window.location.href = '${cp}/formularios/genero/listagem.jsp'">
                                Voltar
                            </button>
                        </td>
                        <td class="alinharDireita">
                            <input type="submit" value="Alterar">
                        </td>
                    </tr>
                </table>       
            </form>
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
