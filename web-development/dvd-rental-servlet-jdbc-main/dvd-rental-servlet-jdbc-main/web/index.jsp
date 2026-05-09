<%-- 
    Document   : index
    Created on : 25 de set. de 2025, 10:49:28
    Author     : André Lyra e Vic
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Home</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Bebas+Neue&family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">

    <style>
        body {
          background-image: url('cinema.gif'); 
          background-size: cover;
          background-repeat: no-repeat; 
          background-attachment: fixed; 
          margin: 0; 
          padding: 0; 

          display: flex;
          flex-direction: column;
          justify-content: center; 
          align-items: center;    
          height: 100vh;          
        }

        /* bolinhas no canto */
        .bolinhas {
          position: absolute;
          top: 15px;
          left: 15px;
          display: flex;
          gap: 20px;
        }
        .bola {
          width: 20px;
          height: 20px;
          border-radius: 50%;
          display: inline-block;
        }
        .azul { background-color: #4a6cf7; }
        .vermelha { background-color: #ff5f56; }
        .verde { background-color: #27c93f; }
        .roxa { background-color: #c678dd; }

        /* título e subtítulo */
        h1 {
          color: white;
          margin: 0;
          font-family: 'Bebas Neue', sans-serif;
          font-size: 60px;
          letter-spacing: 2px;
        }

        p {
          color: white;
          margin: 5px;
          font-family: 'Roboto', sans-serif;
          font-size: 18px;
        }

        /* botões */
        .links {
          display: flex;
          gap: 20px;
          margin-top: 30px;
        }

        .links a {
          display: inline-block;
          padding: 15px 40px;
          border-radius: 30px;
          font-family: 'Roboto', sans-serif;
          font-size: 16px;
          font-weight: bold;
          text-decoration: none;
          color: white;
          transition: transform 0.2s, opacity 0.2s;
        }

        .links a:hover {
          transform: scale(1.05);
          opacity: 0.9;
          background-color: gray;
        }

        /* cores dos botões */
        .btn-dvd { background-color: #00c896; }
        .btn-ator { background-color: #ff5f56; }
        .btn-classificacao { background-color: #3d7eff; }
        .btn-genero { background-color: #d78cff; }

    </style>
  </head>
  
  <body>
    <!-- bolinhas -->
    <div class="bolinhas">
      <span class="bola azul"></span>
      <span class="bola vermelha"></span>
      <span class="bola verde"></span>
      <span class="bola roxa"></span>
    </div>

    <!-- conteúdo central -->
    <h1>Skye</h1>
    <p>Seu sistema de gerenciamento de filmes</p>

    <div class="links">
      <a href="${cp}/formularios/dvd/listagem.jsp" class="btn-dvd">DVD</a>
      <a href="${cp}/formularios/ator/listagem.jsp" class="btn-ator">Ator</a>
      <a href="${cp}/formularios/classificacao_etaria/listagem.jsp" class="btn-classificacao">Classificação</a>
      <a href="${cp}/formularios/genero/listagem.jsp" class="btn-genero">Gênero</a>
    </div>
  </body>
</html>
