<%-- 
    Author     : André
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaDvd?acao=preparar"/>

<!DOCTYPE html>
<html>
    <head>
        <title>Dvds Cadastrados</title>
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

            /* Header com bolinhas e título */
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

            .titulo {
                font-family: 'Bebas Neue', sans-serif;
                font-size: 38px;
                font-weight: bold;
                letter-spacing: 1px;
                text-align: center;
            }
            .titulo {
                font-family: 'Bebas Neue', sans-serif;
                font-size: 38px;
                font-weight: bold;
                letter-spacing: 1px;
                text-align: center;
                cursor: pointer;
            }

            .titulo:hover {
                color: #7aebce;
                transition: color 0.3s ease;
            }
            /* Container semi-transparente */
            .container {
                margin-top: 30px;
                background: rgba(255,255,255,0.08);
                padding: 40px 50px;
                border-radius: 20px;
                width: 90%;
                max-width: 1500px;
                box-sizing: border-box;
            }

            h1 {
                font-family: 'Bebas Neue', sans-serif;
                font-size: 32px;
                text-align: center;
                margin-bottom: 30px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                font-size: 16px;
                color: white;
            }

            table th, table td {
                padding: 12px;
                text-align: left;
            }

            table th {
                border-bottom: 2px solid white;
            }

            a {
                color: #00c896;
                text-decoration: none;
                font-weight: bold;
            }

            a:hover {
                color: white;
            }

            .botaoNovo {
                display: inline-block;
                margin: 10px 0 20px 0;
                padding: 10px 20px;
                background-color: #00c896;
                border-radius: 12px;
                font-weight: bold;
                color: white;
                text-decoration: none;
            }

            .botaoNovo:hover {
                background-color: #7aebce;
            }

            .acoes a {
                margin-right: 10px;
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
                color:#7aebce;
            }

            /* Quando o menu estiver ativo */
            .painel-menu.ativo {
                right: 0;
            }
            /* Grade de DVDs */
            .grade-dvds {
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
                gap: 40px;
                margin-top: 30px;
            }

            /* Cada card de DVD */
            .card-dvd {
                background-color: #013737;
                border-radius: 14px;
                width: 180px;
                padding: 15px;
                text-align: center;
                transition: transform 0.3s ease, box-shadow 0.3s ease;
            }

            .card-dvd:hover {
                transform: translateY(-5px);
                box-shadow: 0 4px 10px rgba(0,0,0,0.4);
            }

            /* Capa do DVD */
            .capa-dvd {
                width: 100%;
                height: 260px;
                object-fit: cover;
                border-radius: 10px;
            }

            /* Título e ID */
            .info-dvd {
                margin-top: 10px;
            }

            .titulo-dvd {
                font-weight: bold;
                font-size: 16px;
                color: white;
                margin-bottom: 5px;
            }

            .subtitulo-dvd {
                font-size: 13px;
                color: #9fd;
            }

            /* Botões Alterar e Excluir */
            .acoes-dvd {
                margin-top: 10px;
            }

            .acoes-dvd a {
                color: #00c896;
                text-decoration: none;
                font-weight: bold;
                margin: 0 5px;
            }

            .acoes-dvd a:hover {
                color: white;
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

        <div class="container">
            <h1>Dvds Cadastrados</h1>

            <a href="${cp}/formularios/dvd/novo.jsp" class="botaoNovo">Novo Dvd</a>

            <!-- Grade de DVDs -->
            <div class="grade-dvds">
                <jsp:useBean id="servicos" scope="page" class="locacaodvds.servicos.DvdServices"/>

                <c:forEach items="${servicos.todos}" var="dvd">
                    <div class="card-dvd">
                        <img src="${dvd.foto_url}" alt="${dvd.titulo}" class="capa-dvd">
                        <div class="info-dvd">
                            <p class="titulo-dvd">${dvd.titulo}</p>
                            <p class="subtitulo-dvd">Minutagem: ${dvd.duracao_minutos}</p>
                            <p class="subtitulo-dvd">Ano: ${dvd.ano_lancamento}</p>
                        </div>
                        <div class="acoes-dvd">
                            <a href="${cp}/${prefixo}Alteracao&id=${dvd.id}">Alterar</a>
                            <a href="${cp}/${prefixo}Exclusao&id=${dvd.id}">Excluir</a>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <p><a href="${cp}/index.jsp" class="botaoNovo">Tela Principal</a></p>
        </div>

       
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