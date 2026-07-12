double calculaArea(double base, double altura) {
  return (base * altura) / 2;
}

void main() {
  double resultado = calculaArea(10, 5);
  print('A área do triângulo com base 10 e altura 5 é: $resultado');
}