/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Autor;
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
public class autorDAO {
    private conexao conexao;
    private Connection conn;
    
    public autorDAO(){
        this.conexao = new conexao();
        this.conn = (Connection) this.conexao.getConexao();
    }
    
    public void inserir(Autor autor){
        try {
            String sq1 = "INSERT INTO autor (nome, nacionalidade, datanasc) VALUES (?,?,?);";
        
            PreparedStatement stmt = this.conn.prepareStatement(sq1);
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setString(3, autor.getDatanasc());
            
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir autor: " + ex.getMessage());
        }
        
    }
    
    public Autor getAutor(int id){
        String sql = "SELECT * FROM autor WHERE id=?";
        
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Autor a = new Autor();

            rs.first();
            a.setId(id);
            a.setNome(rs.getString("nome"));
            a.setNacionalidade(rs.getString("nacionalidade"));
            a.setDatanasc(rs.getString("datanasc"));
            return a;

        } catch (SQLException ex) {
            System.out.println("Erro ao consultar autor: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Autor> getAutores() {
        String sql = "SELECT * FROM autor";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = stmt.executeQuery();
            List<Autor> listaAutores = new ArrayList();
            
            while (rs.next()) {
                Autor a = new Autor();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setNacionalidade(rs.getString("nacionalidade"));
                a.setDatanasc(rs.getString("datanasc"));
               listaAutores.add(a);
            }
            return listaAutores;
        } catch (SQLException ex) {
            Logger.getLogger(autorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void editar(Autor autor) {
        try {
            String sql = "UPDATE autor set nome=?, nacionalidade=?, datanasc=? where id=?";

            PreparedStatement stmt = conn.prepareStatement(sql);;
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setString(3, autor.getDatanasc());
            stmt.setInt(4, autor.getId());
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar autor:" + ex.getMessage());
        }
    }
    
    public void excluir(int id) {
        try {
            String sql = "delete from autor where id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir autor:" + ex.getMessage());
        }
    }
    
     public List<Autor> getAutorNome(String nome) {
        String sql = "SELECT * FROM autor WHERE nome LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.setString(1, "%" + nome + "%");
            ResultSet rs = stmt.executeQuery();
            List<Autor> listaAutores = new ArrayList();

            while (rs.next()) {
                Autor a = new Autor();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setNacionalidade(rs.getString("nacionalidade"));
                a.setDatanasc(rs.getString("datanasc"));
                listaAutores.add(a);
            }
            return listaAutores;
        } catch (SQLException ex) {
            Logger.getLogger(autorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}


