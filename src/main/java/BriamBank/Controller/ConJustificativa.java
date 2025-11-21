/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Controller;

import BriamBank.Model.Conexao;
import BriamBank.Model.Justificativa;
import BriamBank.Model.Professor;
import BriamBank.Model.Session;
import BriamBank.Model.Turma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class ConJustificativa {

    Conexao conn = new Conexao();

    /*Retorna todas as justificativas cadastradas no banco*/
    public Vector RetornaJusts() {
        /*Vendo se o user ta logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*Comando SQL*/
            String sql = "SELECT * FROM justificativas";

            Vector lista = new Vector();

            try {
                PreparedStatement pstmt = conn.conectar().prepareCall(sql);
                ResultSet rs = pstmt.executeQuery();

                /*Pegando as infos no banco*/
                while (rs.next()) {
                    Justificativa just = new Justificativa();
                    just.setID_Justificativa(rs.getInt("IdJustificativa"));
                    just.setDATE_Justificativa(rs.getString("data"));
                    just.setQTD_Justificativa(rs.getDouble("Quantidade"));
                    just.setDESC_Justificativa(rs.getString("Descricao"));
                    just.setMOTIVO_Justificativa(rs.getString("Motivo"));
                    just.setSTATUS_Just(rs.getBoolean("status"));

                    /*Cria uma linha com os dados da justificativa*/
                    Vector linha = new Vector();
                    linha.addElement(just.getID_Justificativa());
                    linha.addElement(just.getDATE_Justificativa());
                    linha.addElement(just.getQTD_Justificativa());
                    linha.addElement(just.getDESC_Justificativa());
                    linha.addElement(just.getMOTIVO_Justificativa());
                    linha.addElement(just.getSTATUS_Just());

                    lista.addElement(linha);
                }

                conn.desconectar();

            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Ocorreu um erro: " + ex);
                conn.desconectar();
            }

            return lista;

        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possível listar as justificativas pois você não está logado(a)");
            System.exit(0);
            return null;
        }
    }

    /*Retorna uma justificativa especifica pelo motivo dela*/
    public Justificativa retornaJust(String motivo) {
        /*Vendo se o user ta logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        Justificativa just = new Justificativa();

        if (prof != null) {
            /*Comando SQL*/
            String sql = "Select * "
                    + "From Justificativas "
                    + "Where motivo = ?";

            try {
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setString(1, motivo);
                ResultSet rs = stmt.executeQuery();

                /*Pegando as infos no banco*/
                if (rs.next()) {
                    just.setDATE_Justificativa(rs.getString("data"));
                    just.setID_Justificativa(rs.getInt("IdJustificativa"));
                    just.setQTD_Justificativa(rs.getDouble("Quantidade"));
                    just.setDESC_Justificativa(rs.getString("Descricao"));
                    just.setMOTIVO_Justificativa(rs.getString("Motivo"));
                    just.setSTATUS_Just(rs.getBoolean("status"));
                }
                conn.desconectar();
                return just;
            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "erro: " + ex);
                conn.desconectar();
            }
        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta pois voce não esta logado");
            System.exit(0);
        }

        return null;
    }

    /*Retorna uma justificativa especifica pelo ID dela*/
    public Justificativa retornaJust(int id) {
        /*Vendo se o user ta logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        Justificativa just = new Justificativa();

        if (prof != null) {
            /*Comando SQL*/
            String sql = "Select * "
                    + "From Justificativas "
                    + "Where IdJustificativa = ?";

            try {
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                /*Pegando as infos no banco*/
                if (rs.next()) {
                    just.setDATE_Justificativa(rs.getString("data"));
                    just.setID_Justificativa(rs.getInt("IdJustificativa"));
                    just.setQTD_Justificativa(rs.getDouble("Quantidade"));
                    just.setDESC_Justificativa(rs.getString("Descricao"));
                    just.setMOTIVO_Justificativa(rs.getString("Motivo"));
                    just.setSTATUS_Just(rs.getBoolean("status"));
                }
                conn.desconectar();
                return just;
            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "erro: " + ex);
                conn.desconectar();
            }
        } else {
            /*Mensagem de erro se n estiver logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a consulta pois voce não esta logado");
            System.exit(0);
        }

        return null;
    }

    /*Cadastra uma nova justificativa no banco*/
    public boolean CadastrarJust(String Desc, String Motivo, Double qtd, String Status) {
        /*Vendo se o user ta logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*comando SQL*/
            String sql = "INSERT INTO JUSTIFICATIVAS (Descricao, Motivo, Quantidade, Data, Status) "
                    + "VALUES (?, ?, ?, ?, ?)";
            try {
                Boolean status;
                if (Status.equals("Inativo")) {
                    status = false;
                } else {
                    status = true;
                }

                LocalDateTime agora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dataHoraMySQL = agora.format(formatter);
                /*Se conectado ao banco, executando comando e desconectando*/
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setString(1, Desc);
                stmt.setString(2, Motivo);
                stmt.setDouble(3, qtd);
                stmt.setString(4, dataHoraMySQL);
                stmt.setBoolean(5, status);
                stmt.executeUpdate();

                conn.desconectar();

                System.out.println("Cadstro de Justificativa realizado com sucesso");
                return true;
            } catch (SQLException ex) {
                /*Mensagem de erro*/
                if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Esse motivo já existe");
                    return false;
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro da justificativa");
                    conn.desconectar();
                    return false;
                }

            }
        } else {
            /*Mensagem de erro por não estar logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro pois voce não esta logado");
            System.exit(0);
            return false;
        }

    }

    /*Edita os dados de uma justificativa ja existente*/
    public boolean EditarJust(String Desc, String Motivo, Double qtd, String Status, int id) {
        /*Vendo se user está logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*Comando SQL*/
            String sql = "UPDATE justificativas "
                    + "SET descricao = ?, Motivo = ?, Quantidade = ?, Data = ?, Status = ? "
                    + "WHERE IdJustificativa = ?";
            Boolean status;
            if (Status.equals("Inativo")) {
                status = false;
            } else {
                status = true;
            }

            try {
                LocalDateTime agora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dataHoraMySQL = agora.format(formatter);
                /*Pegando informações e realizando*/
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setString(1, Desc);
                stmt.setString(2, Motivo);
                stmt.setDouble(3, qtd);
                stmt.setString(4, dataHoraMySQL);
                stmt.setBoolean(5, status);
                stmt.setInt(6, id);
                stmt.executeUpdate();

                conn.desconectar();

                System.out.println("Edição de Justificativa realizado com sucesso!");
                return true;
            } catch (SQLException ex) {
                /*Mensagem de erro*/
                if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(null, "Esse motivo já existe");
                    return false;
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possivel realizar a edição da justificativa");
                    conn.desconectar();
                    return false;
                }
            }
        } else {
            /*Mensagem de n está logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar o update pois voce não esta logado");
            System.exit(0);
            return false;
        }
    }

    /*Exclui uma justificativa do banco pelo ID*/
    public void ExcluirJust(int id) {
        /*Vendo se o user está logado*/
        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            /*comando SQL*/
            String sql = "DELETE "
                    + "FROM justificativas "
                    + "WHERE IdJustificativa = ?";

            try {
                /*Se conectnado ao banco, executando comando e desconectando*/
                PreparedStatement stmt = conn.conectar().prepareStatement(sql);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                conn.desconectar();

                System.out.println("Exclusão de Justificativa realizada com sucesso!");
            } catch (SQLException ex) {
                /*Mensagem de erro*/
                JOptionPane.showMessageDialog(null, "Não foi possivel realizar a exclusão da justificativa");
                conn.desconectar();
            }
        } else {
            /*Mensagem de erro por não estar logado*/
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a exclusão pois voce não esta logado");
            System.exit(0);
        }
    }

}
