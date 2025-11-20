/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Model;

/**
 *
 * @author LABINFO
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    Connection conn = null;
  public Connection conectar(){
    String url="jdbc:mysql://localhost/bdbrianbank";
    String usuario = "root";
    String senha ="";
    
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      
      conn = DriverManager.getConnection(url,usuario,senha);
      System.out.println("Conexão criada com secesso");
      return conn;
    } catch (SQLException ex){
     System.out.println("SQLException: " +ex.getMessage());
     return null;
    } catch (Exception e){
     System.out.println("Erro ao conectar:" +e);
     return null;
    }
  }
  public void desconectar(){
    try{
      conn.close();
      System.out.println("Desconexão feita");
      
    } catch (SQLException ex){
      System.out.println("Erro ao desconectar: "+ex);
      
    }
  }  
}

