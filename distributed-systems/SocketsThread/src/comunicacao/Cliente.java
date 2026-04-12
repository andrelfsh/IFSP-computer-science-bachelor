package comunicacao;

// libs modificadas para ficarem mais abrangentes
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cliente { // fazer mais de uma isntancia para comunicar
    
    public static void main(String[] args) {
        
        try (Socket socket = new Socket("localhost", 8189);
                
             Scanner leitorTeclado = new Scanner(System.in);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
             Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8)) {

            System.out.println("Conectado! (TCHAU para sair):");

            // thread que fica lendo o que o servidor manda
            new Thread(() -> {
                while (in.hasNextLine()) {
                    System.out.println("\n[BROADCAST] " + in.nextLine());
                }
            }).start();

            while (true) {
                
                String msg = leitorTeclado.nextLine();
                out.println(msg);
                
                if (msg.equalsIgnoreCase("TCHAU")) break;
                
            }

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}