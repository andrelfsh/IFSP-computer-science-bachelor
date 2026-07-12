Future<String> buscarDados() async {

  await Future.delayed(const Duration(seconds: 3));
  return 'Dados carregados com sucesso';


}

void main() async {
  print('Iniciando busca de dados...');
  String resultado = await buscarDados();

  print(resultado);
}