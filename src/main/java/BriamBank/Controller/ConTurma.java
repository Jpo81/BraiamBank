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
public class ConTurma {

    Conexao conn = new Conexao();

    /*Retorna todas as turmas do professor logado*/
    public Vector retornar_turmas() {
        /*Vendo se o user ta logado*/
        Professor professor = Session.getInstancia().getProfessorLogado();
        Vector lista = new Vector();
        if (professor != null) {
            /*Comando SQL*/
            String sql = "SELECT * "
                    + "FROM turma "
                    + "WHERE COD_Professor = ?;";

            try {
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setString(1, String.valueOf(professor.getRM_Professor()));
                ResultSet rs = stmt.executeQuery();

                /*Pegando as infos no banco*/
                while (rs.next()) {

                    Turma turma = new Turma();
                    turma.setANO_Turma(rs.getInt("Ano"));
                    turma.setCOD_Professor(rs.getInt("cod_professor"));
                    turma.setCURSO_Turma(rs.getString("curso"));
                    turma.setGRUPO_Turma(rs.getString("grupo"));
                    turma.setID_Turma(rs.getInt("idturma"));
                    turma.setNOME_Turma(rs.getString("nometurma"));
                    turma.setPERIODO_Turma(rs.getString("periodo"));

                    lista.addElement(turma);
                }
                conn.desconectar();
            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Erro ao listar turmas do professor: " + ex.getMessage());
                return lista;
            }
            return lista;

        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o login para consultar as turmas do professor");
            System.exit(0);
            return lista;
        }

    }

    /*Retorna uma turma especifica pelo codigo dela*/
    public Turma retornaTurma(int codTurma) {
        /*Vendo se o user ta logado*/
        Professor professor = Session.getInstancia().getProfessorLogado();
        if (professor != null) {
            Vector lista = new Vector();
            /*Comando SQL*/
            String sql = "SELECT * "
                    + "FROM turma "
                    + "WHERE IDTURMA = ? AND COD_Professor = ?";

            Turma turma = new Turma();

            try {
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setString(1, String.valueOf(codTurma));
                stmt.setInt(2, professor.getRM_Professor());
                ResultSet rs = stmt.executeQuery();

                /*Pegando as infos no banco*/
                if (rs.next()) {
                    turma.setANO_Turma(rs.getInt("Ano"));
                    turma.setCOD_Professor(rs.getInt("cod_professor"));
                    turma.setCURSO_Turma(rs.getString("curso"));
                    turma.setGRUPO_Turma(rs.getString("grupo"));
                    turma.setID_Turma(rs.getInt("idturma"));
                    turma.setNOME_Turma(rs.getString("nometurma"));
                    turma.setPERIODO_Turma(rs.getString("periodo"));
                }
                conn.desconectar();
            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Erro procurar dados da turma" + ex.getMessage());
                return null;
            }
            return turma;

        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o login para consultar os dados da turma");
            System.exit(0);
            return null;
        }
    }

    /*Retorna uma turma especifica pelo nome e grupo (formato: "Nome-Grupo")*/
    public Turma retornaTurma(String nomeTurma) {
        /*Vendo se o user ta logado*/
        Professor professor = Session.getInstancia().getProfessorLogado();
        if (professor != null) {
            /*Comando SQL*/
            String sql = "SELECT * FROM turma WHERE Nometurma = ? AND grupo = ? AND COD_Professor = ?";
            String[] dados = nomeTurma.split("-");

            if (dados.length < 2) {
                JOptionPane.showMessageDialog(null, "Formato de nome da turma inválido");
                return null;
            }

            try {
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setString(1, dados[0]);
                stmt.setString(2, dados[1]);
                stmt.setInt(3, professor.getRM_Professor());
                ResultSet rs = stmt.executeQuery();

                Turma turma = null;
                /*Pegando as infos no banco*/
                if (rs.next()) {
                    turma = new Turma();
                    turma.setANO_Turma(rs.getInt("Ano"));
                    turma.setCOD_Professor(rs.getInt("cod_professor"));
                    turma.setCURSO_Turma(rs.getString("curso"));
                    turma.setGRUPO_Turma(rs.getString("grupo"));
                    turma.setID_Turma(rs.getInt("idturma"));
                    turma.setNOME_Turma(rs.getString("nometurma"));
                    turma.setPERIODO_Turma(rs.getString("periodo"));
                }
                conn.desconectar();
                return turma;

            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Erro ao procurar dados da turma: " + ex.getMessage());
                return null;
            }

        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar o login para consultar os dados da turma");
            System.exit(0);
            return null;
        }
    }

