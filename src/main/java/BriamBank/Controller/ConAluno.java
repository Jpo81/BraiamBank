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

    /*Cadastra um novo aluno no banco*/
    public boolean cadastrar(Aluno aluno) {
        /*Vendo se o user ta logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*comando SQL*/
            String sql = "INSERT INTO ALUNO(EmailAluno, DataNasc, NomeAluno, Cod_Turma, RmAluno) "
                    + "VALUES(?,?,?,?,?);";

            try {
                /*Se conectado ao banco, executando comando e desconectando*/
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
                /*Mensagem de erro*/
                if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Esse aluno já existe");
                    conexao.desconectar();
                    return false;
                } else {
                    /*Mensagem de erro*/
                    JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
                    conexao.desconectar();
                    return false;
                }
            }
        } else {
            /*Mensagem de erro por não estar logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro pois voce não esta logado");
            System.exit(0);
            return false;
        }

    }

    /*Pesquisa um aluno pelo ID dele*/
    public Aluno pesquisarID(String ID) {
        /*Vendo se o user ta logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();
        if (prof != null) {
            /*Comando SQL*/
            String sql = "Select * from Aluno where IDAluno = ?";
            try {
                PreparedStatement pstmt = conexao.conectar().prepareCall(sql);
                pstmt.setString(1, ID);
                ResultSet rs = pstmt.executeQuery();

                Aluno aluno = new Aluno();

                /*Pegando as infos no banco*/
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
                /*Mensagem de erro se não der pra pesquisar*/
                JOptionPane.showMessageDialog(null, ex);
                conexao.desconectar();
                return null;
            }
        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a pesquisa pois você não está logado(a)");
            System.exit(0);
            return null;
        }

    }

    /*Edita os dados de um aluno ja existente*/
    public boolean editar(Aluno aluno) {
        /*Vendo se user está logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*Comando SQL*/
            String sql = "UPDATE ALUNO SET EmailAluno = ?, DataNasc = ?, NomeAluno = ?, Cod_Turma = ?, RmAluno = ?"
                    + " where idaluno = ?";
            try {
                /*Pegando informações e realizando*/
                PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
                psmt.setString(1, aluno.getEMAIL_Aluno());
                psmt.setString(2, aluno.getDATA_NASC_Aluno());
                psmt.setString(3, aluno.getNOME_Aluno());
                psmt.setInt(4, aluno.getCOD_Turma());
                psmt.setInt(5, aluno.getRM_Aluno());
                psmt.setInt(6, aluno.getID_Aluno());
                int altera = psmt.executeUpdate();
                conexao.desconectar();
                
                if (altera != 0 ) {
                    System.out.println("Edição realizada com sucesso");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "nenhum registro encontrado");
                    return false;
                }
                
                
            } catch (SQLException ex) {
                /*Mensagem de erro*/
               if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Já existe um aluno com este rm nesta turma");
                    conexao.desconectar();
                    return false;
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
                    conexao.desconectar();
                    return false;
                }
            }
        } else {
            /*Mensagem de n está logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a edição pois você não está logado(a)");
            System.exit(0);
            return false;
        }

    }

    /*Exclui um aluno do banco pelo ID*/
    public boolean excluir(int ID) {
        /*Vendo se o user está logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*comando SQL*/
            String sql = "DELETE from Aluno where IDAluno = ?";
            try {
                /*Se conectnado ao banco, executando comando e desconectando*/
                PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
                psmt.setInt(1, ID);
                int altera = psmt.executeUpdate();
                conexao.desconectar();
                
                if (altera != 0) {
                    System.out.println("Exclusão realizada com exito");
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum registro encontrado");
                    return false;
                }
                

            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
                conexao.desconectar();
                return false;
            }
        } else {
            /*Mensagem de erro por não estar logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir o aluno pois você não está logado(a)");
            System.exit(0);
            

        }
        return false;

    }

    /*Lista todos os alunos de uma turma com os pontos somados de cada um*/
    public Vector listarPorTurma(int CodTurma) {
        /*Vendo se o user ta logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*Comando SQL*/
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

                /*Pegando as infos no banco*/
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
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
                conexao.desconectar();

            }
            return lista;
        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel listar os alunos pois você não está logado(a)");
            System.exit(0);
            return null;
        }

    }

}
