import 'dart:io';

void main() {

  stdout.write('Digite um número: ');
  String entrada = stdin.readLineSync()!;
  int numero = int.parse(entrada); // converte pra int

  if (numero % 2 == 0) {
    print('O número $numero é PAR.');
  } else {
    print('O número $numero é ÍMPAR.');
  }
}