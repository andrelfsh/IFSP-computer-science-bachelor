<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cp" value="${pageContext.request.contextPath}" />

<jsp:useBean id="atorService" class="locacaodvds.servicos.AtorServices" scope="page"/>
<jsp:useBean id="generoService" class="locacaodvds.servicos.GeneroServices" scope="page"/>
<jsp:useBean id="classificacaoService" class="locacaodvds.servicos.Classificacao_etariaServices" scope="page"/>

<c:set var="atores" value="${atorService.todos}" />
<c:set var="generos" value="${generoService.todos}" />
<c:set var="classificacoes" value="${classificacaoService.todos}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${cp}/css/estilos.css">
    <title>Alterar DVD</title>
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
            .bola { width: 14px; height: 14px; border-radius: 50%; display: inline-block; }
            .azul { background-color: #4a6cf7; }
            .vermelha { background-color: #ff5f56; }
            .verde { background-color: #27c93f; }
            .roxa { background-color: #c678dd; }

            .titulo { font-family: 'Bebas Neue', sans-serif; font-size: 38px; font-weight: bold; letter-spacing: 1px; text-align: center; }

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
                margin-bottom: 25px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                font-size: 16px;
                color: white;
            }

            table td {
                padding: 10px 5px;
            }

            .alinharDireita {
                text-align: right;
                padding-right: 15px;
                font-weight: bold;
            }

            input[type="text"] {
                width: 100%;
                padding: 8px 12px;
                border-radius: 8px;
                border: none;
                font-size: 16px;
                background-color: #ffffff;
                color: black;
            }

            input[type="submit"] {
                background: #e2a9f1;
                border: none;
                color: white;
                padding: 12px 25px;
                border-radius: 12px;
                font-weight: bold;
                font-size: 16px;
                cursor: pointer;
            }

            input[type="submit"]:hover { background: #dcbbe5; }
             input[type="submit"],
        button {
            background: #00c896;
            border: none;
            color: white;
            padding: 12px 25px;
            border-radius: 12px;
            font-weight: bold;
            font-size: 16px;
            cursor: pointer;
        }


            input[type="submit"]:hover,
        button:hover {
            background: #7aebce;
        }  /* Ícone do menu lateral (hamburger) */
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

    <div class="container">
        <h1>Alterar DVD</h1>
        <form method="post" action="${cp}/processaDvd">
            <input name="acao" type="hidden" value="alterar"/>
            <input name="id" type="hidden" value="${requestScope.dvd.id}"/>

            <table>
                <tr>
                    <td class="alinharDireita">Título:</td>
                    <td><input name="titulo" type="text" maxlength="100" value="${requestScope.dvd.titulo}" required/></td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ano de lançamento:</td>
                    <td><input name="ano_lancamento" type="number" value="${requestScope.dvd.ano_lancamento}" required/></td>
                </tr>
                <tr>
                    <td class="alinharDireita">Data de lançamento:</td>
                    <td><input name="data" type="date" value="${requestScope.dvd.data_lancamento}" required/></td>
                </tr>
                <tr>
                    <td class="alinharDireita">Duração Em Minutos:</td>
                    <td><input name="duracao" type="number" value="${requestScope.dvd.duracao_minutos}" required/></td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator principal:</td>
                    <td>
                        <select name="ator_principal_id" required>
                            <c:forEach items="${atores}" var="ator">
                                <option value="${ator.id}" 
                                    <c:if test="${ator.id == requestScope.dvd.ator_principal.id}">selected</c:if>>
                                    ${ator.nome} ${ator.sobrenome}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Ator coadjuvante:</td>
                    <td>
                        <select name="ator_coadjuvante_id" required>
                            <c:forEach items="${atores}" var="ator">
                                <option value="${ator.id}" 
                                    <c:if test="${ator.id == requestScope.dvd.ator_coadjuvante.id}">selected</c:if>>
                                    ${ator.nome} ${ator.sobrenome}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Classificação:</td>
                    <td>
                        <select name="classificacao_id" required>
                            <c:forEach items="${classificacoes}" var="c">
                                <option value="${c.id}" 
                                    <c:if test="${c.id == requestScope.dvd.classificacao_etaria.id}">selected</c:if>>
                                    ${c.descricao}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">Gênero:</td>
                    <td>
                        <select name="genero_id" required>
                            <c:forEach items="${generos}" var="g">
                                <option value="${g.id}" 
                                    <c:if test="${g.id == requestScope.dvd.genero.id}">selected</c:if>>
                                    ${g.descricao}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="alinharDireita">URL da Capa:</td>
                    <td>
                        <input name="foto_url" type="text" maxlength="255" value="${requestScope.dvd.foto_url}" /> 
                    </td>
                </tr>
                <tr>
                     <td>
                        <button type="button" onclick="window.location.href='${cp}/formularios/dvd/listagem.jsp'">Voltar</button>
                    </td>
                    <td class="alinharDireita"><input type="submit" value="Alterar"/></td>
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
