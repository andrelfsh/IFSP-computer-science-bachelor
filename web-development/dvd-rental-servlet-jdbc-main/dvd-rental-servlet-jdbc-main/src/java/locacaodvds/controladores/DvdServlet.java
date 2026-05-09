package locacaodvds.controladores;

import locacaodvds.entidades.Dvd;
import locacaodvds.dao.DvdDAO;
import locacaodvds.entidades.Genero;
import locacaodvds.entidades.Ator;
import locacaodvds.entidades.Classificacao_etaria;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DvdServlet", urlPatterns = { "/processaDvd" })
public class DvdServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        DvdDAO dao = null;
        RequestDispatcher disp = null;

        try {
            dao = new DvdDAO();

            if ("inserir".equals(acao)) {

                String titulo = request.getParameter("titulo");
                int anoLancamento = Integer.parseInt(request.getParameter("ano_lancamento"));
                Date dataLancamento = Date.valueOf(request.getParameter("data"));
                int duracaoMinutos = Integer.parseInt(request.getParameter("duracao"));
                String fotoUrl = request.getParameter("foto_url");  // novo

                int atorPrincipalId = Integer.parseInt(request.getParameter("ator_principal_id"));
                Ator atorPrincipal = new Ator();
                atorPrincipal.setId(atorPrincipalId);

                int atorCoadjuvanteId = Integer.parseInt(request.getParameter("ator_coadjuvante_id"));
                Ator atorCoadjuvante = new Ator();
                atorCoadjuvante.setId(atorCoadjuvanteId);

                int generoId = Integer.parseInt(request.getParameter("genero_id"));
                Genero genero = new Genero();
                genero.setId(generoId);

                int classificacaoId = Integer.parseInt(request.getParameter("classificacao_id"));
                Classificacao_etaria classificacao = new Classificacao_etaria();
                classificacao.setId(classificacaoId);


                Dvd dvd = new Dvd();
                dvd.setTitulo(titulo);
                dvd.setAno_lancamento(anoLancamento);
                dvd.setData_lancamento(dataLancamento);
                dvd.setDuracao_minutos(duracaoMinutos);
                dvd.setAtor_principal(atorPrincipal);
                dvd.setAtor_coadjuvante(atorCoadjuvante);
                dvd.setGenero(genero);
                dvd.setClassificacao_etaria(classificacao);
                dvd.setFoto_url(fotoUrl); // novo
                

                dao.salvar(dvd);

                disp = request.getRequestDispatcher("/formularios/dvd/listagem.jsp");

            } else if ("alterar".equals(acao)) {

                int id = Integer.parseInt(request.getParameter("id"));
                String titulo = request.getParameter("titulo");
                int anoLancamento = Integer.parseInt(request.getParameter("ano_lancamento"));
                Date dataLancamento = Date.valueOf(request.getParameter("data"));
                int duracaoMinutos = Integer.parseInt(request.getParameter("duracao"));
                String fotoUrl = request.getParameter("foto_url");  // novo

                int atorPrincipalId = Integer.parseInt(request.getParameter("ator_principal_id"));
                Ator atorPrincipal = new Ator();
                atorPrincipal.setId(atorPrincipalId);

                int atorCoadjuvanteId = Integer.parseInt(request.getParameter("ator_coadjuvante_id"));
                Ator atorCoadjuvante = new Ator();
                atorCoadjuvante.setId(atorCoadjuvanteId);

                int generoId = Integer.parseInt(request.getParameter("genero_id"));
                Genero genero = new Genero();
                genero.setId(generoId);

                int classificacaoId = Integer.parseInt(request.getParameter("classificacao_id"));
                Classificacao_etaria classificacao = new Classificacao_etaria();
                classificacao.setId(classificacaoId);


                Dvd dvd = new Dvd();
                dvd.setId(id);
                dvd.setTitulo(titulo);
                dvd.setAno_lancamento(anoLancamento);
                dvd.setData_lancamento(dataLancamento);
                dvd.setDuracao_minutos(duracaoMinutos);
                dvd.setAtor_principal(atorPrincipal);
                dvd.setAtor_coadjuvante(atorCoadjuvante);
                dvd.setGenero(genero);
                dvd.setClassificacao_etaria(classificacao);
                dvd.setFoto_url(fotoUrl); // novo

                dao.atualizar(dvd);

                disp = request.getRequestDispatcher("/formularios/dvd/listagem.jsp");

            } else if ("excluir".equals(acao)) {

                int id = Integer.parseInt(request.getParameter("id"));
                Dvd dvd = new Dvd();
                dvd.setId(id);
                dao.excluir(dvd);

                disp = request.getRequestDispatcher("/formularios/dvd/listagem.jsp");

            } else if ("prepararAlteracao".equals(acao) || "prepararExclusao".equals(acao)) {

                int id = Integer.parseInt(request.getParameter("id"));
                Dvd dvd = dao.obterPorId(id);
                request.setAttribute("dvd", dvd);

                if ("prepararAlteracao".equals(acao)) {
                    disp = request.getRequestDispatcher("/formularios/dvd/alterar.jsp");
                } else {
                    disp = request.getRequestDispatcher("/formularios/dvd/excluir.jsp");
                }
            }

        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new ServletException(exc);
        } finally {
            if (dao != null) {
                try {
                    dao.fecharConexao();
                } catch (SQLException exc) {
                    exc.printStackTrace();
                }
            }
        }

        if (disp != null) {
            disp.forward(request, response);
        }

    }

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

    @Override
    public String getServletInfo() {
        return "DvdServlet";
    }

}
