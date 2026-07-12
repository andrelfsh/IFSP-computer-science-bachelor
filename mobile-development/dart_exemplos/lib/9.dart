void main() {
  String? nomeNullable;
  nomeNullable = null;

  if (nomeNullable != null) {
    print('O valor é: $nomeNullable');
  } else {
    print('A variável está nula');
  }
}