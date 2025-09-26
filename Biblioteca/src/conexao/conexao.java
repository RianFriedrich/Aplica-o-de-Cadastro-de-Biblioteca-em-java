package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author laboratorio
 */
public class conexao {
    public Connection getConexao(){
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca?useTimezone=true&serverTimezone=UTC","root","laboratorio");
            return conn;
        }
        catch(Exception e){
            System.out.println("Erro ao conectar ao DB"+e.getMessage());
            return null;
        }
        
    }
}
