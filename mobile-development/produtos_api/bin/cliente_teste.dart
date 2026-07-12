import 'dart:convert';
import 'dart:io';

void main() async {

  final url = Uri.parse('http://127.0.0.1:8080/adicionar');

  // teste
  final novoProduto = {
    'id': 4,
    'nome': 'Headset Gamer 7.1',
    'preco': 259.90
  };

  try {

    final cliente = HttpClient();
    final requisicao = await cliente.postUrl(url);

    requisicao.headers.set('content-type', 'application/json; charset=utf-8');
    requisicao.write(jsonEncode(novoProduto));

    final resposta = await requisicao.close();
    final respostaCorpo = await resposta.transform(utf8.decoder).join();

    cliente.close();
  } catch (e) {
    print('Erro: $e');
  }
}