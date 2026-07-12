class Produto {
  String? id;
  String nome;
  double preco;
  int quantidade;

  Produto({this.id, required this.nome, required this.preco, required this.quantidade});


  factory Produto.fromJson(String id, Map<dynamic, dynamic> json) {
    return Produto(
      id: id,
      nome: json['nome'] ?? '',
      preco: (json['preco'] ?? 0.0).toDouble(),
      quantidade: json['quantidade'] ?? 0,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'nome': nome,
      'preco': preco,
      'quantidade': quantidade,
    };
  }
}