/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Controller;

import BriamBank.Model.Aluno;
import BriamBank.Model.Conexao;
import BriamBank.Model.Professor;
import BriamBank.Model.Session;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author LABINFO
 */
public class ConAluno {

    Conexao conexao = new Conexao();

    public boolean cadastrar(Aluno aluno) {

        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            String sql = "INSERT INTO ALUNO(EmailAluno, DataNasc, NomeAluno, Cod_Turma, RmAluno) "
                    + "VALUES(?,?,?,?,?);";

            try {

                PreparedStatement psmt = conexao.conectar().prepareCall(sql);
                psmt.setString(1, aluno.getEMAIL_Aluno());
                psmt.setString(2, aluno.getDATA_NASC_Aluno());
                psmt.setString(3, aluno.getNOME_Aluno());
                psmt.setInt(4, aluno.getCOD_Turma());
                psmt.setInt(5, aluno.getRM_Aluno());
                
                psmt.executeUpdate();

                conexao.desconectar();

                System.out.println("Cadastro de aluno realizado com sucesso!");
                return true;

            } catch (SQLException ex) {
                if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Esse aluno já existe");
                    return false;
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
                    return false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro pois voce não esta logado");
            return false;
        }

    }

    public Aluno pesquisarID(String ID) {

        Professor prof = Session.getInstancia().getProfessorLogado();
        if (prof != null) {
            String sql = "Select * from Aluno where IDAluno = ?";
            try {
                PreparedStatement pstmt = conexao.conectar().prepareCall(sql);
                pstmt.setString(1, ID);
                ResultSet rs = pstmt.executeQuery();

                Aluno aluno = new Aluno();

                while (rs.next()) {
                    aluno.setID_Aluno(rs.getInt("IDAluno"));
                    aluno.setEMAIL_Aluno(rs.getString("EmailAluno"));
                    aluno.setDATA_NASC_Aluno(rs.getString("DataNasc"));
                    aluno.setNOME_Aluno(rs.getString("NomeAluno"));
                    aluno.setCOD_Turma(rs.getInt("Cod_Turma"));
                    aluno.setRM_Aluno(rs.getInt("RmAluno"));

                }
                conexao.desconectar();
                return aluno;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a pesquisa pois você não está logado(a)");
            return null;
        }

    }

    public boolean editar(Aluno aluno) {

        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            String sql = "UPDATE ALUNO SET EmailAluno = ?, DataNasc = ?, NomeAluno = ?, Cod_Turma = ?, RmAluno = ?"
                    + " where idaluno = ?";
            try {

                PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
                psmt.setString(1, aluno.getEMAIL_Aluno());
                psmt.setString(2, aluno.getDATA_NASC_Aluno());
                psmt.setString(3, aluno.getNOME_Aluno());
                psmt.setInt(4, aluno.getCOD_Turma());
                psmt.setInt(5, aluno.getRM_Aluno());
                psmt.setInt(6, aluno.getID_Aluno());
                psmt.executeUpdate();
                conexao.desconectar();
                
                return true;
            } catch (SQLException ex) {
               if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Já existe um aluno com este rm nesta turma");
                    return false;
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
                    return false;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a edição pois você não está logado(a)");
            return false;
        }

    }

    public void excluir(int ID) {
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            String sql = "DELETE from Aluno where IDAluno = ?";
            try {
                PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
                psmt.setInt(1, ID);
                psmt.executeUpdate();
                conexao.desconectar();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir o aluno pois você não está logado(a)");

        }

    }

    public Vector listarPorTurma(int CodTurma) {

        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            String sql = "SELECT *, SUM(QtdPontos) AS 'Pontos' "
                    + "FROM aluno "
                    + "INNER JOIN pontos "
                    + "ON idaluno = cod_aluno "
                    + "WHERE cod_turma = ?"
                    + "GROUP BY NomeAluno";

            Vector lista = new Vector();

            try {
                PreparedStatement pstmt = conexao.conectar().prepareCall(sql);
                pstmt.setString(1, String.valueOf(CodTurma));
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    Aluno aluno = new Aluno();
                    aluno.setCOD_Turma(CodTurma);
                    aluno.setID_Aluno(rs.getInt("idaluno"));
                    aluno.setNOME_Aluno(rs.getString("nomealuno"));
                    aluno.setRM_Aluno(rs.getInt("rmaluno"));
                    double somapontos = rs.getDouble("Pontos");

                    Vector linha = new Vector();
                    linha.addElement(aluno.getID_Aluno());
                    linha.addElement(aluno.getNOME_Aluno());
                    linha.addElement(aluno.getRM_Aluno());
                    linha.addElement(somapontos);

                    linha.addElement(CodTurma);

                    lista.addElement(linha);
                }

                conexao.desconectar();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);

            }
            return lista;
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel listar os alunos pois você não está logado(a)");
            return null;
        }

    }

}
