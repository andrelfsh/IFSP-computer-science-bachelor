import 'package:firebase_database/firebase_database.dart';
import '../models/produto.dart';

class DatabaseService {

  final DatabaseReference _dbRef = FirebaseDatabase.instance.ref().child('produtos');

  Future<void> adicionarProduto(Produto produto) async {


    await _dbRef.push().set(produto.toJson());
  }

  Stream<List<Produto>> getListaProdutos() {

    return _dbRef.onValue.map((event) {
      final snapshot = event.snapshot;
      if (snapshot.value == null) return [];

      final Map<dynamic, dynamic> map = snapshot.value as Map<dynamic, dynamic>;
      List<Produto> lista = [];

      map.forEach((key, value) {
        lista.add(Produto.fromJson(key, value));
      });

      return lista;
    });
  }

  Future<void> atualizarProduto(Produto produto) async {
    if (produto.id != null) {
      await _dbRef.child(produto.id!).update(produto.toJson());
    }
  }

  Future<void> excluirProduto(String id) async {
    await _dbRef.child(id).remove();
  }
}