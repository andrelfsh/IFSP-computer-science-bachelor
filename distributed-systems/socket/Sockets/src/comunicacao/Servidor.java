package comunicacao;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner; 

public class Servidor {
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket servidor = new ServerSocket(8189);

        while (true) { // lop do servidor rodando
            
            Socket cliente = servidor.accept();
            Scanner entrada = new Scanner(cliente.getInputStream());
            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);

            // recebe a opção (1 ou 2)
            String opcao = entrada.nextLine();
            saida.println("ok");

            // recebe os dados para processar
            String dados = entrada.nextLine();
            String resposta = "";

            if (opcao.equals("1")) { // conta

                String[] partes = dados.split(" ");
                double n1 = Double.parseDouble(partes[0]);
                String op = partes[1];
                double n2 = Double.parseDouble(partes[2]);

                if (op.equals("+")) resposta = "" + (n1 + n2);
                else if (op.equals("-")) resposta = "" + (n1 - n2);
                else if (op.equals("*")) resposta = "" + (n1 * n2);
                else if (op.equals("/")) resposta = "" + (n1 / n2);
            } 
            
            else if (opcao.equals("2")) { // texto

                String[] partes = dados.split(";");
                int pos = partes[0].indexOf(partes[1]);
                resposta = (pos != -1) ? "Posicao: " + pos : "Nao existe.";
                
            }

            saida.println(resposta); 
            cliente.close();
        }
    }
}