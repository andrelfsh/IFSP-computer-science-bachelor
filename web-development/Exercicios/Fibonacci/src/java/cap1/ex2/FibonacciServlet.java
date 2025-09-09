package cap1.ex2;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FibonacciServlet", urlPatterns = { "/ola" })
public class FibonacciServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        for (int i = 1; i <= 30; i++) {
            System.out.print(fibonacci(i));
        }
    }

    public static long fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1 || n == 2) return 1;

        long n1 = 1, n2 = 1;
        for (int i = 3; i <= n; i++) {
            long tmp = n1 + n2;
            n1 = n2;
            n2 = tmp;
        }
        return n2;
    }
}
