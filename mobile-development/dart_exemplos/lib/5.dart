void main() {
  List<String> cidades = ['São Paulo', 'Sâo João da Boa Vista', 'Campinas', 'Mococa'];

  print('Lista Inicial');
  for (int i = 0; i < cidades.length; i++) {
    print('Cidade: ${cidades[i]}');
  }

  // Adicionando uma nova cidade
  cidades.add('Aguai');
  print('\n');
  print('Lista Atualizada');

  print(cidades);
}