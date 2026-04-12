package comunicacao;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        
        Socket socket = new Socket("localhost", 8189);
        
        Scanner teclado = new Scanner(System.in);
        
        PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
        
        Scanner entrada = new Scanner(socket.getInputStream());

        System.out.println("--- SERVICOS ---");
        System.out.println("[1] Calc (Ex: 10 + 5)");
        System.out.println("[2] Busca (Ex: frase;palavra)");
        System.out.print("Escolha: ");

                
        saida.println(teclado.nextLine()); // Envia 1 ou 2

        if (entrada.nextLine().equals("ok")) {
            System.out.println("Digite:");
            saida.println(teclado.nextLine()); 
            
            System.out.println("Resultado: " + entrada.nextLine());
        }

        socket.close();
    }
}