    /*
    * @author Pedro
     */
    /*Cadastra uma nova turma no banco*/
    public boolean cadTurma(Turma turma) {
        /*Vendo se o user ta logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*comando SQL*/
            String sql = "INSERT INTO TURMA(ANO, COD_PROFESSOR, GRUPO, CURSO, PERIODO, NOMETURMA) "
                    + "VALUES(?,?,?,?,?,?);";

            try {

                if (turma.getGRUPO_Turma().equals("Inteiro")) {
                    turma.setGRUPO_Turma("I");
                }

                /*Se conectado ao banco, executando comando e desconectando*/
                PreparedStatement psmt = conn.conectar().prepareStatement(sql);
                psmt.setInt(1, turma.getANO_Turma());
                psmt.setInt(2, prof.getRM_Professor());
                psmt.setString(3, turma.getGRUPO_Turma());
                psmt.setString(4, turma.getCURSO_Turma());
                psmt.setString(5, turma.getPERIODO_Turma());
                psmt.setString(6, turma.getNOME_Turma());
                psmt.executeUpdate();

                conn.desconectar();

                System.out.println("Cadastro de Turma realizado com sucesso!");
                return true;

            } catch (SQLException ex) {
                /*Mensagem de erro*/
                if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Essa turma já existe");
                    return false;
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
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

    /*Pesquisa uma turma pelo ID dela*/
    public Turma pesquisarID(String ID) {
        /*Vendo se o user tá logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();
        if (prof != null) {
            /*Comando SQL*/
            String sql = "Select * from turma where IdTurma = ? AND COD_Professor = ?";
            try {
                PreparedStatement pstmt = conn.conectar().prepareCall(sql);
                pstmt.setString(1, ID);
                pstmt.setInt(2, prof.getRM_Professor());
                ResultSet rs = pstmt.executeQuery();

                Turma turma = new Turma();

                /*Pegando as infos no banco*/
                while (rs.next()) {
                    turma.setID_Turma(rs.getInt("IdTurma"));
                    turma.setANO_Turma(rs.getInt("Ano"));
                    turma.setCOD_Professor(rs.getInt("Cod_Professor"));
                    turma.setGRUPO_Turma(rs.getString("Grupo"));
                    turma.setCURSO_Turma(rs.getString("Curso"));
                    turma.setPERIODO_Turma(rs.getString("Periodo"));
                    turma.setNOME_Turma(rs.getString("NomeTurma"));

                }
                conn.desconectar();
                return turma;
            } catch (SQLException ex) {
                /*Mensagem de erro se não der pra pesquisar*/
                JOptionPane.showMessageDialog(null, ex);
                return null;
            }
        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a pesquisa pois você não está logado(a)");
            System.exit(0);
            return null;
        }
    }

    /*Exclui uma turma do banco pelo ID*/
    public void excluir(int ID) {
        /*Vendo se o user está logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*comando SQL*/
            String sql = "DELETE from Turma where IdTurma = ? AND COD_Professor = ?";
            try {
                /*Se conectnado ao banco, executando comando e desconectando*/
                PreparedStatement psmt = conn.conectar().prepareStatement(sql);
                psmt.setInt(1, ID);
                psmt.setInt(2, prof.getRM_Professor());
                psmt.executeUpdate();
                conn.desconectar();

            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Ocorreu um erro:" + ex);
            }
        } else {
            /*Mensagem de erro por não estar logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel excluir o aluno pois você não está logado(a)");
            System.exit(0);
        }
    }

    /*Edita os dados de uma turma ja existente*/
    public boolean editar(Turma turma) {
        /*Vendo se user está logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*Comando SQL*/
            String sql = "UPDATE Turma SET Ano = ?, Grupo = ?, Curso = ?, Periodo = ?, NomeTurma = ?"
                    + " where IdTurma = ? AND cod_Professor = ?";
            try {
                if (turma.getGRUPO_Turma().equals("Inteiro")) {
                    turma.setGRUPO_Turma("I");
                }
                /*Pegando informações e realizando*/
                PreparedStatement psmt = conn.conectar().prepareStatement(sql);
                psmt.setInt(1, turma.getANO_Turma());
                psmt.setString(2, turma.getGRUPO_Turma());
                psmt.setString(3, turma.getCURSO_Turma());
                psmt.setString(4, turma.getPERIODO_Turma());
                psmt.setString(5, turma.getNOME_Turma());
                psmt.setInt(6, turma.getID_Turma());
                psmt.setInt(7, prof.getRM_Professor());
                psmt.executeUpdate();
                conn.desconectar();
                return true;
            } catch (SQLException ex) {
                /*Mensagem de erro*/
                if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Essa turma já existe");
                    return false;
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
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

    /*Retorna todas as turmas com o mesmo nome (sem filtrar por grupo)*/
    public Vector<Turma> RetornaTurmaCompleta(String nomeTurma) {
        /*Vendo se o user ta logado*/
        Professor professor = Session.getInstancia().getProfessorLogado();

        Vector<Turma> turmas = new Vector();

        if (professor != null) {
            /*Comando SQL*/
            String sql = "SELECT * FROM turma WHERE Nometurma = ? AND COD_Professor = ?";

            try {
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setString(1, nomeTurma);
                stmt.setInt(2, professor.getRM_Professor());
                ResultSet rs = stmt.executeQuery();

                /*Pegando as infos no banco*/
                while (rs.next()) {

                    Turma turma = new Turma();

                    turma = new Turma();
                    turma.setANO_Turma(rs.getInt("Ano"));
                    turma.setCOD_Professor(rs.getInt("cod_professor"));
                    turma.setCURSO_Turma(rs.getString("curso"));
                    turma.setGRUPO_Turma(rs.getString("grupo"));
                    turma.setID_Turma(rs.getInt("idturma"));
                    turma.setNOME_Turma(rs.getString("nometurma"));
                    turma.setPERIODO_Turma(rs.getString("periodo"));

                    turmas.addElement(turma);
                }
                conn.desconectar();
                return turmas;

            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Erro ao procurar dados da turma: " + ex.getMessage());
                return turmas;
            }

        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possível efetuar o login para consultar os dados da turma");
            System.exit(0);
            return turmas;
        }
    }

}
