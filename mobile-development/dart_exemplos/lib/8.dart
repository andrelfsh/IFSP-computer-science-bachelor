class Carro {

  String marca;
  String modelo;
  int ano;

  // Construtor
  Carro(this.marca, this.modelo, this.ano);

  void exibirDetalhes() {
    print('Carro: $marca $modelo | Ano: $ano');
  }
}

void main() {
  Carro meuCarro = Carro('Toyota', 'Corolla', 2024);
  meuCarro.exibirDetalhes();
}