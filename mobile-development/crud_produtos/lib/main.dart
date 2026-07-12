import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart'; // import novo teste
import 'models/produto.dart';
import 'services/database_service.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  await Firebase.initializeApp(
    options: const FirebaseOptions(
      apiKey: "AIzaSyFakeKey_EstudosIpsf_Exemplo",
      appId: "1:123456789:web:abcdef123456",
      messagingSenderId: "123456789",
      projectId: "crud-produtos", // od do firebase
      databaseURL: "https://crud-produtos-744ee-default-rtdb.firebaseio.com", // url do banco
    ),
  );

  runApp(const MaterialApp(
    home: CRUDProdutoPage(),
    debugShowCheckedModeBanner: false,
  ));
}

class CRUDProdutoPage extends StatefulWidget {
  const CRUDProdutoPage({super.key});
  @override
  State<CRUDProdutoPage> createState() => _CRUDProdutoPageState();
}

class _CRUDProdutoPageState extends State<CRUDProdutoPage> {

  final _dbService = DatabaseService();
  final _nomeController = TextEditingController();
  final _precoController = TextEditingController();
  final _qtdController = TextEditingController();

  void _salvar() {

    final nome = _nomeController.text;
    final preco = double.tryParse(_precoController.text) ?? 0.0;
    final qtd = int.tryParse(_qtdController.text) ?? 0;

    if (nome.isNotEmpty) {

      _dbService.adicionarProduto(Produto(nome: nome, preco: preco, quantidade: qtd));
      _nomeController.clear();
      _precoController.clear();
      _qtdController.clear();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('CRUD Produtos')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [

            TextField(controller: _nomeController, decoration: const InputDecoration(labelText: 'Nome')),
            TextField(controller: _precoController, decoration: const InputDecoration(labelText: 'Preço'), keyboardType: TextInputType.number),
            TextField(controller: _qtdController, decoration: const InputDecoration(labelText: 'Quantidade'), keyboardType: TextInputType.number),

            const SizedBox(height: 15),
            ElevatedButton(onPressed: _salvar, child: const Text('Cadastrar')),

            const Divider(height: 30),
            const Text('Produtos no Banco:', style: TextStyle(fontWeight: FontWeight.bold)),
            const SizedBox(height: 10),

            Expanded(
              child: StreamBuilder<List<Produto>>(

                stream: _dbService.getListaProdutos(),

                builder: (context, snapshot) {
                  if (!snapshot.hasData) return const Center(child: CircularProgressIndicator());
                  final produtos = snapshot.data!;
                  if (produtos.isEmpty) return const Center(child: Text('Nenhum produto.'));

                  return ListView.builder(
                    itemCount: produtos.length,
                    itemBuilder: (context, index) {
                      final p = produtos[index];
                      return ListTile(
                        title: Text(p.nome),
                        subtitle: Text('R\$ ${p.preco.toStringAsFixed(2)} | Qtd: ${p.quantidade}'),
                        trailing: IconButton(
                          icon: const Icon(Icons.delete, color: Colors.red),

                          onPressed: () => _dbService.excluirProduto(p.id!),
                        ),
                      );
                    },
                  );
                },
              ),
            )
          ],
        ),
      ),
    );
  }
}