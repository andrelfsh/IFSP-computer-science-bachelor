import 'dart:convert';
import 'dart:io';
import 'package:shelf/shelf.dart';
import 'package:shelf_router/shelf_router.dart'; 
import 'package:shelf/shelf_io.dart' as io;


final List<Map<String, dynamic>> produtos = [
  {'id': 1, 'nome': 'Notebook Gamer i7', 'preco': 5499.90},
  {'id': 2, 'nome': 'Mouse Sem Fio', 'preco': 120.00},
  {'id': 3, 'nome': 'Teclado Mecânico RGB', 'preco': 350.00},
];

void main() async {
  final router = Router();

  router.get('/produtos', (Request request) {

    final jsonResponse = jsonEncode(produtos);

    return Response.ok(
      jsonResponse,

      headers: {'content-type': 'application/json; charset=utf-8'},
    );
  });



  router.post('/adicionar', (Request request) async {

    final body = await request.readAsString();
    final Map<String, dynamic> novoProduto = jsonDecode(body);

    produtos.add(novoProduto);

    return Response.ok(
      jsonEncode({'status': 'sucesso', 'item_adicionado': novoProduto}),
      headers: {'content-type': 'application/json; charset=utf-8'},
    );
  });


  final handler = const Pipeline().addMiddleware(logRequests()).addHandler(router);

  final server = await io.serve(handler, InternetAddress.loopbackIPv4, 8080);

  print('Servidor: http://${server.address.host}:${server.port}');
}