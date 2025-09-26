/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Autor;
import beans.Livro;
import java.sql.*;
import conexao.conexao;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author laboratorio
 */
public class livroDAO {

    private conexao conexao;
    private Connection conn;

    public livroDAO() {
        this.conexao = new conexao();
        this.conn = (Connection) this.conexao.getConexao();
    }

    public void inserir(Livro livro) {
        try {
            String sq1 = "INSERT INTO livro (titulo, genero, anoPub, id_autor, numCop) VALUES (?,?,?, ?, ?);";

            PreparedStatement stmt = this.conn.prepareStatement(sq1);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getGenero());
            stmt.setString(3, livro.getAnoPub());
            stmt.setInt(4, livro.getId_autor().getId());
            stmt.setString(5, livro.getNumCop());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir autor: " + ex.getMessage());
        }

    }

    public Livro getLivro(int id) {
        String sql = "SELECT * FROM livro WHERE id=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Livro l = new Livro();

            rs.first();
            l.setId(id);
            l.setTitulo(rs.getString("titulo"));
            l.setGenero(rs.getString("genero"));
            l.setAnoPub(rs.getString("anoPub"));
            //l.setId_autor(rs.);
            l.setNumCop(rs.getString("numCop"));
            return l;

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar livro: " + ex.getMessage());
            return null;
        }
    }

    public List<Livro> getLivroID(String titulo) {
        String sql = "SELECT * FROM livro WHERE titulo LIKE ?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1,"%" + titulo + "%");
            ResultSet rs = stmt.executeQuery();
            List<Livro> listaLivros = new ArrayList();

            while (rs.next()) {
                Livro l = new Livro();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("titulo"));
                l.setGenero(rs.getString("genero"));
                l.setAnoPub(rs.getString("anoPub"));

                autorDAO aDAO = new autorDAO();

                Autor a = aDAO.getAutor(rs.getInt("id_autor"));
                l.setId_autor(a);

                l.setNumCop(rs.getString("numCop"));
                listaLivros.add(l);
            }
            return listaLivros;
        } catch (SQLException ex) {
            System.out.println("Erro ao consultar livros: " + ex.getMessage());
            return null;
        }
    }

    public void excluir(int id) {
        try {
            String sql = "delete from livro where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir livro:" + ex.getMessage());
        }
    }

    public void editar(Livro livro) {
        try {
            String sql = "UPDATE livro set titulo=?, genero=?, anoPub=?, numCop=? where id=?";

            PreparedStatement stmt = conn.prepareStatement(sql);;
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getGenero());
            stmt.setString(3, livro.getAnoPub());
            stmt.setString(4, livro.getNumCop());
            stmt.setInt(5, livro.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar livro:" + ex.getMessage());
        }
    }

    // private Livro getLivro(int aInt) {
    //    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}
}
