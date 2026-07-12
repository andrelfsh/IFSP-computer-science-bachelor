import 'package:flutter/material.dart';
import 'exercises.dart';

void main() { // rodar no web - http://localhost:60315/
  runApp(const MeuAplicativo());
}

class MeuAplicativo extends StatelessWidget {
  const MeuAplicativo({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Lista Semana 10',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      home: const TelaPrincipal(),
    );
  }
}

class TelaPrincipal extends StatelessWidget {
  const TelaPrincipal({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Exercícios Semana 10 - Flutter'),
        backgroundColor: Colors.blue.shade200,
      ),
      body: ListView(
        padding: const EdgeInsets.all(16.0),
        children: [
          _botaoExercicio(context, 'Exercício 1', const Exercicio1Page()),
          _botaoExercicio(context, 'Exercício 2', const Exercicio2Page()),
          _botaoExercicio(context, 'Exercício 3', const Exercicio3Page()),
          _botaoExercicio(context, 'Exercício 4', const Exercicio4Page()),
          _botaoExercicio(context, 'Exercício 5', const Exercicio5Page()),
          _botaoExercicio(context, 'Exercício 6', const Exercicio6Page()),
        ],
      ),
    );
  }

  Widget _botaoExercicio(BuildContext context, String titulo, Widget pagina) {
    return Card(
      margin: const EdgeInsets.symmetric(vertical: 8.0),
      child: ListTile(
        title: Text(titulo, style: const TextStyle(fontWeight: FontWeight.bold)),
        trailing: const Icon(Icons.arrow_forward_ios, size: 16),
        onTap: () {
          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => pagina),
          );
        },
      ),
    );
  }
}