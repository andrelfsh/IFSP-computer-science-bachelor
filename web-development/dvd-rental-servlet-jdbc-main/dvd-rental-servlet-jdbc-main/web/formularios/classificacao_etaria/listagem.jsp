<%-- 
    Author     : André
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}" />
<c:set var="prefixo" value="processaClassificacao?acao=preparar"/>

<!DOCTYPE html>
<html>
<head>
    <title>Classificações Cadastradas</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${cp}/css/estilos.css"/>
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

        .bola { width: 14px; height: 14px; border-radius: 50%; display: inline-block; }
        .azul { background-color: #4a6cf7; }
        .vermelha { background-color: #ff5f56; }
        .verde { background-color: #27c93f; }
        .roxa { background-color: #c678dd; }

        .titulo { font-family: 'Bebas Neue', sans-serif; font-size: 38px; font-weight: bold; letter-spacing: 1px; text-align: center; }
         .titulo:hover {
            color: #5382ff;
            transition: color 0.3s ease;
        }
        /* Container semi-transparente */
        .container {
            margin-top: 30px;
            background: rgba(255,255,255,0.08);
            padding: 30px 40px;
            border-radius: 20px;
            width: 90%;
            max-width: 900px;
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

        table th {
            border-bottom: 2px solid white;
        }

        a {
            color: #5382ff;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover { color: white; }

        .botaoNovo {
            display: inline-block;
            margin: 10px 0 20px 0;
            padding: 10px 20px;
            background-color: #5382ff;
            border-radius: 12px;
            font-weight: bold;
            color: white;
            text-decoration: none;
        }

        .botaoNovo:hover { background-color: #97b1f7; }

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
                color: #5382ff ;
            }

            /* Quando o menu estiver ativo */
            .painel-menu.ativo {
                right: 0;
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
        <div class="titulo" onclick="window.location.href='${cp}/index.jsp'">
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
        <h1>Classificações Etária Cadastradas</h1>

        <a href="${cp}/formularios/classificacao_etaria/novo.jsp" class="botaoNovo">Nova Classificação Etária</a>

        <table class="tabelaListagem">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Descrição</th>
                    <th>Alterar</th>
                    <th>Excluir</th>
                </tr>
            </thead>
            <tbody>
                <jsp:useBean 
                    id="servicos"
                    scope="page"
                    class="locacaodvds.servicos.Classificacao_etariaServices"/>

                <c:forEach items="${servicos.todos}" var="Classificacao_etaria">
                    <tr>
                        <td>${Classificacao_etaria.id}</td>
                        <td>${Classificacao_etaria.descricao}</td>
                        <td class="acoes">
                            <a href="${cp}/${prefixo}Alteracao&id=${Classificacao_etaria.id}">Alterar</a>
                        </td>
                        <td class="acoes">
                            <a href="${cp}/${prefixo}Exclusao&id=${Classificacao_etaria.id}">Excluir</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="${cp}/index.jsp" class="botaoNovo">Tela Principal</a>
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
