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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class ConProfessor {

    Conexao conn = new Conexao();

    /*Faz o login do professor verificando RM e senha no banco*/
    public Professor logar(Professor professor) {
        /*comando SQL*/
        String sql = "SELECT * FROM Professor WHERE RmProfessor = ? AND Senha = ?";
        try {
            PreparedStatement stmt = conn.conectar().prepareStatement(sql);
            stmt.setString(1, String.valueOf(professor.getRM_Professor()));
            stmt.setString(2, professor.getSENHA_Professor());
            ResultSet rs = stmt.executeQuery();

            /*Pegando as infos no banco*/
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
            /*Mensagem de erro*/
            JOptionPane.showMessageDialog(null, "Erro ao efetuar login: " + ex);

            return null;
        }
    }

    /*Cadastra um novo professor no banco*/
    public boolean cadastrar(Professor professor) {
        /*comando SQL*/
        String sql = "INSERT INTO Professor (RmProfessor, Nome, EmailProfessor, Senha) VALUES (?, ?, ?, ?)";

        try {
            /*Se conectado ao banco, executando comando e desconectando*/
            PreparedStatement stmt = conn.conectar().prepareStatement(sql);
            stmt.setInt(1, professor.getRM_Professor());
            stmt.setString(2, professor.getNome_Professor());
            stmt.setString(3, professor.getEMAIL_Professor());
            stmt.setString(4, professor.getSENHA_Professor());

            stmt.executeUpdate();
            conn.desconectar();

            System.out.println("Professor cadastrado com sucesso!");
            return true;
        } catch (SQLException ex) {
            if (ex instanceof SQLIntegrityConstraintViolationException) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar. RM ou Email já existe!");
                return false;
            } else {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar professor: " + ex.getMessage());

                return false;
            }
        }

    }

    /*Edita os dados de um professor ja existente*/
    public boolean editar(Professor professor) {
        
            /*Comando SQL*/
            String sql = "UPDATE Professor "
                    + "SET Nome = ?, EmailProfessor = ?, Senha = ? "
                    + "WHERE RmProfessor = ?";

            try {
                /*Pegando informações e realizando*/
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
                    JOptionPane.showMessageDialog(null, "Nenhum professor encontrado com esse RM.");
                    return false;
                }
            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Erro ao atualizar professor: " + ex.getMessage());
                return false;
            }
        

    }

    /*Exclui um professor do banco pelo RM dele*/
    public boolean excluir(int rmProfessor) {
        
            /*comando SQL*/
            String sql = "DELETE FROM Professor WHERE RmProfessor = ?";

            try {
                /*Se conectnado ao banco, executando comando e desconectando*/
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
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Erro ao excluir professor: " + ex.getMessage());
                System.exit(0);
                return false;
            }
        

    }

    /*Lista todos os professores cadastrados no banco*/
    public Vector listar() {

        String sql = "SELECT * FROM Professor ORDER BY RmProfessor";

        Vector lista = new Vector();

        try {
            PreparedStatement pstmt = conn.conectar().prepareCall(sql);
            ResultSet rs = pstmt.executeQuery();

            /*Pegando as infos no banco*/
            while (rs.next()) {
                Professor professor = new Professor();
                professor.setRM_Professor(rs.getInt("RmProfessor"));
                professor.setNome_Professor(rs.getString("Nome"));
                professor.setEMAIL_Professor(rs.getString("EmailProfessor"));
                professor.setSENHA_Professor(rs.getString("Senha"));

                /*Cria uma linha com os dados do professor*/
                Vector linha = new Vector();
                linha.addElement(professor.getRM_Professor());
                linha.addElement(professor.getNome_Professor());
                linha.addElement(professor.getEMAIL_Professor());

                lista.addElement(linha);
            }

            conn.desconectar();

        } catch (SQLException ex) {
            /*Mensagem de erro*/
            JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
            conn.desconectar();
        }

        return lista;

    }

    /*Pesquisa um professor pelo RM dele*/
    public Professor pesquisarPorRM(int rmProfessor) {

        /*Comando SQL*/
        String sql = "SELECT * FROM Professor WHERE RmProfessor = ?";
        try {
            PreparedStatement pstmt = conn.conectar().prepareCall(sql);
            pstmt.setInt(1, rmProfessor);
            ResultSet rs = pstmt.executeQuery();

            Professor professor = new Professor();

            /*Pegando as infos no banco*/
            if (rs.next()) {
                professor.setRM_Professor(rs.getInt("RmProfessor"));
                professor.setNome_Professor(rs.getString("Nome"));
                professor.setEMAIL_Professor(rs.getString("EmailProfessor"));
                professor.setSENHA_Professor(rs.getString("Senha"));
            }
            conn.desconectar();
            return professor;
        } catch (SQLException ex) {
            /*Mensagem de erro se não der pra pesquisar*/
            JOptionPane.showMessageDialog(null, "Ocorreu um erro ao pesquisar professor: " + ex);
            conn.desconectar();
            return null;
        }

    }

}
