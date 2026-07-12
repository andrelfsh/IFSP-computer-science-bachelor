void main() {
  Map<String, int> pessoasIdades = {
    'Victória': 20,
    'Andre': 23,
  };

  // Acessando uma pessoa específica pela chave
  String busca = 'Victória';
  print('A idade de $busca é: ${pessoasIdades[busca]} anos.');
}