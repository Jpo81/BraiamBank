/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Controller;

import BriamBank.Model.Aluno;
import BriamBank.Model.Conexao;
import BriamBank.Model.Pontos;
import BriamBank.Model.Professor;
import BriamBank.Model.Session;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Miguel
 */
public class ConPontos {

    /*
    CREATE TABLE Pontos (
QtdPontos NUMERIC(10) NOT NULL,
Cod_Aluno INTEGER NOT NULL REFERENCES Aluno(IDAluno),
IdPontos INTEGER PRIMARY KEY UNIQUE NOT NULL AUTO_INCREMENT,
Cod_Justificativa INTEGER NOT NULL REFERENCES Justificativas(IdJustificativa)
);
     */
    Conexao conexao = new Conexao();

    public void cadastroPontos(int codJustificativa, double qtdPontos, int CodAluno) {

        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            String sql = "INSERT INTO PONTOS (QtdPontos, Cod_Aluno, Cod_Justificativa, data) " + "VALUES (?, ?, ?, ?) ";

            try {
                LocalDateTime agora = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String dataHoraMySQL = agora.format(formatter);
                PreparedStatement psmt = conexao.conectar().prepareStatement(sql);
                psmt.setDouble(1, qtdPontos);
                psmt.setInt(2, CodAluno);
                psmt.setInt(3, codJustificativa);
                psmt.setString(4, dataHoraMySQL);
                psmt.executeUpdate();

                conexao.desconectar();

                System.out.println("Cadstro de ponto realizado com sucesso");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um ERRO:" + ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de pontos pois você não está logado(a)");
        }

    }

    public Pontos PesquisarPontos(String CodAluno) {

        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            String sql = "SELECT A.IDAluno AS ID, A.NomeAluno AS Aluno, SUM(P.QtdPontos) AS Pontos "
                    + "FROM aluno A "
                    + "INNER JOIN pontos P "
                    + "ON A.IDAluno = P.Cod_Aluno "
                    + "INNER JOIN turma t "
                    + "ON t.IdTurma = A.Cod_Turma "
                    + "WHERE A.IDAluno = ? "
                    + "AND t.Cod_professor = ? "
                    + "GROUP BY A.IDAluno, A.NomeAluno";

            try {
                PreparedStatement psmt = conexao.conectar().prepareCall(sql);
                psmt.setString(1, CodAluno);
                psmt.setInt(2, Session.getInstancia().getProfessorLogado().getRM_Professor());
                ResultSet rs = psmt.executeQuery();

                Pontos pontos = new Pontos();
                Aluno aluno = new Aluno();

                while (rs.next()) {
                    pontos.setCOD_Aluno(rs.getInt("ID"));
                    pontos.setNOME_Aluno(rs.getString("Aluno"));
                    pontos.setQTD_Pontos(rs.getDouble("Pontos"));
                }

                conexao.desconectar();
                return pontos;

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                return null;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a consulta");
            return null;
        }

    }

}
