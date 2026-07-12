import 'package:flutter/material.dart';
import 'dart:math'; // lib para calculos amtematicos

// =============================================================================
class Exercicio1Page extends StatelessWidget {

  const Exercicio1Page({super.key});

  @override
  Widget build(BuildContext context) {
    const id = '1';
    const nome = 'André';
    const idade = 23;
    const genero = 'Masculino';
    final saudacao = 'sou $nome, id $id, genero $genero, tenho $idade anos';

    return Scaffold(
      appBar: AppBar(title: const Text('Exercício 1'), backgroundColor: Colors.blue.shade100),
      body: Center(
        child: Card(
          margin: const EdgeInsets.all(20),
          child: Padding(
            padding: const EdgeInsets.all(20),
            child: Text(saudacao, style: const TextStyle(fontSize: 18), textAlign: TextAlign.center),
          ),
        ),
      ),
    );
  }
}

// =============================================================================


class Exercicio2Page extends StatefulWidget {
  const Exercicio2Page({super.key});
  @override
  State<Exercicio2Page> createState() => _Exercicio2PageState();
}

class _Exercicio2PageState extends State<Exercicio2Page> {

  final _controller = TextEditingController();
  String _resultado = '';

  void _calcular() {
    final raio = double.tryParse(_controller.text) ?? 0.0;
    final volume = (4.0 / 3.0) * pi * pow(raio, 3);
    setState(() {
      _resultado = 'volume: ${volume.toStringAsFixed(2)}';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Exercício 2'), backgroundColor: Colors.blue.shade100),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
                controller: _controller,
                decoration: const InputDecoration(labelText: 'Digite o raio da esfera'),
                keyboardType: TextInputType.number
            ),
            const SizedBox(height: 20),
            ElevatedButton(onPressed: _calcular, child: const Text('Calcular Volume')),
            const SizedBox(height: 20),
            Text(_resultado, style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }
}

// =============================================================================

class Exercicio3Page extends StatefulWidget {
  const Exercicio3Page({super.key});
  @override
  State<Exercicio3Page> createState() => _Exercicio3PageState();
}

class _Exercicio3PageState extends State<Exercicio3Page> {
  final _controller = TextEditingController();
  String _resultado = '';

  void _converter() {
    final fahrenheit = double.tryParse(_controller.text) ?? 0.0;

    final celsius = (5.0 / 9.0) * (fahrenheit - 32);

    setState(() {
      _resultado = '$fahrenheit°F equivale a ${celsius.toStringAsFixed(1)}°C';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Exercício 3'), backgroundColor: Colors.blue.shade100),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
                controller: _controller,
                decoration: const InputDecoration(labelText: 'Temperatura em Fahrenheit'),
                keyboardType: TextInputType.number
            ),
            const SizedBox(height: 20),
            ElevatedButton(onPressed: _converter, child: const Text('Converter')),
            const SizedBox(height: 20),
            Text(_resultado, style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }
}

// =============================================================================


class Exercicio4Page extends StatefulWidget {
  const Exercicio4Page({super.key});
  @override
  State<Exercicio4Page> createState() => _Exercicio4PageState();
}

class _Exercicio4PageState extends State<Exercicio4Page> {
  final _baseController = TextEditingController();
  final _alturaController = TextEditingController();
  String _resultado = '';

  void _calcular() {
    final base = double.tryParse(_baseController.text) ?? 0.0;
    final altura = double.tryParse(_alturaController.text) ?? 0.0;
    setState(() {
      _resultado = 'A área é: ${base * altura}';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Exercício 4'), backgroundColor: Colors.blue.shade100),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(controller: _baseController, decoration: const InputDecoration(labelText: 'Base'), keyboardType: TextInputType.number),
            TextField(controller: _alturaController, decoration: const InputDecoration(labelText: 'Altura'), keyboardType: TextInputType.number),
            const SizedBox(height: 20),
            ElevatedButton(onPressed: _calcular, child: const Text('Calcular Área')),
            const SizedBox(height: 20),
            Text(_resultado, style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }
}

// =============================================================================
// EXERCÍCIO 5: FATORIAL
// =============================================================================
class Exercicio5Page extends StatefulWidget {
  const Exercicio5Page({super.key});
  @override
  State<Exercicio5Page> createState() => _Exercicio5PageState();
}

class _Exercicio5PageState extends State<Exercicio5Page> {
  final _controller = TextEditingController();
  String _resultado = '';

  void _calcular() {
    final numero = int.tryParse(_controller.text) ?? -1;
    if (numero < 0) {
      setState(() => _resultado = 'Digite um número válido (>= 0)');
      return;
    }
    int fat = 1;
    for (int i = 1; i <= numero; i++) {
      fat *= i;
    }
    setState(() {
      _resultado = 'O fatorial de $numero! é: $fat';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Exercício 5'), backgroundColor: Colors.blue.shade100),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
                controller: _controller,
                decoration: const InputDecoration(labelText: 'Número Inteiro'),
                keyboardType: TextInputType.number
            ),
            const SizedBox(height: 20),
            ElevatedButton(onPressed: _calcular, child: const Text('Calcular Fatorial')),
            const SizedBox(height: 20),
            Text(_resultado, style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }
}

// =============================================================================

class Exercicio6Page extends StatefulWidget {
  const Exercicio6Page({super.key});
  @override
  State<Exercicio6Page> createState() => _Exercicio6PageState();
}

class _Exercicio6PageState extends State<Exercicio6Page> {
  final _controller = TextEditingController();
  String _resultado = '';

  void _verificar() {
    final ano = int.tryParse(_controller.text) ?? 0;
    bool bissexto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    setState(() {
      _resultado = bissexto ? 'O ano $ano É bissexto.' : 'O ano $ano NÃO é bissexto.';
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Exercício 6'), backgroundColor: Colors.blue.shade100),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
                controller: _controller,
                decoration: const InputDecoration(labelText: 'Digite o Ano'),
                keyboardType: TextInputType.number
            ),
            const SizedBox(height: 20),
            ElevatedButton(onPressed: _verificar, child: const Text('Verificar Ano')),
            const SizedBox(height: 20),
            Text(_resultado, style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
          ],
        ),
      ),
    );
  }
}