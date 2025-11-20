/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BriamBank.Controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import BriamBank.Model.Conexao;
import BriamBank.Model.Professor;
import BriamBank.Model.Session;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ConRelatorio {

    public void gerarRelatorioPorProfessor() {

        Professor prof = Session.getInstancia().getProfessorLogado();

        if (prof != null) {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();

            try {
                String sqlTurmas = "SELECT IdTurma, NomeTurma, Grupo "
                        + "FROM Turma WHERE Cod_Professor = ?";
                PreparedStatement stmtTurmas = conn.prepareStatement(sqlTurmas);

                /*ConProfessor conProf = new ConProfessor(); 
            Professor professor = new Professor();
            professor.setRM_Professor(44444);
            professor.setSENHA_Professor("A1234567");
            conProf.logar(professor);*/
                Professor professor = Session.getInstancia().getProfessorLogado();
                stmtTurmas.setInt(1, professor.getRM_Professor());
                ResultSet rsTurmas = stmtTurmas.executeQuery();

                //Cria uma pasta temporaria pra csg trabalhar mlr com os relatorios e dps compactar
                Path tempDir = Files.createTempDirectory("relatorios_brianbank_");
                List<Path> arquivosGerados = new ArrayList<>();

                while (rsTurmas.next()) {
                    int idTurma = rsTurmas.getInt("IdTurma");
                    String nomeTurma = rsTurmas.getString("NomeTurma");
                    String grupo = rsTurmas.getString("Grupo");

                    //td q n for de a-z, A-Z, 0-9 ele troca por _
                    String nomeArquivo = nomeTurma.replaceAll("[^a-zA-Z0-9]", "_")
                            + "_Grupo_" + grupo + ".xlsx";
                    //Cria o caminho do arquivo (basicamente pega a localização da pasta temporaria e adiciona o nome do arquivo no endereco
                    Path caminhoArquivo = tempDir.resolve(nomeArquivo);

                    String sql = "SELECT A.NomeAluno, T.NomeTurma, T.Grupo, SUM(P.QtdPontos) AS QtdPontos "
                            + "FROM Aluno A "
                            + "INNER JOIN Turma T ON T.IdTurma = A.Cod_Turma "
                            + "INNER JOIN Pontos P ON A.IDAluno = P.Cod_Aluno "
                            + "WHERE T.IdTurma = ? "
                            + "GROUP BY A.NomeAluno, T.NomeTurma, T.Grupo "
                            + "ORDER BY A.NomeAluno";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, idTurma);
                    ResultSet rs = stmt.executeQuery();

                    //Criação da tabela com Worbbook
                    try (Workbook workbook = new XSSFWorkbook()) {

                        Sheet sheet = workbook.createSheet("Relatório de Pontos");

                        String[] colunas = {"Nome", "Turma", "Grupo", "Nota", "Pontos"};
                        Row header = sheet.createRow(0);
                        for (int i = 0; i < colunas.length; i++) {
                            Cell cell = header.createCell(i);
                            cell.setCellValue(colunas[i]);
                        }

                        int rowNum = 1;
                        while (rs.next()) {
                            String nota = "";
                            if (rs.getInt("QtdPontos") >= 92) {
                                nota = "MB";
                            } else if (rs.getInt("QtdPontos") >= 85) {
                                nota = "B";
                            } else if (rs.getInt("QtdPontos") >= 52) {
                                nota = "R";
                            } else {
                                nota = "I";
                            }

                            Row row = sheet.createRow(rowNum++);
                            row.createCell(0).setCellValue(rs.getString("NomeAluno"));
                            row.createCell(1).setCellValue(rs.getString("NomeTurma"));
                            row.createCell(2).setCellValue(rs.getString("Grupo"));
                            row.createCell(3).setCellValue(nota);
                            row.createCell(4).setCellValue(String.valueOf(rs.getInt("QtdPontos")));
                        }

                        for (int i = 0; i < colunas.length; i++) {
                            sheet.autoSizeColumn(i);
                        }

                        try (FileOutputStream fileOut = new FileOutputStream(caminhoArquivo.toFile())) {
                            workbook.write(fileOut);
                        }

                        arquivosGerados.add(caminhoArquivo);
                        System.out.println("Gerado: " + nomeArquivo);
                    }
                }

                String zipFileName = "RelatoriosProfessor_" + professor.getRM_Professor() + ".zip";
                //`Pega o endereco da pasta dowloads
                Path downloads = Paths.get(System.getProperty("user.home"), "Downloads");
                ////Cria o caminho do arquivo (basicamente pega a localização da pasta temporaria e adiciona o nome do arquivo no endereco
            Path zipPath = downloads.resolve(zipFileName);

                //Cria ou escreve em cima de um arquivo no endereco
                try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()))) {
                    //Um loop pra cada arquivo
                    for (Path arquivo : arquivosGerados) {
                        //Abre o arquivo pra pdr copiar
                        try (InputStream fis = new FileInputStream(arquivo.toFile())) {
                            // Adiciona no zip os arquivo
                            ZipEntry entry = new ZipEntry(arquivo.getFileName().toString());
                            zos.putNextEntry(entry);
                            //Copia o conteudo pro arquivo no zip
                            fis.transferTo(zos);
                            // fecha a entrada e deixa pronto pro proximo
                            zos.closeEntry();
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Erro ao gerar relatórios: " + e.getMessage());
                e.printStackTrace();
            } finally {
                conexao.desconectar();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel gerar os relatorios pois você não está logado");         
        }

    }

}
