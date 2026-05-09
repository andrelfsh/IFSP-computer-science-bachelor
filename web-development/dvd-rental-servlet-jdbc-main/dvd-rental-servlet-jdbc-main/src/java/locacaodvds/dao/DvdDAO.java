/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.dao;

import locacaodvds.entidades.Dvd;
import java.sql.Date; // extra
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
// conexões externas
import locacaodvds.entidades.Classificacao_etaria; // adicionado
import locacaodvds.entidades.Ator;
import locacaodvds.entidades.Genero;

/**
 *
 * @author Victória - Revisado por André
 */
public class DvdDAO extends DAO<Dvd> {

    public DvdDAO() throws SQLException {
    }

    @Override
    public void salvar(Dvd obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                INSERT INTO dvd (
                    titulo,
                    ano_lancamento,
                    ator_principal_id,
                    ator_coadjuvante_id,
                    data_lancamento,
                    duracao_minutos,
                    classificacao_etaria_id,
                    genero_id,
                    foto_url
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); 
                """, PreparedStatement.RETURN_GENERATED_KEYS); // ADICIONADO PARA PRINTAR O ID NO TERMINAL

        stmt.setString(1, obj.getTitulo());
        stmt.setInt(2, obj.getAno_lancamento());
        stmt.setInt(3, obj.getAtor_principal().getId());
        stmt.setInt(4, obj.getAtor_coadjuvante().getId());
        stmt.setDate(5, (Date) obj.getData_lancamento());
        stmt.setInt(6, obj.getDuracao_minutos());
        stmt.setInt(7, obj.getClassificacao_etaria().getId());
        stmt.setInt(8, obj.getGenero().getId());
        stmt.setString(9, obj.getFoto_url());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys(); // ADICIONADO PARA PRINTAR O ID NO TERMINAL
        if (rs.next()) { // ADICIONADO PARA PRINTAR O ID NO TERMINAL
            obj.setId(rs.getInt(1));
        }

        stmt.close();

    }

   

    @Override
    public void atualizar(Dvd obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                UPDATE dvd
                SET 
                    titulo = ?,
                    ano_lancamento = ?,
                    ator_principal_id = ?,
                    ator_coadjuvante_id = ?,
                    data_lancamento = ?,
                    duracao_minutos = ?,
                    classificacao_etaria_id = ?,
                    genero_id = ?,
                    foto_url = ? 
                WHERE 
                    id = ?;
                """);

        stmt.setString(1, obj.getTitulo());
        stmt.setInt(2, obj.getAno_lancamento());
        stmt.setInt(3, obj.getAtor_principal().getId());
        stmt.setInt(4, obj.getAtor_coadjuvante().getId());
        stmt.setDate(5, (Date) obj.getData_lancamento());
        stmt.setInt(6, obj.getDuracao_minutos());
        stmt.setInt(7, obj.getClassificacao_etaria().getId());
        stmt.setInt(8, obj.getGenero().getId());
        stmt.setString(9, obj.getFoto_url()); 
        stmt.setInt(10, obj.getId()); // mudar para 10 futuramente


        


        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void excluir(Dvd obj) throws SQLException {

        PreparedStatement stmt = getConnection().prepareStatement(
                """
                DELETE FROM dvd
                WHERE
                    id =?;
                """);

        stmt.setInt(1, obj.getId());

        stmt.executeUpdate();
        stmt.close();

    }

