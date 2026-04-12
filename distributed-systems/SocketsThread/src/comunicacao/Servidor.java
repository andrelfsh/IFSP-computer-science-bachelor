package comunicacao;

// libs modificadas para ficarem mais abrangentes
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Servidor {
    
    // Lista para guardar infost dos clientes (fluxo de saida)
    private static List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) {
        
        try (ServerSocket server = new ServerSocket(8189)) {
            System.out.println("Servidor de Broadcast rodando na porta 8189...");

            while (true) {
                Socket sIncoming = server.accept();
                System.out.println("Novo cliente: " + sIncoming.getInetAddress());
                new Thread(new ManipuladorCliente(sIncoming)).start();
                
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método de broandsscat
    public static synchronized void broadcast(String msg) {
        for (PrintWriter out : clientes) {
            out.println(msg);
        }
    }

    // Classe que lida com cada cliente separadamente
    private static class ManipuladorCliente implements Runnable {
        
        private Socket socket;
        private PrintWriter out;

        public ManipuladorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (Scanner in = new Scanner(socket.getInputStream(), StandardCharsets.UTF_8)) {
                this.out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
                
                // Adiciona o canal de saída deste cliente à lista global
                synchronized (clientes) {
                    clientes.add(this.out);
                }

                String idCliente = socket.getRemoteSocketAddress().toString(); // endereço do socket. ver como mudar dps

                while (in.hasNextLine()) {
                    String linha = in.nextLine();
                    if (linha.equalsIgnoreCase("TCHAU")) break;

                    // Envia a mensagem identificada para todos
                    broadcast("Cliente [" + idCliente + "] diz: " + linha);
                }
            } catch (IOException e) {
                System.err.println("Erro no cliente: " + e.getMessage());
            } finally {
                
                // Remove o cliente da lista ao desconectar
                synchronized (clientes) {
                    clientes.remove(out);
                }
                try { socket.close(); } catch (IOException e) {}
            }
        }
    }
}