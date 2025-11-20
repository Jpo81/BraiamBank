/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Controller;

import BriamBank.Model.Conexao;
import BriamBank.Model.Professor;
import BriamBank.Model.Session;
import BriamBank.Model.Turma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class ConProfessor {

    Conexao conn = new Conexao();

    public Professor logar(Professor professor) {
        String sql = "SELECT * FROM Professor WHERE RmProfessor = ? AND Senha = ?";
        try {
            PreparedStatement stmt = conn.conectar().prepareStatement(sql);
            stmt.setString(1, String.valueOf(professor.getRM_Professor()));
            stmt.setString(2, professor.getSENHA_Professor());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Professor p = new Professor();
                p.setRM_Professor(rs.getInt("RmProfessor"));
                p.setSENHA_Professor(rs.getString("Senha"));
                p.setNome_Professor(rs.getString("Nome"));
                p.setEMAIL_Professor(rs.getString("EmailProfessor"));

                System.out.println("Login válido!");
                Session.getInstancia().setProfessorLogado(p);
                conn.desconectar();
                return p;
            } else {
                System.out.println("Usuário ou senha incorretos.");
                conn.desconectar();
                return null;
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao efetuar login: " + ex);
            return null;
        }
    }

    

    public boolean cadastrar(Professor professor) {

        String sql = "INSERT INTO Professor (RmProfessor, Nome, EmailProfessor, Senha) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.conectar().prepareStatement(sql);
            stmt.setInt(1, professor.getRM_Professor());
            stmt.setString(2, professor.getNome_Professor());
            stmt.setString(3, professor.getEMAIL_Professor());
            stmt.setString(4, professor.getSENHA_Professor());

            int linhasAfetadas = stmt.executeUpdate();
            conn.desconectar();
            if (linhasAfetadas == 1) {
                System.out.println("Professor cadastrado com sucesso!");
                return true;
            } else {
                System.out.println("Falha ao cadastrar o professor.");
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar professor: " + ex.getMessage());
            return false;
        }

    }

    public boolean editar(Professor professor) {

        Professor prof = Session.getInstancia().getProfessorLogado();
        if (prof != null) {
            String sql = "UPDATE Professor "
                    + "SET Nome = ?, EmailProfessor = ?, Senha = ? "
                    + "WHERE RmProfessor = ?";

            try  {
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setString(1, professor.getNome_Professor());
                stmt.setString(2, professor.getEMAIL_Professor());
                stmt.setString(3, professor.getSENHA_Professor());
                stmt.setInt(4, professor.getRM_Professor());

                int linhasAfetadas = stmt.executeUpdate();
                conn.desconectar();
                if (linhasAfetadas > 0) {
                    System.out.println("Professor atualizado com sucesso!");
                    return true;
                } else {
                    System.out.println("Nenhum professor encontrado com esse RM.");
                    return false;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar professor: " + ex.getMessage());
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o loguin para consultar as turmas do professor");
            return false;
        }

    }

    public boolean excluir(int rmProfessor) {

        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {

            String sql = "DELETE FROM Professor WHERE RmProfessor = ?";

            try {
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setInt(1, rmProfessor);

                int linhasAfetadas = stmt.executeUpdate();
                conn.desconectar();
                if (linhasAfetadas > 0) {
                    System.out.println("Professor excluído com sucesso!");
                    return true;
                } else {
                    System.out.println("Nenhum professor encontrado com esse RM.");
                    return false;
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao excluir professor: " + ex.getMessage());
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o loguin para consultar as turmas do professor");
            return false;
        }

    }
    
}