    @Override
    public List<Dvd> listarTodos() throws SQLException {

        List<Dvd> lista = new ArrayList<>();

        PreparedStatement stmt = getConnection().prepareStatement( // SQL ALTERADO - ANDRÈ
                """
            SELECT 
                d.id,
                d.titulo,
                d.ano_lancamento,
                d.data_lancamento,
                d.duracao_minutos,
                d.foto_url,
                ap.id AS ator_principal_id,
                ap.nome AS ator_principal_nome,
                ap.sobrenome AS ator_principal_sobrenome,
                ac.id AS ator_coadjuvante_id,
                ac.nome AS ator_coadjuvante_nome,
                ac.sobrenome AS ator_coadjuvante_sobrenome,
                g.id AS genero_id,
                g.descricao AS genero_descricao,
                c.id AS classificacao_etaria_id,
                c.descricao AS classificacao_etaria_descricao
            FROM dvd d
            JOIN ator ap ON d.ator_principal_id = ap.id
            JOIN ator ac ON d.ator_coadjuvante_id = ac.id
            JOIN genero g ON d.genero_id = g.id
            JOIN classificacao_etaria c ON d.classificacao_etaria_id = c.id
            ORDER BY d.titulo;
            """
        );

        ResultSet rs = stmt.executeQuery(); // ADICIONADO PARA PRINTAR O ID NO TERMINAL

        while (rs.next()) { // ADICIONADO PARA PRINTAR O ID NO TERMINAL

            Dvd d = new Dvd();
            Ator atorPrincipal = new Ator();
            Ator atorCoadjuvante = new Ator();
            Genero genero = new Genero();
            Classificacao_etaria classificacao = new Classificacao_etaria();

            d.setId(rs.getInt("id"));
            d.setTitulo(rs.getString("titulo"));
            d.setAno_lancamento(rs.getInt("ano_lancamento"));
            d.setData_lancamento(rs.getDate("data_lancamento"));
            d.setDuracao_minutos(rs.getInt("duracao_minutos"));

            atorPrincipal.setId(rs.getInt("ator_principal_id"));
            atorPrincipal.setNome(rs.getString("ator_principal_nome"));
            atorPrincipal.setSobrenome(rs.getString("ator_principal_sobrenome"));

            atorCoadjuvante.setId(rs.getInt("ator_coadjuvante_id"));
            atorCoadjuvante.setNome(rs.getString("ator_coadjuvante_nome"));
            atorCoadjuvante.setSobrenome(rs.getString("ator_coadjuvante_sobrenome"));

            genero.setId(rs.getInt("genero_id"));
            genero.setDescricao(rs.getString("genero_descricao"));

            classificacao.setId(rs.getInt("classificacao_etaria_id"));
            classificacao.setDescricao(rs.getString("classificacao_etaria_descricao"));

            d.setAtor_principal(atorPrincipal);
            d.setAtor_coadjuvante(atorCoadjuvante);
            d.setGenero(genero);
            d.setClassificacao_etaria(classificacao);
            d.setFoto_url(rs.getString("foto_url"));


            lista.add(d);
        }

        rs.close();
        stmt.close();

        return lista;
    }

    @Override
    public Dvd obterPorId(int id) throws SQLException {

        Dvd dvd = null;

        PreparedStatement stmt = getConnection().prepareStatement( // SQL ALTERADO - ANDRÈ
                """ 
                    SELECT 
                        dvd.id,
                        dvd.titulo,
                        dvd.ano_lancamento,
                        dvd.data_lancamento,
                        dvd.duracao_minutos,
                        dvd.foto_url,
                        ator_principal.id,
                        ator_principal.nome,
                        ator_principal.sobrenome,
                        ator_coadjuvante.id,
                        ator_coadjuvante.nome,
                        ator_coadjuvante.sobrenome,
                        genero.id,
                        genero.descricao,
                        classificacao_etaria.id,
                        classificacao_etaria.descricao
                    FROM dvd, ator ator_principal, ator ator_coadjuvante, genero, classificacao_etaria
                    WHERE 
                        dvd.id = ? AND
                        dvd.ator_principal_id = ator_principal.id AND
                        dvd.ator_coadjuvante_id = ator_coadjuvante.id AND
                        dvd.genero_id = genero.id AND
                        dvd.classificacao_etaria_id = classificacao_etaria.id
                """);

        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery(); // ADICIONADO PARA PRINTAR O ID NO TERMINAL

        if (rs.next()) { // ADICIONADO PARA PRINTAR O ID NO TERMINAL

            dvd = new Dvd();

            Ator atorPrincipal = new Ator();
            atorPrincipal.setId(rs.getInt("ator_principal.id"));
            atorPrincipal.setNome(rs.getString("ator_principal.nome"));
            atorPrincipal.setSobrenome(rs.getString("ator_principal.sobrenome"));

            Ator atorCoadjuvante = new Ator();
            atorCoadjuvante.setId(rs.getInt("ator_coadjuvante.id"));
            atorCoadjuvante.setNome(rs.getString("ator_coadjuvante.nome"));
            atorCoadjuvante.setSobrenome(rs.getString("ator_coadjuvante.sobrenome"));

            Genero genero = new Genero();
            genero.setId(rs.getInt("genero.id"));
            genero.setDescricao(rs.getString("genero.descricao"));

            Classificacao_etaria classificacao = new Classificacao_etaria();
            classificacao.setId(rs.getInt("classificacao_etaria.id"));
            classificacao.setDescricao(rs.getString("classificacao_etaria.descricao"));

            dvd.setId(rs.getInt("dvd.id"));
            dvd.setTitulo(rs.getString("dvd.titulo"));
            dvd.setAno_lancamento(rs.getInt("dvd.ano_lancamento"));
            dvd.setData_lancamento(rs.getDate("dvd.data_lancamento"));
            dvd.setDuracao_minutos(rs.getInt("dvd.duracao_minutos"));

            dvd.setAtor_principal(atorPrincipal);
            dvd.setAtor_coadjuvante(atorCoadjuvante);
            dvd.setGenero(genero);
            dvd.setClassificacao_etaria(classificacao);
            dvd.setFoto_url(rs.getString("foto_url"));

        }

        rs.close();
        stmt.close();

        return dvd;
    }

}
